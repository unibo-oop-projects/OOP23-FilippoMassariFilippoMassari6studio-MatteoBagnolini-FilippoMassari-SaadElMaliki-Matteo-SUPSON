package supson.model.block.api;

import supson.model.entity.api.GameEntity;
import supson.model.world.api.World;

/**
 * The FinishLine interface represents a block entity that signals the reaching of the current level ending.
 * It extends the GameEntity interface.
 */
public interface Finishline extends GameEntity {

    /**
     * Triggers the end of the current game when the player reaches the finishline.
     * 
     * @param world the game world which has to end
     */
    void endGame(World world);
}
