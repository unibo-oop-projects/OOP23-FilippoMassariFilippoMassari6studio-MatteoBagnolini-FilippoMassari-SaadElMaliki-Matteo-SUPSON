package supson.model.entity.api.player;

import supson.model.entity.impl.player.PlayerState;

/**
 * This interface models a player manager.
 */
public interface PlayerManager {

    /**
     * This method set the state.
     * @param state the state of the player
     */
    void setState(PlayerState state);

    /**
     * This method return the new updated state of the player.
     * @return the updated state
     */
    PlayerState getUpdatedState();

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
    void stopOnOrizontal();

    /**
     * Stops the player to jump.
     */
    void stopOnVertical();

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
