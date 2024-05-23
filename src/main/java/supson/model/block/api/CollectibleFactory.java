package supson.model.block.api;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;

/**
 * The CollectibleFactory interface represents a factory for creating collectible objects.
 */
public interface CollectibleFactory {

    /**
     * Creates a collectible object.
     *
     * @param type the type of the collectible.
     * @param pos the position of the collectible.
     * @return the created Collectible object.
     */
    Collectible createCollectible(GameEntityType type, Pos2d pos);

}
