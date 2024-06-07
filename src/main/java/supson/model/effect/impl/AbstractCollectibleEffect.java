package supson.model.effect.impl;

import supson.model.effect.api.CollectibleEffect;
import supson.model.entity.player.Player;

/**
 * Abstract base class for collectible effects.
 * Provides a skeletal implementation for collectible effects.
 */
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
