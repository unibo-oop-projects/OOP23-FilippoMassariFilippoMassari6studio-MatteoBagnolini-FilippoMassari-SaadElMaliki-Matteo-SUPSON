package supson.model.world.api;

import supson.common.api.Pos2d;
import supson.model.block.api.BlockEntity;
import supson.model.entity.api.MoveableEntity;

/**
 * The World interface represents a game world.
 * It provides methods to load, create, destroy, and reset the world.
 * It also provides methods to add and remove various entities such as rings, blocks, players, and enemies.
 */
public interface World {

    /**
     * Loads the world from the specified file path.
     *
     * @param filePath The path of the file containing the world data.
     */
    void loadWorld(String filePath);

    /**
     * Resets the world to its initial state.
     */
    void reset();

    /**
     * Adds a ring to the world at the specified position.
     *
     * @param pos The position where the ring should be added.
     */
    void addRing(Pos2d pos);

    /**
     * Removes a ring from the world.
     *
     * @param ring The ring to be removed.
     */
    void removeRing(BlockEntity ring);

    /**
     * Adds a power-up to the world at the specified position.
     *
     * @param pos The position where the power-up should be added.
     */
    void addPowerUp(Pos2d pos);

    /**
     * Removes a power-up from the world.
     *
     * @param powerUp The power-up to be removed.
     */
    void removePowerUp(BlockEntity powerUp);

    /**
     * Adds a trap to the world at the specified position.
     *
     * @param pos The position where the trap should be added.
     */
    void addTrap(Pos2d pos);

    /**
     * Removes a trap from the world.
     *
     * @param trap The trap to be removed.
     */
    void removeTrap(BlockEntity trap);

    /**
     * Adds a block to the world at the specified position.
     *
     * @param pos The position where the block should be added.
     */
    void addTerrain(Pos2d pos);

    /**
     * Removes a block from the world.
     *
     * @param terrain The block to be removed.
     */
    void removeTerrain(BlockEntity terrain);

    /**
     * Adds an enemy to the world at the specified position.
     *
     * @param pos The position where the enemy should be added.
     */
    void addEnemy(Pos2d pos);

    /**
     * Removes an enemy from the world.
     *
     * @param enemy The enemy to be removed.
     */
    void removeEnemy(MoveableEntity enemy);

    /**
     * Adds a player to the world at the specified position.
     *
     * @param pos The position where the player should be added.
     */
    void addPlayer(Pos2d pos);
}
