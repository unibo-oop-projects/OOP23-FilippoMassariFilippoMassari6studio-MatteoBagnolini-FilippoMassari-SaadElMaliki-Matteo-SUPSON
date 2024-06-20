package supson.model.entity.api.moveable.player;

import supson.model.entity.impl.moveable.player.Player;
import supson.model.entity.impl.moveable.player.PlayerState;

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
     * @param player the player
     * @param flag bool value representing true if player should go right,
     * false otherwise
     */
    void moveRight(Player player, boolean flag);

    /**
     * Set the player to move left.
     * @param player the player
     * @param flag bool value representing true if player shoudl go left,
     * false otherwise
     */
    void moveLeft(Player player, boolean flag);

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
     * @param player the player
     * @param flag bool value representing true if player should jump,
     * false otherwise
     */
    void jump(Player player, boolean flag);

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
