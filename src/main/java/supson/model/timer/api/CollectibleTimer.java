package supson.model.timer.api;

/**
 * The CollectibleTimer interface represents a timer that can activate and terminate effects.
 * It extends the Runnable interface, allowing it to be executed as a thread.
 */
public interface CollectibleTimer extends Runnable {

    /**
     * Activates the effect associated with this timer.
     */
    void activateEffect();

    /**
     * Terminates the effect associated with this timer.
     */
    void terminateEffect();

    /**
     * Runs the timer.
     */
    void run();

}