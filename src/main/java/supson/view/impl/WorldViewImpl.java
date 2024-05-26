package supson.view.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.entity.api.GameEntity;
import supson.model.entity.player.Player;
import supson.view.SpriteMap;
import supson.view.api.WorldView;

/**
 * Implementation of the {@link WorldView} interface.
 * Responsible for rendering the game world and its entities on the game frame.
 */
public class WorldViewImpl implements WorldView {

    private static final int CAMERA_RANGE = 20;
    private static final int DEFAULT_DIMENSION = 24;

    private final SpriteMap spriteMap = new SpriteMap();
    private final List<GameEntity> cameraGameEntitiesList = new ArrayList<>();

    /**
     * Selects the game entities to be rendered based on the player's position and camera range.
     * Adds the selected game entities to the cameraGameEntitiesList.
     *
     * @param gameEntitiesList the list of all game entities
     * @param player the player entity
     * @param mapWidth the width of the map
     */
    private void selectGameEntity(final List<GameEntity> gameEntitiesList, final Player player, final int mapWidth) {
        cameraGameEntitiesList.clear();
        int playerX = (int) player.getPosition().x();
        int leftBoundary = playerX - CAMERA_RANGE;
        int rightBoundary = playerX + CAMERA_RANGE;

        if (playerX >= mapWidth - CAMERA_RANGE) {
            leftBoundary = mapWidth - 2 * CAMERA_RANGE;
            rightBoundary = mapWidth;
        }

        if (playerX <= CAMERA_RANGE) {
            leftBoundary = 0;
            rightBoundary = 2 * CAMERA_RANGE;
        }

        for (GameEntity gameEntity : gameEntitiesList) {
            if (gameEntity.getPosition().x() >= leftBoundary
                && gameEntity.getPosition().x() <= rightBoundary) {
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
     * Adds the game entities to the game frame panel.
     *
     * @param gameFrame the game frame
     * @param player the player entity
     * @param mapWidth the width of the map
     */
    private void addToPanel(final JFrame gameFrame, final Player player, final int mapWidth) {
        int centerX = (gameFrame.getWidth() / 2) - (3 * DEFAULT_DIMENSION / 4); //todo refactor
        int centerY = gameFrame.getHeight() / 2;
        int playerX = (int) player.getPosition().x();
        int playerY = (int) player.getPosition().y();

        for (GameEntity gameEntity : cameraGameEntitiesList) {
            Optional<ImageIcon> icon = getEntityImage(gameEntity);
            if (icon.isPresent()) {
                JLabel label = new JLabel(icon.get());
                Pos2d pos = gameEntity.getPosition();
                int x, y;
                if (playerX >= mapWidth - CAMERA_RANGE) {
                    x = (int) Math.round(centerX + (pos.x() - (mapWidth - CAMERA_RANGE)) * DEFAULT_DIMENSION);
                } else if (playerX <= CAMERA_RANGE) {
                    x = (int) Math.round(centerX + (pos.x() - CAMERA_RANGE) * DEFAULT_DIMENSION);
                } else {
                    x = (int) Math.round(centerX + (pos.x() - playerX) * DEFAULT_DIMENSION);
                }
                int gameEntityHeight = DEFAULT_DIMENSION * gameEntity.getHeight();
                y = (int) Math.round(centerY - (pos.y() - playerY) * gameEntityHeight);
                label.setBounds(x, y, DEFAULT_DIMENSION, gameEntityHeight);
                gameFrame.add(label);
            }
        }
    }

    @Override
    public void renderWorld(final JFrame gameFrame, final List<GameEntity> gameEntitiesList, final Player player) {
        cameraGameEntitiesList.clear();
        gameFrame.getContentPane().removeAll();
        int mapWidth = 0;
        for (GameEntity gameEntity : gameEntitiesList) { //todo refactor
            if (gameEntity.getPosition().x() > mapWidth) {
                mapWidth = (int) gameEntity.getPosition().x();
            }
        }
        selectGameEntity(gameEntitiesList, player, mapWidth);
        addToPanel(gameFrame, player, mapWidth);
        gameFrame.revalidate();
        gameFrame.repaint();
    }
}
