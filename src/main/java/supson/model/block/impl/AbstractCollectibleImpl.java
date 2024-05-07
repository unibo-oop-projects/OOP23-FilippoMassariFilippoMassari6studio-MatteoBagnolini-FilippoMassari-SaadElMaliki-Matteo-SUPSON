package supson.model.block.impl;

import java.io.File;

import supson.common.api.Pos2d;
import supson.model.block.BlockType;
import supson.model.block.api.Collectible;

/**
 * An abstract implementation of the Collectible interface.
 * This class provides a base implementation for collectible blocks in the game.
 */
public abstract class AbstractCollectibleImpl extends TerrainImpl implements Collectible {

    /**
     * Constructs a new AbstractCollectibleImpl object with the specified position and block type.
     *
     * @param pos  the position of the collectible block
     * @param type the type of the collectible block
     * @param sprite the sprite of the collectible block
     */
    public AbstractCollectibleImpl(final Pos2d pos, final BlockType type, final File sprite) {
        super(pos, type);
        this.sprite = sprite;
    }

}
