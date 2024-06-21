package supson.model.entity.api.moveable.player;

import supson.model.entity.impl.moveable.player.PlayerState;

/**
 * This interface model the main player of the game.
 */
public interface MainPlayer {

    /**
     * This method set the state of the player.
     * The method should be called whenever the state of the player
     * needs to change, passing to this method an appropriate new state.
     * @param state the state to be set
     */
    public void setState(PlayerState state);

    /**
     * This method returns the current player state.
     * @return the player current state
     */
    public PlayerState getState();

    /**
     * This method is used to increment (or decrement) the score.
     * @param score the score to be incremented
     */
    public void incrementScore(int score);

    /**
     * This method return the score.
     * @return the score
     */
    public int getScore();

    /**
     * This method increments (or decrements) the lives of the player. It does 
     * nothing when the player has already the max number of lives.
     * @param lives an integer representing how many lives to increment
     */
    public void incrementLife(int lives);

}
