package supson.model.timer.api;

/**
 * The GameTimer interface represents a timer for a game.
 */
public interface GameTimer {

    /**
     * Starts the timer.
     */
    void start();

    /**
     * Stops the timer.
     */
    void stop();

    /**
     * Resets the timer.
     */
    void reset();

    /**
     * Returns the elapsed time in milliseconds.
     *
     * @return the elapsed time in milliseconds.
     */
    long getElapsedTime();

    /**
     * Returns the elapsed time in seconds.
     *
     * @return the elapsed time in seconds.
     */
    double getElapsedTimeInSeconds();

}
