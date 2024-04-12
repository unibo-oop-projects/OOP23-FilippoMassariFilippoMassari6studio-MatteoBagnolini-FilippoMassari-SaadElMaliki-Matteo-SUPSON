package supson.model.block.api;

/**
 * The Collectible interface represents a block entity that can be collected.
 * It extends the BlockEntity interface.
 *
 * @param <T> the type of the collectible item
 */
public interface Collectible<T> extends BlockEntity {

    /**
     * Collects the collectible item.
     * 
     * @param item the specific item to collect
     */
    void collect(T item);

}
