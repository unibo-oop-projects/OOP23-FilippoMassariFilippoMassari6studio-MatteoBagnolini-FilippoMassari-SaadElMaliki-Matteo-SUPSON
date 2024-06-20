package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import supson.common.impl.Pos2dImpl;
import supson.model.effect.api.CollectibleEffect;
import supson.model.effect.impl.StrengthPowerUpEffectImpl;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.entity.impl.moveable.player.PlayerState;

/*
 * This class contains unit tests for the Strength PowerUp Effect class.
 */
public final class TestStrengthPowerUpEffectImpl {

    private Player player;
    private Object lock;
    private long duration;

    @BeforeEach
    void setUp() {
        player = new Player(new Pos2dImpl(0, 0));
        player.setState(new PlayerState(null, false, false, false, false, false, false));
        lock = new Object();
        duration = 1000; // 1 second
    }

    /**
     * Tests the run() method of the StrengthPowerUpEffectImpl class.
     * It checks if the effect is activated and terminated correctly after the specified duration.
     */
    @Test
    void testRun() throws InterruptedException {
        final CollectibleEffect effect = new StrengthPowerUpEffectImpl(duration, player, lock);

        assertFalse(player.getState().isInvulnerable());
        final Thread thread = new Thread(effect);
        thread.start();
        Thread.sleep(duration / 2);
        assertTrue(player.getState().isInvulnerable());
        thread.join();
        assertFalse(player.getState().isInvulnerable());
    }
}
