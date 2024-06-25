package supson.model.effect.impl;

import supson.model.entity.impl.moveable.player.Player;

/**
 * Represents a strength power-up effect that makes the player invulnerable.
 */
public final class StrengthPowerUpEffectImpl extends AbstractCollectibleEffect {

    /**
     * Constructs a new `StrengthPowerUpEffectImpl`.
     *
     * @param duration The duration of the effect.
     * @param player   The player affected by the effect.
     * @param lock     An object used for synchronization.
     */
    public StrengthPowerUpEffectImpl(final int duration, final Player player, final Object lock) {
        super(duration, player, lock);
    }

    @Override
    public void activateEffect(final Player player) {
        player.setState(player.getState().setInvulnerable());
    }

    @Override
    public void terminateEffect(final Player player) {
        player.setState(player.getState().setNotInvulnerable());
    }
}
