package supson.model.hud.impl;

import supson.model.hud.api.Hud;

/**
 * This class, which implements Hud, models the hud of the game. It is used to display info
 * about the current level.
 * A new instance of this class should be created whenever the hud has to be updated, with newer info. In fact 
 * this class is immutable.
 */
public final class HudImpl implements Hud {

    private final int score;
    private final int lives;

    /**
     * This is the constructor of the class.
     * @param score the actual score of the game
     * @param lives the actual lives of the main character
     */
    public HudImpl(final int score, final int lives) {
        this.score = score;
        this.lives = lives;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int getLives() {
        return this.lives;
    }

}
