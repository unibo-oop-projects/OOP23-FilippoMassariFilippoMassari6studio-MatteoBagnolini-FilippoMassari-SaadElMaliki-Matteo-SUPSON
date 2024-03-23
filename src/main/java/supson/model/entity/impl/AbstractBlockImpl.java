package supson.model.entity.impl;

import supson.common.api.BlockType;
import supson.model.entity.api.BlockEntity;

/**
 * This is the abstract class AbstractBlockImpl that extends AbstractGameEntity and implements the Block interface.
 * It represents a block in the game with a specific type defined by BlockType.
 */
public abstract class AbstractBlockImpl extends AbstractGameEntity implements BlockEntity {

    /**
     * The type of block, defined as a BlockType value.
     */
    private final BlockType blockType;

    /**
     * Constructor for the AbstractBlockImpl class.
     * 
     * @param blockType The type of block to create.
     */
    public AbstractBlockImpl(final BlockType blockType) {
        this.blockType = blockType;
    }

    @Override
    public final BlockType getBlockType() {
        return this.blockType;
    }

}


