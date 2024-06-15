package supson.model.entity.impl.block;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.entity.api.block.Trap;
import supson.model.entity.impl.common.AbstractGameEntity;
import supson.model.entity.impl.player.Player;

/**
 * Represents a damage trap in the game.
 * This trap reduces the player's life by a specified amount when activated.
 */
public final class DamageTrapImpl extends AbstractGameEntity implements Trap {

    private static final int HEIGHT = 1;
    private static final int WIDTH = 1;

    private static final int DAMAGE = 1;

    private static final GameEntityType TYPE = GameEntityType.DAMAGE_TRAP;

    /**
     * Constructs a new DamageTrapImpl object with the specified position.
     *
     * @param pos the position of the damage trap
     */
    public DamageTrapImpl(final Pos2d pos) {
        super(pos, HEIGHT, WIDTH, TYPE);
    }

    @Override
    public void activate(final Player player) {
        player.setLife(player.getLife() - DAMAGE);
    }

}
