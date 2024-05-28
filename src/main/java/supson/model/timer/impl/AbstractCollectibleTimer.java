package supson.model.timer.impl;

import supson.model.timer.api.CollectibleTimer;

/**
 * An abstract implementation of the {@link CollectibleTimer} interface.
 * Provides a common structure for collectible timers with a specified duration.
 */
public abstract class AbstractCollectibleTimer implements CollectibleTimer {

    private final long duration;

    /**
     * Constructs a new {@code AbstractCollectibleTimer} with the specified duration.
     *
     * @param duration the duration of the timer in milliseconds
     */
    public AbstractCollectibleTimer(long duration) {
        this.duration = duration;
    }

    @Override
    public void run() {
        try {
            this.activateEffect();
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            this.terminateEffect();
        }
    }
}
