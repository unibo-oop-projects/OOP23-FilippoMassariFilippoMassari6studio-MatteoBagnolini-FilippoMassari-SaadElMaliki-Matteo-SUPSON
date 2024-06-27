package supson.model.effect.impl;

import supson.common.GameEntityType;
import supson.model.effect.api.CollectibleEffect;
import supson.model.effect.api.TimedEffectFactory;
import supson.model.entity.impl.moveable.player.Player;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

/**
 * An implementation of the TimedEffectFactory interface.
 * This class provides a factory for creating timed collectible effects.
 */
public final class TimedEffectFactoryImpl implements TimedEffectFactory {

    private final Map<GameEntityType, BiFunction<Player, Object, CollectibleEffect>> effectCreators;

    /**
     * Constructs a new instance of the TimedEffectFactoryImpl class.
     * This factory is responsible for creating different types of timed effects in the game.
     * 
     * The constructor initializes an EnumMap with GameEntityType as the key.
     */
    public TimedEffectFactoryImpl() {
        effectCreators = new EnumMap<>(GameEntityType.class);
        effectCreators.put(GameEntityType.STRNGTH_BOOST_POWER_UP, this::createStrengthPowerUpEffect);
    }

    @Override
    public CollectibleEffect createEffect(final GameEntityType type, final Player player, final Object lock) {
        final Optional<BiFunction<Player, Object, CollectibleEffect>> creator = Optional.ofNullable(effectCreators.get(type));
        if (creator.isPresent()) {
            return creator.get().apply(player, lock);
        } else {
            throw new IllegalArgumentException("Invalid effect type: " + type);
        }
    }

    /**
     * Creates a strength power-up effect.
     * 
     * @param player the player affected by the effect
     * @param lock the lock object for synchronization
     * @return a new strength power-up effect
     */
    private CollectibleEffect createStrengthPowerUpEffect(final Player player, final Object lock) {

        final int duration = 5;

        return new AbstractCollectibleEffect(duration, player, lock) {
        
            @Override
            public void activateEffect(final Player player) {
                player.setState(player.getState().setInvulnerable());
            }

            @Override
            public void terminateEffect(final Player player) {
                player.setState(player.getState().setNotInvulnerable());
            }
        };
    }
}
