package supson.model.entity.impl.block;

import supson.model.entity.impl.AbstractGameEntity;
import supson.common.GameEntityType;
import supson.common.api.Pos2d;

/**
 * The SubTerrainImpl class represents une of the type of terrain block in the game.
 * It extends the AbstractGameEntity class and implements the BlockEntity interface.
 */
public final class SubTerrainImpl extends AbstractGameEntity {

    private static final int HEIGHT = 22;
    private static final int WIDTH = 1;

    private static final GameEntityType TYPE = GameEntityType.SUBTERRAIN;

    /**
     * The constructor of the SubTerrainImpl class.
     * @param pos the starting position of the subterrain
     */
    public SubTerrainImpl(final Pos2d pos) {
        super(pos, HEIGHT, WIDTH, TYPE);
    }
}
