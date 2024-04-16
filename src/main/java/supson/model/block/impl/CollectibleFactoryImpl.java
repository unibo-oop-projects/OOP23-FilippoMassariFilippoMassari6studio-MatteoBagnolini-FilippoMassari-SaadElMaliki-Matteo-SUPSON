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

            private final static int RING_VALUE = 100;

            @Override
            public void collect(final Player player) {
                player.setScore(player.getScore() + RING_VALUE);
            }

        };
    }

    @Override
    public Collectible createCollectibleLifeBoostPowerUp(Pos2d pos) {
        return new AbstractCollectibleImpl(pos, BlockType.LIFE_BOOST_POWER_UP) {

            private static final int LIFE_BOOST_VALUE = 1;

            @Override
            public void collect(final Player player) {
                player.setLife(player.getLife() + LIFE_BOOST_VALUE);
            }

        };
    }

    @Override
    public Collectible createCollectibleStrengthPowerUp(Pos2d pos) {
        return new AbstractCollectibleImpl(pos, BlockType.STRNGTH_BOOST_POWER_UP) {

            @Override
            public void collect(final Player player) {
                //TODO: Implement when setStatus is available
                throw new UnsupportedOperationException("Unimplemented method 'collect'");
            }

        };
    }

}
