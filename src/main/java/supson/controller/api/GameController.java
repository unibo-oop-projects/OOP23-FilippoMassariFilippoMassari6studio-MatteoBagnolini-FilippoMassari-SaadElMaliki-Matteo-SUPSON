package supson.controller.api;

/**
 * The GameController interface represents a game controller.
 * It provides methods for processing input, updating the game state, and rendering the game.
 */
public interface GameController {

    /**
     * Processes the input for the game.
     * TODO: Specify the type of input to be processed.
     */
    void processInput();

    /**
     * Updates the game state.
     */
    void update();

    /**
     * Renders the game.
     */
    void render(); //il render è gia presente in GameEngine vedere se tenerlo o no

}
