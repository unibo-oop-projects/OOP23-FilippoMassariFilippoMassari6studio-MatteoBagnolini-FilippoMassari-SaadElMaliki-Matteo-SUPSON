package supson.model.entity.api;

import supson.common.api.Pos2d;
import supson.model.hitbox.api.Hitbox;

/**
 * This interface models a generic entity of the game.
 */
public interface GameEntity {

    /**
     * @return the position of the entity
     */
    Pos2d getPosition();

    /**
     * Set the position of the entity.
     * @param pos the new position of the entity
     */
    void setPosition(Pos2d pos);

    /**
     * @return the hitbox of the entity
     */
    Hitbox getHitbox();

}
