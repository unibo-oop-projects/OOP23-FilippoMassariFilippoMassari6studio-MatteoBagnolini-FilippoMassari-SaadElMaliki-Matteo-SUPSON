package supson.view.api;

import javax.swing.JLabel;

import supson.model.entity.api.GameEntity;

/**
 * Manages the positioning of game entities in the world.
 */
public interface WorldPositionManager {

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
    void setPosition(JLabel label, GameEntity gameEntity, double playerX, double playerY,
            int centerX, int centerY, int mapWidth);

    /**
     * Calculates the left boundary of the camera view.
     *
     * @param playerX the X position of the player.
     * @param mapWidth the width of the map.
     * @return the left boundary.
     */
    int calculateLeftBoundary(int playerX, int mapWidth);

    /**
     * Calculates the right boundary of the camera view.
     *
     * @param playerX the X position of the player.
     * @param mapWidth the width of the map.
     * @return the right boundary.
     */
    int calculateRightBoundary(int playerX, int mapWidth);

    /**
     * Checks if a game entity is within the camera range.
     *
     * @param gameEntity the game entity to check.
     * @param leftBoundary the left boundary of the camera view.
     * @param rightBoundary the right boundary of the camera view.
     * @return true if the game entity is within the camera range, false otherwise.
     */
    boolean isWithinCameraRange(GameEntity gameEntity, int leftBoundary, int rightBoundary);

}
