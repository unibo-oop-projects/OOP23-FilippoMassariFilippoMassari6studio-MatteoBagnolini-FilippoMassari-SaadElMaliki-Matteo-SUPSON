package supson.model.block.api;

import supson.model.entity.api.GameEntity;

/**
 * An interface representing a block in the game.
 */
public interface Block extends GameEntity {

    /**
     * Retrieves the type of the block.
     * 
     * @return the type of the block
     */
    public BlockType getBlockType();
}