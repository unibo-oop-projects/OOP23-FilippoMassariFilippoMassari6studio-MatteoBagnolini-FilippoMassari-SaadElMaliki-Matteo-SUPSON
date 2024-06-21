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
     * Sets the player to jump.
     * @param player the player
     * @param flag bool value representing true if player should jump,
     * false otherwise
     */
    void jump(Player player, boolean flag);

}
