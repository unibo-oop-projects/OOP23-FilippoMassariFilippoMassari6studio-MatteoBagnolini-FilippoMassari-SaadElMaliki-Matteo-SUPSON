package supson.model.entity.impl;

import supson.common.api.BlockType;
import supson.model.hitbox.api.Hitbox;
import supson.model.hitbox.impl.RectHitbox;

/**
 * The TerrainImpl class extends the AbstractBlockImpl class and represents a terrain block in the game.
 * It has a specific block type and a rectangular hitbox.
 */
public class TerrainImpl extends AbstractBlockImpl {

    /**
     * The side length of the terrain block.
     */
    private static final int SIDE = 10;

    /**
     * The hitbox of the terrain block.
     */
    private final Hitbox hb;

    /**
     * Constructor for the TerrainImpl class.
     * 
     * @param blockType The type of block to create.
     */
    public TerrainImpl(final BlockType blockType) {
        super(blockType);
        this.hb = new RectHitbox(this.getPosition(), SIDE, SIDE);
    }
 
}
