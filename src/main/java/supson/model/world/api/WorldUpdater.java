package supson.model.world.api;

import supson.model.entity.impl.moveable.player.Player;

/**
 * The WorldUpdater interface provides methods to update the game world and control the player.
 */
public interface WorldUpdater {

    /**
     * Updates every entity of the model based on time elapsed from last update.
     *
     * @param world the world to update
     * @param elapsed time elapsed from last update.
     */
    void updateGame(World world, long elapsed);

    /**
     * This method sets the player movement flags.
     * 
     * @param world the world to update
     * @param right bool representing true if player should move right
     * @param left bool representing true if player should move left
     * @param jump bool representing true if player should jump
     */
    void setPlayerFlags(Player player, boolean right, boolean left, boolean jump);
}
