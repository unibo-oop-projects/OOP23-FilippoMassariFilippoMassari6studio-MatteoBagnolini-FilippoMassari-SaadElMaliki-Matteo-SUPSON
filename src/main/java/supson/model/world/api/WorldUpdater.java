package supson.model.world.api;

import supson.model.entity.api.moveable.player.PlayerManager;

/**
 * The WorldUpdater interface provides a method to update the game world.
 */
public interface WorldUpdater {

    /**
     * Updates every entity of the model based on time elapsed from the last update.
     *
     * @param world the world to be updated.
     * @param elapsed time elapsed since the last update.
     */
    void updateWorld(World world, long elapsed, PlayerManager playerManager);
}
