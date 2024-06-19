package supson.model.entity.impl.block;

import supson.model.entity.api.block.TagBlockEntity;
import supson.model.entity.impl.AbstractGameEntity;
import supson.common.GameEntityType;
import supson.common.api.Pos2d;

/**
 * The TerrainImpl class represents a terrain block in the game.
 * It extends the AbstractGameEntity class and implements TagBlockEntity tag interface.
 */
public final class TerrainImpl extends AbstractGameEntity implements TagBlockEntity {

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
