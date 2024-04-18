package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import supson.model.block.api.BlockEntity;
import supson.model.entity.impl.Enemy;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.common.impl.Pos2dImpl;

/**
 * This class contains unit tests for the World class.
 */
public class WorldTest {

    private static final String FILE_PATH = "src\\resources\\world.txt";
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
     * It checks if the returned list of enemies is not null and empty.
     * This assumes that no enemies are loaded initially.
     */
    @Test
    void testGetEnemies() {
        List<Enemy> enemies = world.getEnemies();
        assertNotNull(enemies);
        assertTrue(enemies.isEmpty());
    }

    /**
     * Tests the getPlayerPosition() method of the World class.
     * It checks if the returned player position is not null and matches the expected initial position.
     */
    @Test
    void testPlayerPosition() {
        assertNotNull(world.getPlayerPosition());
        assertEquals(new Pos2dImpl(0, 0), world.getPlayerPosition());
    }

    /**
     * Tests the reset() method of the World class.
     * It checks if the world is reset properly by verifying that the lists of blocks and enemies are not empty,
     * and the player position is set to null.
     */
    @Test
    void testReset() {
        world.reset(FILE_PATH);
        assertNotNull(world.getBlocks());
        assertFalse(world.getBlocks().isEmpty());
        assertTrue(world.getEnemies().isEmpty());
        assertNull(world.getPlayerPosition());
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
        List<Enemy> enemies = world.getEnemies();
        int initialEnemyCount = enemies.size();
        if (initialEnemyCount > 0) {
            world.removeEnemy(enemies.get(0));
            assertEquals(initialEnemyCount - 1, world.getEnemies().size());
        }
    }

}

