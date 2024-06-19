package supson.model.entity.api.block.collectible;

import supson.model.entity.api.block.TagBlockEntity;
import supson.model.entity.impl.moveable.player.Player;

/**
 * The Collectible interface represents a block entity that can be collected.
 * It extends the BlockEntity interface.
 */
public interface Collectible extends TagBlockEntity {

    /**
     * Collects the collectible item.
     * 
     * @param player the player collecting the item
     */
    void collect(Player player);

}
