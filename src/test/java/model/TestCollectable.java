package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.common.impl.Vect2dImpl;
import supson.model.entity.player.Player;
import supson.model.block.impl.CollectibleFactoryImpl;
import supson.model.block.api.Collectible;

/**
 * This class contains unit tests for the Collectible class.
 */
public class TestCollectable {

    private CollectibleFactoryImpl factory;
    private Player player;

    /**
     * Sets up the necessary objects for each test case.
     */
    @BeforeEach
    public void setUp() {
        factory = new CollectibleFactoryImpl();
        player = new Player(new Pos2dImpl(0,0), new Vect2dImpl(0, 0), 1);
    }

    /**
     * Tests the creation of a collectible ring.
     */
    @Test
    public void testCreateCollectibleRing() {
        Pos2d pos = new Pos2dImpl(0, 0);
        Collectible ring = factory.createCollectible(GameEntityType.RING, pos);

        assertNotNull(ring);
        assertEquals(GameEntityType.RING, ring.getGameEntityType());

        ring.collect(player);
        assertEquals(100, player.getScore());
    }

    /**
     * Tests the creation of a collectible life boost.
     */
    @Test
    public void testCreateCollectibleLifeBoost() {
        Pos2d pos = new Pos2dImpl(0, 0);
        Collectible lifeBoost = factory.createCollectible(GameEntityType.LIFE_BOOST_POWER_UP, pos);

        assertNotNull(lifeBoost);
        assertEquals(GameEntityType.LIFE_BOOST_POWER_UP, lifeBoost.getGameEntityType());

        lifeBoost.collect(player);
        assertEquals(2, player.getLife());
    }

    /**
     * Tests the creation of a collectible strength boost.
     */
    @Test
    public void testCreateCollectibleStrengthBoost() {
        Pos2d pos = new Pos2dImpl(0, 0);
        Collectible strengthBoost = factory.createCollectible(GameEntityType.STRNGTH_BOOST_POWER_UP, pos);

        assertNotNull(strengthBoost);
        assertEquals(GameEntityType.STRNGTH_BOOST_POWER_UP, strengthBoost.getGameEntityType());

        strengthBoost.collect(player);
        assertTrue(player.getState().isInvulnerable());
    }

    /**
     * Tests the creation of an invalid collectible.
     */
    @Test
    public void testCreateInvalidCollectible() {
        Pos2d pos = new Pos2dImpl(0, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createCollectible(GameEntityType.ENEMY, pos);
        });
    }
}
