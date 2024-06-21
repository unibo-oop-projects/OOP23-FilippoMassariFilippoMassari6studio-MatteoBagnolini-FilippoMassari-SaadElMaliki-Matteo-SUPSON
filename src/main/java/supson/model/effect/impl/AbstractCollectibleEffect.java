package supson.model.effect.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import supson.model.effect.api.CollectibleEffect;
import supson.model.entity.impl.moveable.player.Player;

/**
 * Abstract base class for collectible effects.
 * Provides a skeletal implementation for collectible effects.
 */
@SuppressFBWarnings(
    value = {
        "EI_EXPOSE_REP2",
        "SWL_SLEEP_WITH_LOCK_HELD"
    },
    justification = "The player object is passed as an external reference avoiding" 
                    + " creating a defensive copy since the Power-Up needs to"
                    + " implement its effect directly on the latter"

                    + "The sleep is necessary for the duration of the effect "
                    + "and does not cause significant issues in this context. "
)
public abstract class AbstractCollectibleEffect implements CollectibleEffect {

    private final long duration;
    private final Player player;
    private final Object lock;

    /**
     * Constructs an AbstractCollectibleEffect.
     *
     * @param duration The duration of the effect.
     * @param player   The target player for the effect.
     * @param lock     An object used for synchronization.
     */
    public AbstractCollectibleEffect(final long duration, final Player player, final Object lock) {
        this.duration = duration;
        this.player = player;
        this.lock = lock;
    }

    @Override
    public final void run() {
        synchronized (lock) {
            try {
                while (player.getState().isInvulnerable()) {
                    lock.wait();
                }
                this.activateEffect(player);
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                this.terminateEffect(player);
                lock.notifyAll();
            }
        }
    }
}
