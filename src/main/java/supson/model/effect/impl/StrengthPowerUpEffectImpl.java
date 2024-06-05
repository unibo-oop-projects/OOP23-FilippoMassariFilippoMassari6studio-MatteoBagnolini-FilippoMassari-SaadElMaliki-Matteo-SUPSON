package supson.model.effect.impl;

import supson.model.entity.player.Player;
import supson.model.entity.player.PlayerState;

public final class StrengthPowerUpEffectImpl extends AbstractCollectibleEffect {

    public StrengthPowerUpEffectImpl(final long duration, final Player player, final Object lock) {
        super(duration, player, lock);
    }

    @Override
    public void activateEffect(final Player player) {
        player.setState(new PlayerState.Builder(
            player.getState()).isInvulnerable(true).build());
    }

    @Override
    public void terminateEffect(final Player player) {
        player.setState(new PlayerState.Builder(
            player.getState()).isInvulnerable(false).build());
    }
}
