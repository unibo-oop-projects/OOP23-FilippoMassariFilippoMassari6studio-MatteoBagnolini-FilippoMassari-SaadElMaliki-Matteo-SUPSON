package supson.model.effect.api;

import supson.common.GameEntityType;
import supson.model.entity.impl.moveable.player.Player;

public interface TimedEffectFactory {

    /**
     * Create a new effect
     * 
     * @param type the type of collectible associated with the effect
     * @param player the player that collected the collectable
     * @param lock the lock variable to synchronize the effect
     *
     *  @return the effect of the collectible
     */
    CollectibleEffect createEffect(GameEntityType type, Player player, Object lock);
}
