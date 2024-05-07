package supson.model.block.impl;

import java.io.File;

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

    private static final File RING_SPRITE = new File("src/main/resources/ring.png");
    private static final File LIFE_BOOST_POWER_UP_SPRITE = new File("src/main/resources/life_boost_power_up.png");
    private static final File STRENGTH_POWER_UP_SPRITE = new File("src/main/resources/strength_power_up.png");

    @Override
    public Collectible createCollectibleRing(final Pos2d pos) {
        return new AbstractCollectibleImpl(pos, BlockType.RING, RING_SPRITE) {

            private static final int RING_VALUE = 100;

            @Override
            public void collect(final Player player) {
                player.setScore(player.getScore() + RING_VALUE);
            }

        };
    }

    @Override
    public Collectible createCollectibleLifeBoostPowerUp(final Pos2d pos) {
        return new AbstractCollectibleImpl(pos, BlockType.LIFE_BOOST_POWER_UP, LIFE_BOOST_POWER_UP_SPRITE) {

            private static final int LIFE_BOOST_VALUE = 1;

            @Override
            public void collect(final Player player) {
                player.setLife(player.getLife() + LIFE_BOOST_VALUE);
            }

        };
    }

    @Override
    public Collectible createCollectibleStrengthPowerUp(final Pos2d pos) {
        return new AbstractCollectibleImpl(pos, BlockType.STRNGTH_BOOST_POWER_UP, STRENGTH_POWER_UP_SPRITE) {

            @Override
            public void collect(final Player player) {
                //TODO : Implement when setStatus is available
                throw new UnsupportedOperationException("Unimplemented method 'collect'");
            }

        };
    }

}
