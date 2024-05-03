package supson.model.block.impl;

import supson.common.api.Pos2d;
import supson.model.block.BlockType;
import supson.model.entity.impl.Player;

public class TrapImpl extends TerrainImpl {

    private static final int DAMAGE = 2;

    public TrapImpl(Pos2d pos, BlockType blockType) {
        super(pos, blockType);
    }

    public void activate(Player player) {
        player.setLife(player.getLife() - DAMAGE);
    }

}
