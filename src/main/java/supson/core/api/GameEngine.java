package supson.core.api;

import supson.view.ViewEvent;

/**
 * The GameEngine interface represents a game engine that controls the game flow.
 */
public interface GameEngine {

    /**
     * Initializes the game.
     */
    void initGame();

    /**
     * The main control method of the game.
     */
    void mainControl();

    /**
     * Processes the user input.
     */
    void processInput();

    /**
     * Updates the game state based on the elapsed time.
     *
     * @param elapsed The elapsed time since the last update.
     */
    void updateGame(long elapsed);

    /**
     * Renders the game.
     */
    void render();

    /**
     * This method receive an event from the view. It is used to
     * manage the states of the game, based on the view's events.
     * @param event
     */
    void onNotifyFromView(ViewEvent event);

}
