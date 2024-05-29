package supson.model.timer.impl;

import supson.model.timer.api.GameTimer;

/**
 * Implementation of the {@link GameTimer} interface.
 * This class provides functionality to measure elapsed time for a game.
 */
public final class GameTimerImpl implements GameTimer {

    private static final double NANOSECONDS_TO_SECONDS = 1_000_000_000.0;

    private long startTime;
    private long elapsedTime;
    private boolean running;

    /**
     * Constructs a new instance of the {@code GameTimerImpl} class.
     * The timer is initially not running and the elapsed time is set to zero.
     */
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
        return getElapsedTime() / NANOSECONDS_TO_SECONDS;
    }
}
