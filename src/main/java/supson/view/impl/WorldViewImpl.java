package supson.view.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.entity.api.GameEntity;
import supson.model.entity.player.Player;
import supson.view.api.WorldView;

public class WorldViewImpl implements WorldView {

    private static final int CAMERA_RANGE = 21;
    private static final int DEFAULT_DIMENSION = 24;
    private static final int PIXELS_CORRECTOR = 3;

    private final List<GameEntity> cameraGameEntitiesList = new ArrayList<>();

    @Override
    public void renderWorld(final JPanel gamePanel, final List<GameEntity> gameEntitiesList, final Player player) {
        cameraGameEntitiesList.clear();
        int mapWidth = getMapWidth(gameEntitiesList);
        selectGameEntity(gameEntitiesList, player, mapWidth);
        addToPanel(gamePanel, player, mapWidth);
    }

    /**
     * Calculates the width of the map based on the positions of the game entities.
     *
     * @param gameEntitiesList the list of game entities.
     * @return the width of the map.
     */
    private int getMapWidth(final List<GameEntity> gameEntitiesList) { //TODO :  da calcolare nel loadworld della map
        int mapWidth = 0;
        for (GameEntity gameEntity : gameEntitiesList) {
            if (gameEntity.getPosition().x() > mapWidth) {
                mapWidth = (int) gameEntity.getPosition().x();
            }
        }
        return mapWidth;
    }

    /**
     * Selects the game entities that should be displayed in the camera view.
     *
     * @param gameEntitiesList the list of game entities.
     * @param player the player.
     * @param mapWidth the width of the map.
     */
    private void selectGameEntity(final List<GameEntity> gameEntitiesList, final Player player, final int mapWidth) {
        cameraGameEntitiesList.clear();
        final int playerX = (int) player.getPosition().x();
        final int leftBoundary = calculateLeftBoundary(playerX, mapWidth);
        final int rightBoundary = calculateRightBoundary(playerX, mapWidth);

        gameEntitiesList.stream()
            .filter(gameEntity -> isWithinCameraRange(gameEntity, leftBoundary, rightBoundary))
            .forEach(cameraGameEntitiesList::add);

        cameraGameEntitiesList.add(player);
    }

    /**
     * Calculates the left boundary of the camera view.
     *
     * @param playerX the X position of the player.
     * @param mapWidth the width of the map.
     * @return the left boundary.
     */
    private int calculateLeftBoundary(final int playerX, final int mapWidth) {
        if (playerX >= mapWidth - CAMERA_RANGE) {
            return mapWidth - 2 * CAMERA_RANGE;
        }
        return Math.max(0, playerX - CAMERA_RANGE);
    }

    /**
     * Calculates the right boundary of the camera view.
     *
     * @param playerX the X position of the player.
     * @param mapWidth the width of the map.
     * @return the right boundary.
     */
    private int calculateRightBoundary(final int playerX, final int mapWidth) {
        if (playerX <= CAMERA_RANGE) {
            return 2 * CAMERA_RANGE;
        }
        return Math.min(mapWidth, playerX + CAMERA_RANGE);
    }

    /**
     * Checks if a game entity is within the camera range.
     *
     * @param gameEntity the game entity to check.
     * @param leftBoundary the left boundary of the camera view.
     * @param rightBoundary the right boundary of the camera view.
     * @return true if the game entity is within the camera range, false otherwise.
     */
    private boolean isWithinCameraRange(final GameEntity gameEntity, final int leftBoundary, final int rightBoundary) {
        final double entityX = gameEntity.getPosition().x();
        return entityX >= leftBoundary && entityX <= rightBoundary;
    }


    /**
 * Adds the selected game entities to the game panel.
 *
 * @param gamePanel the game panel.
 * @param player the player.
 * @param mapWidth the width of the map.
 */
private void addToPanel(final JPanel gamePanel, final Player player, final int mapWidth) {
    final int centerX = (gamePanel.getWidth() / 2) + PIXELS_CORRECTOR;
    final int centerY = gamePanel.getHeight() / 2;
    final double playerX = player.getPosition().x();
    final double playerY = player.getPosition().y();

    for (GameEntity gameEntity : cameraGameEntitiesList) {
        Optional<ImageIcon> icon = getImageIcon(gameEntity, player);
        icon.ifPresent(imageIcon -> {
            JLabel label = createLabel(imageIcon);
            setPosition(label, gameEntity, playerX, playerY, centerX, centerY, mapWidth);
            gamePanel.add(label);
        });
    }
}

