package supson.model.block.impl;

import supson.common.api.Pos2d;
import supson.model.block.BlockType;
import supson.model.block.api.Collectible;
import supson.model.block.api.CollectibleFactory;

public class CollectibleFactoryImpl implements CollectibleFactory{

    @Override
    public Collectible createCollectibleRing(Pos2d pos) {
        return new AbstractCollectibleImpl(pos, BlockType.RING) {
            
            @Override
            public void collect() {  // TODO : quando ci sarà l'hud potro implemntare
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'collect'");
            }
        
        };
    }

    @Override
    public Collectible createCollectiblePowerUp(Pos2d pos) {
        return new AbstractCollectibleImpl(pos, BlockType.POWER_UP) {
            
            @Override
            public void collect() {  // TODO : quando ci sarà l'hud potro implemntare
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'collect'");
            }
        
        };
    }

}
