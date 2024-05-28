package supson.model.timer.impl;

import supson.model.entity.player.Player;

public final class StrengthPowerUpTimerImpl extends AbstractCollectibleTimer {

    private final Player player;

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