    /**
     * Sets the position of a JLabel based on the game entity's position and the player's position.
     *
     * @param label the JLabel to position.
     * @param gameEntity the game entity.
     * @param playerX the player's x position.
     * @param playerY the player's y position.
     * @param centerX the center x position of the game panel.
     * @param centerY the center y position of the game panel.
     * @param mapWidth the width of the map.
     */
    private void setPosition(final JLabel label, final GameEntity gameEntity, final double playerX, final double playerY, 
                            final int centerX, final int centerY, final int mapWidth) {
        Pos2d pos = gameEntity.getPosition();
        int x = calculateXPosition(pos.x(), playerX, centerX, mapWidth);
        int y = calculateYPosition(pos.y(), playerY, centerY, gameEntity);

        int gameEntityHeight = DEFAULT_DIMENSION * gameEntity.getHeight();
        label.setBounds(x, y, DEFAULT_DIMENSION, gameEntityHeight);
    }

    /**
     * Calculates the x position of a game entity on the panel.
     *
     * @param entityX the x position of the game entity.
     * @param playerX the x position of the player.
     * @param centerX the center x position of the game panel.
     * @param mapWidth the width of the map.
     * @return the calculated x position.
     */
    private int calculateXPosition(final double entityX, final double playerX, final int centerX, final int mapWidth) {
        if (playerX >= mapWidth - CAMERA_RANGE) {
            return (int) Math.round(centerX + (entityX - (mapWidth - CAMERA_RANGE)) * DEFAULT_DIMENSION);
        } else if (playerX <= CAMERA_RANGE) {
            return (int) Math.round(centerX + (entityX - CAMERA_RANGE) * DEFAULT_DIMENSION);
        } else {
            return (int) Math.round(centerX + (entityX - playerX) * DEFAULT_DIMENSION);
        }
    }

    /**
     * Calculates the y position of a game entity on the panel.
     *
     * @param entityY the y position of the game entity.
     * @param playerY the y position of the player.
     * @param centerY the center y position of the game panel.
     * @param gameEntity the game entity.
     * @return the calculated y position.
     */
    private int calculateYPosition(final double entityY, final double playerY, final int centerY, final GameEntity gameEntity) {
        int gameEntityHeight = DEFAULT_DIMENSION * gameEntity.getHeight();
        if (gameEntity.getGameEntityType().equals(GameEntityType.PLAYER)
                || gameEntity.getGameEntityType().equals(GameEntityType.ENEMY)
                || gameEntity.getGameEntityType().equals(GameEntityType.SUBTERRAIN)) {
            return (int) Math.round(centerY - (entityY - playerY) * DEFAULT_DIMENSION - (DEFAULT_DIMENSION / 2));
        } else {
            return (int) Math.round(centerY - (entityY - playerY) * gameEntityHeight);
        }
    }

    /**
     * Gets the image icon for the game entity.
     *
     * @param gameEntity the game entity.
     * @param player the player.
     * @return an optional containing the image icon if present, otherwise an empty optional.
     */
    private Optional<ImageIcon> getImageIcon(final GameEntity gameEntity, final Player player) {
        if (gameEntity.getGameEntityType().equals(GameEntityType.PLAYER)) {
            return getPlayerImage(player);
        } else {
            return getEntityImage(gameEntity);
        }
    }

    /**
     * Creates a label for the image icon.
     *
     * @param icon the image icon.
     * @return the created label.
     */
    private JLabel createLabel(final ImageIcon icon) {
        JLabel label = new JLabel(icon);
        label.setOpaque(false);
        return label;
    }

    /**
     * Gets the image icon for a game entity.
     *
     * @param gameEntity the game entity.
     * @return an optional containing the image icon if present, otherwise an empty optional.
     */
    private Optional<ImageIcon> getEntityImage(final GameEntity gameEntity) {
        final GameEntityType type = gameEntity.getGameEntityType();
        Optional<URL> imgURL = Optional.ofNullable(ClassLoader.getSystemResource(type.getSpritePath()));
        try {
            return Optional.of(new ImageIcon(imgURL.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Gets the image icon for the player.
     *
     * @param player the player.
     * @return an optional containing the image icon if present, otherwise an empty optional.
     */
    private Optional<ImageIcon> getPlayerImage(final Player player) {
        PlayerPathSelector pps = new PlayerPathSelector();
        Optional<URL> imgURL = Optional.ofNullable(ClassLoader.getSystemResource(pps.selectPath(player)));
        try {
            return Optional.of(new ImageIcon(imgURL.get()));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
