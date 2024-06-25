package supson.model.entity.impl.block.trap;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.entity.api.block.trap.Trap;
import supson.model.entity.impl.AbstractGameEntity;

/**
 * Represents a damage trap in the game.
 * This trap reduces the player's life by a specified amount when activated.
 */
public abstract class AbstractTrap extends AbstractGameEntity implements Trap {

    private static final int HEIGHT = 1;
    private static final int WIDTH = 1;

    /**
     * Constructs a new AbstractTrap object with the specified position and trap type.
     * 
     * @param pos the position of the trap
     * @param type the type of the trap
     */
    public AbstractTrap(final Pos2d pos, final GameEntityType type) {
        super(pos, HEIGHT, WIDTH, type);
    }
}
