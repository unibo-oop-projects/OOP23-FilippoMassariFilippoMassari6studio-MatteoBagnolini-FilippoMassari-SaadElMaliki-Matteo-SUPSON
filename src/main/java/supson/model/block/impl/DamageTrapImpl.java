package supson.model.block.impl;

import supson.common.api.Pos2d;
import supson.model.block.BlockType;
import supson.model.block.api.Trap;
import supson.model.entity.impl.AbstractGameEntity;
import supson.model.entity.impl.Player;

public class DamageTrapImpl extends AbstractGameEntity implements Trap{

    private static final int HEIGHT = 1;
    private static final int WIDTH = 1;

    private static final BlockType TYPE = BlockType.DAMAGE_TRAP;

    private static final int DAMAGE = 1;

    /**
     * Constructs a new DamageTrapImpl object with the specified position.
     *
     * @param pos the position of the damage trap
     */
    public DamageTrapImpl(final Pos2d pos) {
        super(pos, HEIGHT, WIDTH, TYPE);
    }

    @Override
    public void activate(Player player) {
        player.setLife(player.getLife() - DAMAGE);
    }

}
