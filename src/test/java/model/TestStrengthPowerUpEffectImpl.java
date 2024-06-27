package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import supson.common.GameEntityType;
import supson.common.impl.Pos2dImpl;
import supson.model.effect.api.CollectibleEffect;
import supson.model.effect.api.TimedEffectFactory;
import supson.model.effect.impl.TimedEffectFactoryImpl;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.entity.impl.moveable.player.PlayerState;

/**
 * This class contains unit tests for the StrengthPowerUpEffectImpl class.
 */
final class TestStrengthPowerUpEffectImpl {

    private Player player;
    private final Object lock = new Object();
    private int duration;

    @BeforeEach
    void setUp() {
        player = new Player(new Pos2dImpl(0, 0));
        player.setState(new PlayerState(null, false, false, false, false, false, false));
        duration = 1; // duration in seconds
    }

    /**
     * Tests the run() method of the StrengthPowerUpEffectImpl class.
     * It checks if the effect is activated and terminated correctly after the specified duration.
     */
    @Test
    void testRun() throws InterruptedException {
        final TimedEffectFactory factory = new TimedEffectFactoryImpl();
        final CollectibleEffect effect = factory.createEffect(GameEntityType.STRNGTH_BOOST_POWER_UP, player, lock);

        assertFalse(player.getState().isInvulnerable());
        final Thread thread = new Thread(effect);
        thread.start();
        Thread.sleep((duration * 1000) / 2);
        synchronized (lock) {
            assertTrue(player.getState().isInvulnerable());
        }
        thread.join();
        synchronized (lock) {
            assertFalse(player.getState().isInvulnerable());
        }
    }
}
