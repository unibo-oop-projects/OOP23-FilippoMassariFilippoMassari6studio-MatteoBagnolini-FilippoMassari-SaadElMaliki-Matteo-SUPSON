package supson.model.block.api;

import supson.model.world.api.World;

public interface Finishline extends BlockEntity{
    void endGame(World world);
}
