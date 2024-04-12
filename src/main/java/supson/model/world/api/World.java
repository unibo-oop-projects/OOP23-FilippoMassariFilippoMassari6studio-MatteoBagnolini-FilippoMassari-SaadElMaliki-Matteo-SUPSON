package supson.model.world.api;

import java.util.List;

import supson.common.api.Pos2d;
import supson.model.block.api.BlockEntity;
import supson.model.entity.api.MoveableEntity;

/**
 * The World interface represents a game world.
 * It provides methods to load the world, reset it, remove blocks and enemies,
 * and retrieve information about the blocks, enemies, and player position.
 */
public interface World {

    /**
     * Loads the game world from the specified file.
     *
     * @param filePath the path of the file containing the game world data
     */
    void loadWorld(String filePath);

    /**
     * Resets the game world to its initial state.
     *
     * @param filePath the path of the file containing the game world data
     */
    void reset(String filePath);

    /**
     * Removes the specified block from the game world.
     *
     * @param block the block to be removed
     */
    void removeBlock(BlockEntity block);

    /**
     * Removes the specified enemy from the game world.
     *
     * @param enemy the enemy to be removed
     */
    void removeEnemy(MoveableEntity enemy);

    /**
     * Returns a list of all the blocks in the game world.
     *
     * @return a list of BlockEntity objects representing the blocks
     */
    List<BlockEntity> getBlocks();

    /**
     * Returns a list of all the enemies in the game world.
     *
     * @return a list of MoveableEntity objects representing the enemies
     */
    List<MoveableEntity> getEnemies();

    /**
     * Returns the position of the player in the game world.
     *
     * @return a Pos2d object representing the player's position
     */
    Pos2d getPlayerPosition();
}