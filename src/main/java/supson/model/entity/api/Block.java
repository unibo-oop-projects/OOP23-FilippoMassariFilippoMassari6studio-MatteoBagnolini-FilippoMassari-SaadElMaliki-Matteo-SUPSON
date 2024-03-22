package supson.model.entity.api;

import supson.common.api.BlockType;

/**
 * An interface representing a block in the game.
 */
public interface Block extends GameEntity {

    /**
     * Retrieves the type of the block.
     * 
     * @return the type of the block
     */
    BlockType getBlockType();
}
