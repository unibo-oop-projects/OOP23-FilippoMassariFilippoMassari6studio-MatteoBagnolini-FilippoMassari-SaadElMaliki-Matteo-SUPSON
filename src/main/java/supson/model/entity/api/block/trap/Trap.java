package supson.model.entity.api.block.trap;

import supson.model.entity.api.block.TagBlockEntity;
import supson.model.entity.impl.moveable.player.Player;

/**
 * The Trap interface represents a block entity that can trap the player.
 * It extends the GameEntity interface.
 */
public interface Trap extends TagBlockEntity {

    /**
     * Activates the trap and affects the player.
     * 
     * @param player the player affected by the trap
     */
    void activate(Player player);
}
