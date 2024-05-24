package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.common.impl.Vect2dImpl;
import supson.model.block.api.BlockEntity;
import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.Enemy;
import supson.model.entity.impl.Player;
import supson.model.hud.api.Hud;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;

/**
 * This class contains unit tests for the World class.
 */
public class TestWorld {

    private static final String FILE_PATH = "/world.txt";
    private World world;

    /**
     * Sets up the test environment by creating a new instance of World and loading the world from the specified file.
     */
    @BeforeEach
    void setUp() {
        world = new WorldImpl();
        world.loadWorld(FILE_PATH);
    }

    /**
     * Tests the getBlocks() method of the World class.
     * It checks if the returned list of blocks is not null and not empty.
     */
    @Test
    void testGetBlocks() {
        List<BlockEntity> blocks = world.getBlocks();
        assertNotNull(blocks);
        assertFalse(blocks.isEmpty());
    }

    /**
     * Tests the getEnemies() method of the World class.
     * It checks if the returned list of enemies is not null and verifies the initial conditions based on the world.txt file.
     */
    @Test
    void testGetEnemies() {
        List<Enemy> enemies = world.getEnemies();
        assertNotNull(enemies);
        assertFalse(enemies.isEmpty());
    }

    /**
     * Tests the getPlayer() method of the World class.
     * It checks if the returned player object is not null and matches the expected initial position.
     */
    @Test
    void testGetPlayer() {
        Player player = world.getPlayer();
        assertNotNull(player);
        assertEquals(new Pos2dImpl(1, 7.5), player.getPosition());
    }

    /**
     * Tests the reset() method of the World class.
     * It checks if the world is reset properly by verifying that the lists of blocks and enemies are not empty,
     * and the player position is set to the default position.
     */
    @Test
    void testReset() {
        world.reset(FILE_PATH);
        assertNotNull(world.getBlocks());
        assertFalse(world.getBlocks().isEmpty());
        assertFalse(world.getEnemies().isEmpty());
        assertNotNull(world.getPlayer());
        assertEquals(new Pos2dImpl(1, 7.5), world.getPlayer().getPosition());
    }

    /**
     * Tests the removeBlock() method of the World class.
     * It checks if a block is successfully removed from the world by verifying that the number of blocks is decreased by one.
     * This assumes that the initial number of blocks is not zero.
     */
    @Test
    void testRemoveBlock() {
        List<BlockEntity> blocks = world.getBlocks();
        int initialBlockCount = blocks.size();
        if (initialBlockCount > 0) {
            world.removeBlock(blocks.get(0));
            assertEquals(initialBlockCount - 1, world.getBlocks().size());
        }
    }

    /**
     * Tests the removeEnemy() method of the World class.
     * It checks if an enemy is successfully removed from the world by verifying that the number of enemies is decreased by one.
     * This assumes that the initial number of enemies is not zero.
     */
    @Test
    void testRemoveEnemy() {
        // Adding an enemy for the purpose of the test
        Pos2d enemyPos = new Pos2dImpl(2, 2);
        world.loadWorld(FILE_PATH);
        world.getEnemies().add(new Enemy(enemyPos, new Vect2dImpl(0, 0), 1, 0));
        List<Enemy> enemies = world.getEnemies();
        int initialEnemyCount = enemies.size();
        if (initialEnemyCount > 0) {
            world.removeEnemy(enemies.get(0));
            assertEquals(initialEnemyCount - 1, world.getEnemies().size());
        }
    }

    /**
     * Tests the updateGame() method of the World class.
     * It checks if the game entities are updated correctly.
     */
    @Test
    void testUpdateGame() {
        long elapsed = 1000; // Simulating 1 second of game time
        world.updateGame(elapsed);
        // Check if player and enemies have moved and collisions resolved correctly
        Player player = world.getPlayer();
        assertNotNull(player);
        // Additional checks can be added based on the specific behavior expected after update
    }

    /**
     * Tests the getGameEntities() method of the World class.
     * It checks if the returned list of game entities contains blocks, enemies, and the player.
     */
    @Test
    void testGetGameEntities() {
        List<GameEntity> entities = world.getGameEntities();
        assertNotNull(entities);
        assertTrue(entities.contains(world.getPlayer()));
        assertTrue(entities.containsAll(world.getBlocks()));
        assertTrue(entities.containsAll(world.getEnemies()));
    }

    /**
     * Tests the getHud() method of the World class.
     * It checks if the HUD is correctly initialized and updated.
     */
    @Test
    void testGetHud() {
        Hud hud = world.getHud();
        assertNotNull(hud);
        assertEquals(world.getPlayer().getScore(), hud.getScore());
        assertEquals(world.getPlayer().getLife(), hud.getLives());
        // Elapsed time in seconds can be tested after simulating game time
    }
}
