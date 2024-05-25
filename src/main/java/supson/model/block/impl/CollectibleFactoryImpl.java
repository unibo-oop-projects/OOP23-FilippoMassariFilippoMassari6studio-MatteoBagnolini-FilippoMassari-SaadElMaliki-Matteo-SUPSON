package supson.model.block.impl;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.block.api.Collectible;
import supson.model.block.api.CollectibleFactory;
import supson.model.entity.player.Player;

/**
 * An implementation of the CollectibleFactory interface.
 * This class provides a factory for creating collectible objects.
 */
public final class CollectibleFactoryImpl implements CollectibleFactory {

    @Override
    public Collectible createCollectible(final GameEntityType type, final Pos2d pos) {
        switch (type) {
            case RING:
                return createCollectibleRing(pos);
            case LIFE_BOOST_POWER_UP:
                return createCollectibleLifeBoostPowerUp(pos);
            case STRNGTH_BOOST_POWER_UP:
                return createCollectibleStrengthPowerUp(pos);
            default:
                throw new IllegalArgumentException("Invalid collectible type");
        }
    }

    private Collectible createCollectibleRing(final Pos2d pos) {
        return new AbstractCollectibleImpl(pos, GameEntityType.RING) {

            private static final int RING_VALUE = 100;

            @Override
            public void collect(final Player player) {
                player.incrementScore(RING_VALUE);
            }
        };
    }

    private Collectible createCollectibleLifeBoostPowerUp(final Pos2d pos) {
        return new AbstractCollectibleImpl(pos, GameEntityType.LIFE_BOOST_POWER_UP) {

            private static final int LIFE_BOOST_VALUE = 1;

            @Override
            public void collect(final Player player) {
                player.incrementLife(LIFE_BOOST_VALUE);
            }
        };
    }

    private Collectible createCollectibleStrengthPowerUp(final Pos2d pos) {
        return new AbstractCollectibleImpl(pos, GameEntityType.STRNGTH_BOOST_POWER_UP) {

            @Override
            public void collect(final Player player) {
                player.setVulnerability(true);
            }
        };
    }
}
