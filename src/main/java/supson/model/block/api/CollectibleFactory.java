package supson.model.block.api;

import supson.common.api.Pos2d;
import supson.model.entity.impl.Player;

/**
 * The CollectibleFactory interface represents a factory for creating collectible objects.
 */
public interface CollectibleFactory {

    /**
     * Creates a collectible ring object.
     *
     * @param pos the position of the collectible.
     * @return the created Collectible object representing a ring.
     */
    Collectible<Object> createCollectibleRing(Pos2d pos); // TODO: Implement when HUD is available

    /**
     * Creates a collectible power-up object.
     *
     * @param pos the position of the collectible.
     * @return the created Collectible object representing a power-up.
     */
    Collectible<Player> createCollectiblePowerUp(Pos2d pos);
}
