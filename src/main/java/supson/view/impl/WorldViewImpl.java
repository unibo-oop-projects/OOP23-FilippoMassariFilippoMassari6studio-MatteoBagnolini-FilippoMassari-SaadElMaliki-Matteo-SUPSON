package supson.view.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import supson.common.GameEntityType;
import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Player;
import supson.view.SpriteMap;
import supson.view.api.WorldView;

/**
 * Implementation of the {@link WorldView} interface.
 * Responsible for rendering the game world and its entities on the game frame.
 */
public class WorldViewImpl implements WorldView {

    private static final int CAMERA_RANGE = 10;
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;

    private final SpriteMap spriteMap = new SpriteMap();

    private final List<GameEntity> cameraGameEntitiesList = new ArrayList<>();

    /**
     * Selects the game entities to be rendered based on the player's position and camera range.
     * Adds the selected game entities to the cameraGameEntitiesList.
     *
     * @param gameEntitiesList the list of all game entities
     * @param player the player entity
     */
    private void selectGameEntity(final List<GameEntity> gameEntitiesList, final Player player) {
        for (GameEntity gameEntity : gameEntitiesList) {
            if (gameEntity.getPosition().x() >= player.getPosition().x() - CAMERA_RANGE
                && gameEntity.getPosition().x() <= player.getPosition().x() + CAMERA_RANGE) {
                this.cameraGameEntitiesList.add(gameEntity);
            }
        }
        this.cameraGameEntitiesList.add(player);
    }

    /**
     * Retrieves the image icon for a given game entity.
     *
     * @param gameEntity the game entity
     * @return an Optional containing the image icon if found, or an empty Optional if not found
     */
    private Optional<ImageIcon> getEntityImage(final GameEntity gameEntity) {
        final GameEntityType type = gameEntity.getGameEntityType();
        Optional<URL> imgURL = Optional.ofNullable(ClassLoader.getSystemResource(spriteMap.getSpritePath(type)));
        try {
            return Optional.of(new ImageIcon(imgURL.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Adds game entities to the game frame panel.
     *
     * @param gameFrame the JFrame representing the game frame
     */
    private void addToPanel(final JFrame gameFrame) {
        for (GameEntity gameEntity : cameraGameEntitiesList) {
            Optional<ImageIcon> icon = getEntityImage(gameEntity);
            JLabel label = new JLabel(icon.get());
             //è troppo lungo bisogna sistemarla
            label.setBounds((int)gameEntity.getPosition().x()*DEFAULT_WIDTH, (int)gameEntity.getPosition().y()*DEFAULT_HEIGHT+270, DEFAULT_WIDTH, DEFAULT_HEIGHT);
            gameFrame.add(label);
        }
    }

    @Override
    public void renderWorld(final JFrame gameFrame, final List<GameEntity> gameEntitiesList, final Player player) {
        cameraGameEntitiesList.clear();
        gameFrame.getContentPane().removeAll();
        selectGameEntity(gameEntitiesList, player);
        addToPanel(gameFrame);
        gameFrame.revalidate();
        gameFrame.repaint();
    }

}
