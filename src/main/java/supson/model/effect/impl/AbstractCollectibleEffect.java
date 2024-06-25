package supson.model.effect.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import supson.model.effect.api.CollectibleEffect;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.timer.api.GameTimer;
import supson.model.timer.impl.GameTimerImpl;

/**
 * Abstract base class for collectible effects.
 * Provides a skeletal implementation for collectible effects.
 */
@SuppressFBWarnings(
    value = {
        "EI_EXPOSE_REP2",
    },
    justification = "The player object is passed as an external reference avoiding" 
                    + " creating a defensive copy since the Power-Up needs to"
                    + " implement its effect directly on the latter"
)
public abstract class AbstractCollectibleEffect implements CollectibleEffect {

    private static final int WAIT_TIME = 10;

    private final int duration;
    private final Player player;
    private final Object lock;
    private final GameTimer timer;

    /**
     * Constructs an AbstractCollectibleEffect.
     *
     * @param duration The duration of the effect.
     * @param player   The target player for the effect.
     * @param lock     An object used for synchronization.
     */
    public AbstractCollectibleEffect(final int duration, final Player player, final Object lock) {
        this.duration = duration;
        this.player = player;
        this.lock = lock;
        this.timer = new GameTimerImpl();
    }

    @Override
    public final void run() {
        synchronized (lock) {
            try {
                while (player.getState().isInvulnerable()) {
                    lock.wait();
                }
                this.activateEffect(player);
                timer.start();
                while (timer.getElapsedTimeInSeconds() <= duration) {
                    lock.wait(WAIT_TIME);
                }
                timer.stop();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                this.terminateEffect(player);
                lock.notifyAll();
            }
        }
    }
}

