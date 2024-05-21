package supson.model.block.impl;

import supson.model.entity.impl.AbstractGameEntity;
import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.block.api.BlockEntity;

/**
 * The TerrainImpl class represents a terrain block in the game.
 * It extends the AbstractGameEntity class and implements the BlockEntity interface.
 */
public final class TerrainImpl extends AbstractGameEntity implements BlockEntity {

    private static final int HEIGHT = 1;
    private static final int WIDTH = 1;

    private static final GameEntityType TYPE = GameEntityType.TERRAIN;

    /**
     * The constructor of the TerrainImpl class.
     * @param pos the starting position of the terrain
     */
    public TerrainImpl(final Pos2d pos) {
        super(pos, HEIGHT, WIDTH, TYPE);
    }
}
