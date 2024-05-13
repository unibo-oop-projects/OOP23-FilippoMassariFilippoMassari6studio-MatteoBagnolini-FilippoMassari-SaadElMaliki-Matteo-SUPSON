package supson.view.impl;

import java.awt.Graphics;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Player;
import supson.view.SpriteMap;
import supson.view.api.WorldView;

public class WorldViewImpl implements WorldView{

    private static final int CAMERA_RANGE = 5;
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;
    private static final String BACKGROUND_STRING = "sprite/background.jpg";

    private final SpriteMap spriteMap = new SpriteMap();

    private final List<GameEntity> cameraGameEntitiesList = new ArrayList<>();

    private void selectGameEntity(List<GameEntity> gameEntitiesList, Player player) {
        for (GameEntity gameEntity : gameEntitiesList) {
            if (gameEntity.getPosition().y() >= player.getPosition().y() - CAMERA_RANGE
                && gameEntity.getPosition().y() <= player.getPosition().y() + CAMERA_RANGE) {
                this.cameraGameEntitiesList.add(gameEntity);
            }
        }
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
        JLabel backgrondLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(BACKGROUND_STRING)));
        //backgrondLabel.setBounds(0, 0, 100, 500);
        gameFrame.add(backgrondLabel);
        for (GameEntity gameEntity : cameraGameEntitiesList) {
            Optional<ImageIcon> icon = getEntityImage(gameEntity);
            JLabel label = new JLabel(icon.get());
            //label.setBounds(gameEntity.getPosition().x(), gameEntity.getPosition().y(), DEFAULT_WIDTH, DEFAULT_HEIGHT);
            label.setBounds(10, 10, DEFAULT_WIDTH, DEFAULT_HEIGHT); //il get position è un double
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
