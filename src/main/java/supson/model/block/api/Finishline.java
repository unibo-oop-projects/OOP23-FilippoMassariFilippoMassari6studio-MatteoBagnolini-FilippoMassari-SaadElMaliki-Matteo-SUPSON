package supson.model.block.api;

import supson.model.entity.api.GameEntity;
import supson.model.world.api.World;

public interface Finishline extends GameEntity {
    void endGame(World world);
}
