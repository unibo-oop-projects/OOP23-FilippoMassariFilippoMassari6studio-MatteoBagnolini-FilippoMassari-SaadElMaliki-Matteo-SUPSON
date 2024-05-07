package supson.model.block.impl;

import supson.model.entity.impl.AbstractGameEntity;
import supson.common.api.Pos2d;
import supson.model.block.BlockType;

public final class TerrainImpl extends AbstractGameEntity {
    
        private static final int HEIGHT = 1;
        private static final int WIDTH = 1;

        private static final BlockType TYPE = BlockType.TERRAIN;

        /**
         * The constructor of the terrain class.
         * @param pos the starting positon of the terrain
         */
        public TerrainImpl(final Pos2d pos) {
            super(pos, HEIGHT, WIDTH, TYPE);
        }
}
