package supson.model.timer.impl;

import supson.model.entity.player.Player;
import supson.model.entity.player.PlayerState;

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
    public StrengthPowerUpTimerImpl(final long duration, final Player player) { //TODO : ai check non piace il player assato cosi
        super(duration);
        this.player = player;
    }

    @Override
    public void activateEffect() {
        // this.player.setState(new PlayerState.Builder(
        //     player.getState()).isInvulnerable(true).build());
        this.player.setState(player.getState().setInvulnerable());
    }

    @Override
    public void terminateEffect() {
        // this.player.setState(new PlayerState.Builder(
        //     player.getState()).isInvulnerable(false).build());
        this.player.setState(player.getState().setNotInvulnerable());
    }

}
