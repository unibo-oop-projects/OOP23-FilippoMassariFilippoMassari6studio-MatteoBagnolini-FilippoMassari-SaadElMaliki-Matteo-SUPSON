package supson.model.entity.api.enemy;

import supson.model.entity.api.moveable.MoveableEntity;
import supson.model.entity.impl.moveable.player.Player;

/**
 * This interface models a generic enemy in the game.
 * It extends MoveableEntity interface and adds enemy behaviors.
 */
public interface Enemy extends MoveableEntity {

    /**
     * Applies damage to specified player.
     * 
     * @param player the player to be damaged
     */
    void applyDamage(Player player);

}
