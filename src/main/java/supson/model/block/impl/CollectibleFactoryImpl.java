package supson.model.block.impl;

import supson.common.api.Pos2d;
import supson.model.block.BlockType;
import supson.model.block.api.Collectible;
import supson.model.block.api.CollectibleFactory;
import supson.model.entity.impl.Player;

/**
 * An implementation of the CollectibleFactory interface.
 * This class provides a factory for creating collectible objects.
 */
public final class CollectibleFactoryImpl implements CollectibleFactory {

    @Override
    public Collectible createCollectibleRing(final Pos2d pos) {
        return new AbstractCollectibleImpl(pos, BlockType.RING) {

            @Override
            public void collect(final Player player) {
                // TODO: implemtare quando sonic agvra setPoints
                throw new UnsupportedOperationException("Unimplemented method 'collect'");
            }

        };
    }

    @Override
    public Collectible createCollectiblePowerUp(final Pos2d pos) {
        return new AbstractCollectibleImpl(pos, BlockType.POWER_UP) {

            @Override
            public void collect(final Player player) {
                // TODO: Implement when setStatus is available
                throw new UnsupportedOperationException("Unimplemented method 'collect'");
            }

        };
    }

}
