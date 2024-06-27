package supson.model.entity.impl.block.collectible;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.effect.api.TimedEffectFactory;
import supson.model.effect.impl.TimedEffectFactoryImpl;
import supson.model.entity.api.block.collectible.Collectible;
import supson.model.entity.api.block.collectible.CollectibleFactory;
import supson.model.entity.impl.moveable.player.Player;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * An implementation of the CollectibleFactory interface.
 * This class provides a factory for creating collectible objects.
 */
public final class CollectibleFactoryImpl implements CollectibleFactory {

    private final Map<GameEntityType, Function<Pos2d, Collectible>> collectibleCreators;
    private final TimedEffectFactory timedEffectFactory = new TimedEffectFactoryImpl();
    private final Object lock = new Object();

    /**
     * Constructs a new instance of the CollectibleFactoryImpl class.
     * This factory is responsible for creating different types of collectibles in the game.
     * 
     * The constructor initializes an EnumMap with GameEntityType as the key.
     */
    public CollectibleFactoryImpl() {
        collectibleCreators = new EnumMap<>(GameEntityType.class);
        collectibleCreators.put(GameEntityType.RING, this::createCollectibleRing);
        collectibleCreators.put(GameEntityType.LIFE_BOOST_POWER_UP, this::createCollectibleLifeBoostPowerUp);
        collectibleCreators.put(GameEntityType.STRNGTH_BOOST_POWER_UP, this::createCollectibleStrengthPowerUp);
    }

    @Override
    public Collectible createCollectible(final GameEntityType type, final Pos2d pos) {
        final Optional<Function<Pos2d, Collectible>> creator = Optional.ofNullable(collectibleCreators.get(type));
        if (creator.isPresent()) {
            return creator.get().apply(pos);
        } else {
            throw new IllegalArgumentException("Invalid collectible type: " + type);
        }
    }

    /**
     * Creates a ring collectible at the specified position.
     * 
     * @param pos the position of the collectible
     * @return a new ring collectible
     */
    private Collectible createCollectibleRing(final Pos2d pos) {
        return new AbstractCollectible(pos, GameEntityType.RING) {

            private static final int RING_VALUE = 100;

            @Override
            public void collect(final Player player) {
                player.incrementScore(RING_VALUE);
            }
        };
    }

    /**
     * Creates a life boost power-up collectible at the specified position.
     * 
     * @param pos the position of the collectible
     * @return a new life boost power-up collectible
     */
    private Collectible createCollectibleLifeBoostPowerUp(final Pos2d pos) {
        return new AbstractCollectible(pos, GameEntityType.LIFE_BOOST_POWER_UP) {

            private static final int LIFE_BOOST_VALUE = 1;

            @Override
            public void collect(final Player player) {
                player.incrementLife(LIFE_BOOST_VALUE);
            }
        };
    }

    /**
     * Creates a strength power-up collectible at the specified position.
     * 
     * @param pos the position of the collectible
     * @return a new strength power-up collectible
     */
    private Collectible createCollectibleStrengthPowerUp(final Pos2d pos) {
        return new AbstractCollectible(pos, GameEntityType.STRNGTH_BOOST_POWER_UP) {

            @Override
            public void collect(final Player player) {
                final Thread timer = new Thread(timedEffectFactory.createEffect(GameEntityType.STRNGTH_BOOST_POWER_UP,
                                                                                player, lock));
                timer.start();
            }
        };
    }
}
