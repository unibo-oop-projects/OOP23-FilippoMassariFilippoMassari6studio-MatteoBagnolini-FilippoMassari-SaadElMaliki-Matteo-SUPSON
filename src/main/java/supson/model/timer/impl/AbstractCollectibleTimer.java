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
    public AbstractCollectibleTimer(final long duration) {
        this.duration = duration;
    }

    @Override //TODO : i check dicono che non c'è java doc non so il motivo
    public final void run() { //TODO : ho messo final per il todo sopra vediamo se va vene
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
