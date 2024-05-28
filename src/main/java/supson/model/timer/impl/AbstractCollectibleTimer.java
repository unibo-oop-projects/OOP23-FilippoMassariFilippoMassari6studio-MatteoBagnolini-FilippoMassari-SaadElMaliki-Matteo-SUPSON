package supson.model.timer.impl;

import supson.model.timer.api.CollectibleTimer;

public abstract class AbstractCollectibleTimer implements CollectibleTimer {

    private final long duration;

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
