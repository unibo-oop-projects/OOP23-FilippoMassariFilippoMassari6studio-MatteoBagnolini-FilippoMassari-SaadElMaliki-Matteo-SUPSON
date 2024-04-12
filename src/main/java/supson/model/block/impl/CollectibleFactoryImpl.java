package supson.model.block.impl;

import supson.common.api.Pos2d;
import supson.model.block.BlockType;
import supson.model.block.api.Collectible;
import supson.model.block.api.CollectibleFactory;
import supson.model.entity.impl.Player;

/**
 * An implementation of the CollectibleFactory interface.
 * This class provides a factory for creating collectible objects.
 */
public final class CollectibleFactoryImpl implements CollectibleFactory {

    @Override
    public Collectible<Object> createCollectibleRing(final Pos2d pos) {
        return new AbstractCollectibleImpl<Object>(pos, BlockType.RING) {

            @Override
            public void collect(Object obj) {  // TODO : quando ci sarà l'hud potro implemntare
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'collect'");
            }

        };
    }

    @Override
    public Collectible<Player> createCollectiblePowerUp(final Pos2d pos) {
        return new AbstractCollectibleImpl<Player>(pos, BlockType.POWER_UP) {

            @Override
            public void collect(Player player) {  // TODO : quando ci sarà l'hud potro implemntare
                // todo: implementare quado ci sarè una set stato di sonic
            }

        };
    }
    
}
