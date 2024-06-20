package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.model.entity.api.block.collectible.Collectible;
import supson.model.entity.impl.block.collectible.CollectibleFactoryImpl;
import supson.model.entity.impl.moveable.player.Player;

/**
 * This class contains unit tests for the Collectible class.
 */
final class TestCollectable {

    private CollectibleFactoryImpl factory;
    private Player player;

    /**
     * Sets up the necessary objects for each test case.
     */
    @BeforeEach
    void setUp() {
        factory = new CollectibleFactoryImpl();
        player = new Player(new Pos2dImpl(0, 0));
    }

    /**
     * Tests the creation of a collectible ring.
     */
    @Test
    void testCreateCollectibleRing() {
        final Pos2d pos = new Pos2dImpl(0, 0);
        final Collectible ring = factory.createCollectible(GameEntityType.RING, pos);

        assertNotNull(ring);
        assertEquals(GameEntityType.RING, ring.getGameEntityType());

        ring.collect(player);
        assertEquals(100, player.getScore());
    }

    /**
     * Tests the creation of a collectible life boost.
     */
    @Test
    void testCreateCollectibleLifeBoost() {
        final Pos2d pos = new Pos2dImpl(0, 0);
        final Collectible lifeBoost = factory.createCollectible(GameEntityType.LIFE_BOOST_POWER_UP, pos);

        assertNotNull(lifeBoost);
        assertEquals(GameEntityType.LIFE_BOOST_POWER_UP, lifeBoost.getGameEntityType());

        player.setLife(1);
        lifeBoost.collect(player);
        assertEquals(2, player.getLife());
    }

    /**
     * Tests the creation of an invalid collectible.
     */
    @Test
    void testCreateInvalidCollectible() {
        final Pos2d pos = new Pos2dImpl(0, 0);

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createCollectible(GameEntityType.ENEMY, pos);
        });
    }
}
