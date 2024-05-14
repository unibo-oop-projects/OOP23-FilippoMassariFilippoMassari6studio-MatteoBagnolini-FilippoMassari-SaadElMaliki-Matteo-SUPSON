package supson.model.hud.api;

/**
 * This interface models a hud. It is used to display info about the current level.
 */
public interface Hud {
    
    /**
     * This method returns the current score of the game.
     * @return the score of the game
     */
    int getScore();

    /**
     * This method returns the current lives of the main character.
     * @return the lives of the main character
     */
    int getLives();

}
