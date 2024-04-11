package supson.model.block.impl;

import supson.common.api.Pos2d;
import supson.model.block.BlockType;
import supson.model.block.api.Collectible;

public abstract class AbstractCollectibleImpl extends BlockEntityImpl implements Collectible{

    public AbstractCollectibleImpl(final Pos2d pos, final BlockType type) {
        super(pos, type);
    }

}
