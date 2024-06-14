package supson.model.world.api;

import java.util.List;
import java.util.Optional;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.block.api.BlockEntity;
import supson.model.block.api.Collectible;
import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.player.Player;
import supson.model.hud.api.Hud;

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
     * Updates every entity of the model based on time elapsed from last update.
     *
     * @param elapsed time elapsed from last update
     */
    void updateGame(long elapsed);

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
    void removeEnemy(Enemy enemy);

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
    List<Enemy> getEnemies();

    /**
     * Returns a list of all the entities in the game world.
     *
     * @return a list of GameEntity objects representing the entities
     */
    List<GameEntity> getGameEntities();

    /**
     * Returns the position of the player in the game world.
     *
     * @return a Player object representing the player's position
     */
    Player getPlayer();

    /**
     * Returns the hud of the current state of the game.
     * 
     * @return the hud of the current state of the game
     */
    Hud getHud();

    /**
     * Returns the height of the map.
     * 
     * @return the height of the map
     */
    Integer getMapWidth();

    /**
     * Sets the width of the map.
     * 
     * @param mapWidth the width of the map
     */
    void setMapWidth(Optional<Integer> mapWidth);

    /**
     * Adds a new block to the world at the specified position with the specified type.
     *
     * @param type The type of block to add.
     * @param pos  The position where the block should be added.
     */
    void addBlock(GameEntityType type, Pos2d pos);

    /**
     * Adds a new enemy to the world at the specified position.
     *
     * @param enemy The enemy to add.
     */
    void addEnemy(Enemy enemy);

    /**
     * Adds a collectible to the world at the specified position.
     * 
     * @param collectible The collectible to add.
     */
    void addCollectible(Collectible collectible);

    /**
     * Returns whether the game is over.
     * 
     * @return whether the game is over
     */
    boolean isGameOver();

    void setGameOver(boolean gameOver);
}
