package supson.model.block.api;

/**
 * The Collectible interface represents a block entity that can be collected.
 * It extends the BlockEntity interface.
 */
public interface Collectible extends BlockEntity {

    /**
     * Collects the collectible item.
     */
    void collect();
}
