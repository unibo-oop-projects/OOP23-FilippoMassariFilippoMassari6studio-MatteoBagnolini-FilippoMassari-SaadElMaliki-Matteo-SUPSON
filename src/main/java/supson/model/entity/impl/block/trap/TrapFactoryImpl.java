package supson.model.entity.impl.block.trap;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.entity.api.block.trap.Trap;
import supson.model.entity.api.block.trap.TrapFactory;
import supson.model.entity.impl.moveable.player.Player;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * An implementation of the TrapFactory interface.
 * This class provides a factory for creating trap objects.
 */
public final class TrapFactoryImpl implements TrapFactory {

    private final Map<GameEntityType, Function<Pos2d, Trap>> trapCreators;

    /**
     * Constructs a new instance of the TrapFactoryImpl class.
     * This factory is responsible for creating different types of traps in the game.
     * 
     * The constructor initializes an EnumMap with GameEntityType as the key.
     */
    public TrapFactoryImpl() {
        trapCreators = new EnumMap<>(GameEntityType.class);
        trapCreators.put(GameEntityType.DAMAGE_TRAP, this::createDamageTrap);
    }

    @Override
    public Trap createTrap(final GameEntityType type, final Pos2d pos) {
        final Optional<Function<Pos2d, Trap>> creator = Optional.ofNullable(trapCreators.get(type));
        if (creator.isPresent()) {
            return creator.get().apply(pos);
        } else {
            throw new IllegalArgumentException("Invalid trap type: " + type);
        }
    }

    /**
     * Creates a damage trap at the specified position.
     * 
     * @param pos the position of the trap
     * @return a new damage trap
     */
    private Trap createDamageTrap(final Pos2d pos) {
        return new AbstractTrap(pos, GameEntityType.DAMAGE_TRAP) {

            private static final int DAMAGE = 1;
            
            @Override
            public void activate(final Player player) {
                player.setLife(player.getLife() - DAMAGE);
            }
        };
    }
}
