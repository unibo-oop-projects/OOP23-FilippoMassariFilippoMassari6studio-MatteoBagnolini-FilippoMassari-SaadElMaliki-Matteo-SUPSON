package supson.model.entity.api.block.trap;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;

/**
 * The TrapFactory interface provides a factory for creating trap objects.
 */
public interface TrapFactory {

    /**
     * Creates a trap of the specified type at the given position.
     *
     * @param type the type of the trap
     * @param pos the position of the trap
     * @return the created trap
     */
    Trap createTrap(GameEntityType type, Pos2d pos);
}
