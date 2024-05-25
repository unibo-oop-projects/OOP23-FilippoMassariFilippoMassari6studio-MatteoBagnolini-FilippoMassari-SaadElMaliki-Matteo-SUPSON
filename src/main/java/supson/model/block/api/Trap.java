package supson.model.block.api;

import supson.model.entity.player.Player;

/**
 * The Trap interface represents a block entity that can trap the player.
 * It extends the GameEntity interface.
 */
public interface Trap extends BlockEntity {

    /**
     * Activates the trap and affects the player.
     * 
     * @param player the player affected by the trap
     */
    void activate(Player player);
}
