package supson.view.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Player;
import supson.view.SpriteMap;
import supson.view.api.WorldView;

public class WorldViewImpl implements WorldView{

    private static final int CAMERA_RANGE = 10;
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;

    private final SpriteMap spriteMap = new SpriteMap();

    private final List<GameEntity> cameraGameEntitiesList = new ArrayList<>();

    private void selectGameEntity(List<GameEntity> gameEntitiesList, Player player) {
        for (GameEntity gameEntity : gameEntitiesList) {
            if (gameEntity.getPosition().x() >= player.getPosition().x() - CAMERA_RANGE
                && gameEntity.getPosition().x() <= player.getPosition().x() + CAMERA_RANGE) {
                this.cameraGameEntitiesList.add(gameEntity);
            }
        }
        this.cameraGameEntitiesList.add(player);
    }

    private Optional<ImageIcon> getEntityImage(GameEntity gameEntity) {
        Optional<URL> imgURL = Optional.ofNullable(ClassLoader.getSystemResource(spriteMap.getSpritePath(gameEntity.getGameEntityType())));
        try {
            return Optional.of(new ImageIcon(imgURL.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private void addToPanel(JFrame gameFrame) {
        for (GameEntity gameEntity : cameraGameEntitiesList) {
            Optional<ImageIcon> icon = getEntityImage(gameEntity);
            JLabel label = new JLabel(icon.get());
            label.setBounds((int)gameEntity.getPosition().x()*DEFAULT_WIDTH, (int)gameEntity.getPosition().y()*DEFAULT_HEIGHT+270, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            gameFrame.add(label);
        }
    }

    @Override
    public void renderWorld(JFrame gameFrame, List<GameEntity> gameEntitiesList, Player player) {
        cameraGameEntitiesList.clear();
        selectGameEntity(gameEntitiesList, player);
        addToPanel(gameFrame);
    }

}
