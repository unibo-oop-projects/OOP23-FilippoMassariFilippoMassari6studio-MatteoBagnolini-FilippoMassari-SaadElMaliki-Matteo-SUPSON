package supson.model.world.api;

import java.util.List;
import java.util.Optional;

import supson.model.entity.api.GameEntity;
import supson.model.entity.api.block.TagBlockEntity;
import supson.model.entity.impl.moveable.enemy.Enemy;
import supson.model.entity.impl.moveable.player.Player;
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
     * @param filePath the path of the file containing the game world data.
     */
    void loadWorld(String filePath);

    /**
     * Resets the game world to its initial state.
     *
     * @param filePath the path of the file containing the game world data.
     */
    void reset(String filePath);

    /**
     * Updates every entity of the model based on time elapsed from last update.
     *
     * @param elapsed time elapsed from last update.
     */
    void updateGame(long elapsed);

    /**
     * Removes the specified block from the game world.
     *
     * @param block the block to be removed.
     */
    void removeBlock(TagBlockEntity block);

    /**
     * Removes the specified enemy from the game world.
     *
     * @param enemy the enemy to be removed.
     */
    void removeEnemy(Enemy enemy);

    /**
     * Returns a list of all the blocks in the game world.
     *
     * @return a list of BlockEntity objects representing the blocks.
     */
    List<TagBlockEntity> getBlocks();

    /**
     * Returns a list of all the enemies in the game world.
     *
     * @return a list of MoveableEntity objects representing the enemies.
     */
    List<Enemy> getEnemies();

    /**
     * Returns a list of all the entities in the game world.
     *
     * @return a list of GameEntity objects representing the entities.
     */
    List<GameEntity> getGameEntities();

    /**
     * Returns the position of the player in the game world.
     *
     * @return a Player object representing the player's position.
     */
    Player getPlayer();

    /**
     * Returns the hud of the current state of the game.
     * 
     * @return the hud of the current state of the game.
     */
    Hud getHud();

    /**
     * Returns the height of the map.
     * 
     * @return the height of the map.
     */
    Integer getMapWidth();

    /**
     * Sets the width of the map.
     * 
     * @param mapWidth the width of the map.
     */
    void setMapWidth(Optional<Integer> mapWidth);

    /**
     * Adds a new block to the world .
     *
     * @param block The block to add.
     */
    void addBlock(TagBlockEntity block);

    /**
     * Adds a new enemy to the world at the specified position.
     *
     * @param enemy The enemy to add.
     */
    void addEnemy(Enemy enemy);

    /**
     * Returns whether the game is over.
     * 
     * @return whether the game is over.
     */
    boolean isGameOver();

    /**
     * Set the game over state in the game.
     * 
     * @param gameOver the gameOver falg to set.
     */
    void setGameOver(boolean gameOver);

    /**
     * Returns whether the player has won the game.
     * 
     * @return whether the player has won the game.
     */
    Boolean isWon();

    /**
     * This method sets the player movement flags.
     * @param right bool representing true if player should move right
     * @param left bool representing true if player should move left
     * @param jump bool representing true if player should jump
     */
    void setPlayerFlags(boolean right, boolean left, boolean jump);
}
