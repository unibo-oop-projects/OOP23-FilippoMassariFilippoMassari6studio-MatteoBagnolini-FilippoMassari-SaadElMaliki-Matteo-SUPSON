package supson.model.block.api;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Player;

/**
 * The Collectible interface represents a block entity that can be collected.
 * It extends the BlockEntity interface.
 */
public interface Collectible extends GameEntity {

    /**
     * Collects the collectible item.
     * 
     * @param player the player collecting the item
     */
    void collect(Player player);

}
