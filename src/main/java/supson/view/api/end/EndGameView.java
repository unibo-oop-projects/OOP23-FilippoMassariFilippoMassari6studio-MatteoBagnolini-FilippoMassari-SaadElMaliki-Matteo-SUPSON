package supson.view.api.end;


/**
 * The EndGameView interface represents the view component of the end game.
 * It provides methods for initializing and rendering the end game view.
 */
public interface EndGameView {

    /**
     * Initializes the view.
     */
    void initView();

    /**
     * Renders the view.
     * @param score the final score of the game.
     * @param time the final time of the game.
     * @param isWon wether the player won or lost.
     */
    void renderView(int score, double time, boolean isWon);
}
