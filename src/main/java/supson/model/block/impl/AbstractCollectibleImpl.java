package supson.model.block.impl;

import supson.common.api.Pos2d;
import supson.model.block.BlockType;
import supson.model.block.api.Collectible;

/**
 * An abstract implementation of the Collectible interface.
 * This class provides a base implementation for collectible blocks in the game.
 */
public abstract class AbstractCollectibleImpl<T> extends BlockEntityImpl implements Collectible<T> {

    /**
     * Constructs a new AbstractCollectibleImpl object with the specified position and block type.
     *
     * @param pos  the position of the collectible block
     * @param type the type of the collectible block
     */
    public AbstractCollectibleImpl(final Pos2d pos, final BlockType type) {
        super(pos, type);
    }

}
