package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import supson.common.impl.Pos2dImpl;
import supson.model.effect.api.CollectibleEffect;
import supson.model.effect.impl.StrengthPowerUpEffectImpl;
import supson.model.entity.impl.player.Player;
import supson.model.entity.impl.player.PlayerState;

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
        CollectibleEffect effect = new StrengthPowerUpEffectImpl(duration, player, lock);

        Thread effectThread = new Thread(effect);
        effectThread.start();
        Thread.sleep(100);
        assertTrue(player.getState().isInvulnerable());
        effectThread.join();
        assertFalse(player.getState().isInvulnerable());
    }

    /**
     * Tests if the effect respects the invulnerability state.
     * It checks that the effect waits if the player is initially invulnerable.
     */
    @Test
    void testRunWithInitialInvulnerability() throws InterruptedException {
        player.setState(new PlayerState(null, true, true, true, true, true, true));
        StrengthPowerUpEffectImpl effect = new StrengthPowerUpEffectImpl(duration, player, lock);
        Thread effectThread = new Thread(effect);
        effectThread.start();
        Thread.sleep(100);
        assertTrue(player.getState().isInvulnerable());
        synchronized (lock) {
            player.setState(new PlayerState(null, false, false, false, false, false, false));
            lock.notifyAll();
        }
        effectThread.join();
        assertFalse(player.getState().isInvulnerable());
    }
}
