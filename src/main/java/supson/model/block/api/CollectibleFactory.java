package supson.model.block.api;

import supson.common.api.Pos2d;

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
    Collectible createCollectibleRing(Pos2d pos);

    /**
     * Creates a collectible power-up object.
     *
     * @param pos the position of the collectible.
     * @return the created Collectible object representing a life boost power-up.
     */
    Collectible createCollectibleLifeBoostPowerUp(Pos2d pos);

    /**
     * Creates a collectible power-up object.
     *
     * @param pos the position of the collectible.
     * @return the created Collectible object representing a strength power-up.
     */
    Collectible createCollectibleStrengthPowerUp(Pos2d pos);
}
