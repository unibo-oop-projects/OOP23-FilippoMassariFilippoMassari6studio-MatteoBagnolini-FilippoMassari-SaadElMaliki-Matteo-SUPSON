package supson.model.block.impl;

import java.io.File;

import supson.common.api.Pos2d;
import supson.model.block.BlockType;
import supson.model.block.api.BlockEntity;
import supson.model.entity.impl.AbstractGameEntity;

/**
 * This is the abstract class AbstractBlockImpl that extends AbstractGameEntity and implements the Block interface.
 * It represents a block in the game with a specific type defined by BlockType.
 */
public class TerrainImpl extends AbstractGameEntity implements BlockEntity {

    private static final File SPRITE = new File("src/main/resources/terrain.png");

    private static final int HEIGHT = 2;
    private static final int WIDTH = 2;

    /**
     * The type of block, defined as a BlockType value.
     */
    private final BlockType blockType;

    /**
     * Constructor for the AbstractBlockImpl class.
     * 
     * @param pos The position of the block to create.
     * @param blockType The type of block to create.
     */
    public TerrainImpl(final Pos2d pos, final BlockType blockType) {
        super(pos, HEIGHT, WIDTH);
        this.blockType = blockType;
        this.sprite = SPRITE;
    }

    @Override
    public final BlockType getBlockType() {
        return this.blockType;
    }

}


