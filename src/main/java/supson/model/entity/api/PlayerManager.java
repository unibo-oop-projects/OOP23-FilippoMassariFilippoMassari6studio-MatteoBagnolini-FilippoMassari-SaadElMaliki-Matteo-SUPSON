package supson.model.entity.api;

public interface PlayerManager {

    /**
     * Set the player to move right.
     */
    void moveRight();

    /**
     * Set the player to move left.
     */
    void moveLeft();

    /**
     * Stops the player to move right or left.
     */
    void stop();

    /**
     * Sets the player to jump.
     */
    void jump();

    /**
     * 
     * @return true if the player is jumping, false otherwise
     */
    boolean isJumping();

    /**
     * 
     * @return true if the player is invulnerable, false otherwise
     */
    boolean isInvulnerable();

    /**
     * Sets the player to be invulnerable to enemies.
     */
    void setInvulnerable();

    /**
     * Sets the player to be vulnerable to enemies.
     */
    void setVulnerable();

}
