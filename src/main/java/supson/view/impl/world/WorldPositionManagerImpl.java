package supson.view.impl.world;

import javax.swing.JLabel;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.entity.api.GameEntity;
import supson.view.api.WorldPositionManager;

/**
 * Implementation of the {@link WorldPositionManager} interface.
 * This class manages the positioning of game entities on the game panel.
 */
public final class WorldPositionManagerImpl implements WorldPositionManager {

    private static final int CAMERA_RANGE = 21;
    private static final int DEFAULT_DIMENSION = 24;

    @Override
    public void setPosition(final JLabel label, final GameEntity gameEntity, final double playerX, final double playerY, 
                            final int centerX, final int centerY, final int mapWidth) {
        final Pos2d pos = gameEntity.getPosition();
        final int x = calculateXPosition(pos.x(), playerX, centerX, mapWidth);
        final int y = calculateYPosition(pos.y(), playerY, centerY, gameEntity);
        final int gameEntityHeight = DEFAULT_DIMENSION * gameEntity.getHeight();
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
        double referencePoint;
        if (playerX >= mapWidth - CAMERA_RANGE) {
            referencePoint = mapWidth - CAMERA_RANGE;
        } else if (playerX <= CAMERA_RANGE) {
            referencePoint = CAMERA_RANGE;
        } else {
            referencePoint = playerX;
        }
        return (int) Math.round(centerX + (entityX - referencePoint) * DEFAULT_DIMENSION);
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
        final int offSet = 6;
        final double relativeY = entityY - (offSet + playerY);
        final boolean isLongEntity = gameEntity.getGameEntityType().equals(GameEntityType.PLAYER)
                || gameEntity.getGameEntityType().equals(GameEntityType.ENEMY)
                || gameEntity.getGameEntityType().equals(GameEntityType.SUBTERRAIN)
                || gameEntity.getGameEntityType().equals(GameEntityType.FINISHLINE);
        final int entityHeight = isLongEntity ? DEFAULT_DIMENSION : DEFAULT_DIMENSION * gameEntity.getHeight();
        if (gameEntity.getGameEntityType().equals(GameEntityType.FINISHLINE)) {
            return (int) Math.round(centerY - relativeY * entityHeight - (DEFAULT_DIMENSION));
        }
        return (int) Math.round(centerY - relativeY * entityHeight - (isLongEntity ? DEFAULT_DIMENSION / 2 : 0));
    }

    @Override
    public int calculateLeftBoundary(final int playerX, final int mapWidth) {
        if (playerX >= mapWidth - CAMERA_RANGE) {
            return mapWidth - 2 * CAMERA_RANGE;
        }
        return Math.max(0, playerX - CAMERA_RANGE);
    }

    @Override
    public int calculateRightBoundary(final int playerX, final int mapWidth) {
        if (playerX <= CAMERA_RANGE) {
            return 2 * CAMERA_RANGE;
        }
        return Math.min(mapWidth, playerX + CAMERA_RANGE);
    }

    @Override
    public boolean isWithinCameraRange(final GameEntity gameEntity, final int leftBoundary, final int rightBoundary) {
        final double entityX = gameEntity.getPosition().x();
        return entityX >= leftBoundary && entityX <= rightBoundary;
    }
}
