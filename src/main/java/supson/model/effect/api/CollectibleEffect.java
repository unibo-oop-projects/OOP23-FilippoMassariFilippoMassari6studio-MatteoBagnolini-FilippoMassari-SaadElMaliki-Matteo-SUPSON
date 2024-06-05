package supson.model.effect.api;

import supson.model.entity.player.Player;

/**
 * The CollectibleTimer interface represents a timer that can activate and terminate effects.
 * It extends the Runnable interface, allowing it to be executed as a thread.
 */
public interface CollectibleEffect extends Runnable {

    /**
     * Activates the effect associated with this timer.
     */
    void activateEffect(Player player);

    /**
     * Terminates the effect associated with this timer.
     */
    void terminateEffect(Player player);

    /**
     * Runs the timer.
     */
    void run();

}
