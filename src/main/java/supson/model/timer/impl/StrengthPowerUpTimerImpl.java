package supson.model.timer.impl;

import supson.model.entity.player.Player;

/**
 * This class represents a timer for the strength power-up effect in the game.
 * It extends the AbstractCollectibleTimer class and implements the Timer interface.
 */
public final class StrengthPowerUpTimerImpl extends AbstractCollectibleTimer {

    private final Player player;

    /**
     * Constructs a StrengthPowerUpTimerImpl object with the specified duration and player.
     * 
     * @param duration the duration of the timer in milliseconds
     * @param player the player associated with the timer
     */
    public StrengthPowerUpTimerImpl(long duration, Player player) {
        super(duration);
        this.player = player;
    }

    @Override
    public void activateEffect() {
        this.player.setVulnerability(true);
    }

    @Override
    public void terminateEffect() {
        this.player.setVulnerability(false);
    }

}
