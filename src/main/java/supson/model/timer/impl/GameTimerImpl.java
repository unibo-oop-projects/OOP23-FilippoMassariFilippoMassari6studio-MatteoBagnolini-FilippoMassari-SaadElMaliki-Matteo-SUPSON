package supson.model.timer.impl;

import supson.model.timer.api.GameTimer;

public class GameTimerImpl implements GameTimer {
    
    private long startTime;
    private long elapsedTime;
    private boolean running;

    public GameTimerImpl() {
        this.startTime = 0;
        this.elapsedTime = 0;
        this.running = false;
    }

    @Override
    public void start() {
        if (!running) {
            this.startTime = System.nanoTime();
            this.running = true;
        }
    }

    @Override
    public void stop() {
        if (running) {
            this.elapsedTime += System.nanoTime() - this.startTime;
            this.running = false;
        }
    }

    @Override
    public void reset() {
        this.startTime = 0;
        this.elapsedTime = 0;
        this.running = false;
    }

    @Override
    public long getElapsedTime() {
        if (running) {
            return this.elapsedTime + (System.nanoTime() - this.startTime);
        } else {
            return this.elapsedTime;
        }
    }

    @Override
    public double getElapsedTimeInSeconds() {
        return getElapsedTime() / 1_000_000_000.0;
    }
}
