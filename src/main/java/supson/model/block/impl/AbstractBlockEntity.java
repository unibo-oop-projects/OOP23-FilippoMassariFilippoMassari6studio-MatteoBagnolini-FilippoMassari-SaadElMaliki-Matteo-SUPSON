package supson.model.block.impl;

import supson.model.block.BlockType;
import supson.model.block.api.BlockEntity;
import supson.model.entity.impl.AbstractGameEntity;

/**
 * This is the abstract class AbstractBlockImpl that extends AbstractGameEntity and implements the Block interface.
 * It represents a block in the game with a specific type defined by BlockType.
 */
public abstract class AbstractBlockEntity extends AbstractGameEntity implements BlockEntity {

    /**
     * The type of block, defined as a BlockType value.
     */
    private final BlockType blockType;

    /**
     * Constructor for the AbstractBlockImpl class.
     * 
     * @param blockType The type of block to create.
     */
    public AbstractBlockEntity(final BlockType blockType) {
        this.blockType = blockType;
    }

    @Override
    public final BlockType getBlockType() {
        return this.blockType;
    }

}


