package supson.model.entity.api;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.hitbox.api.Hitbox;

/**
 * This interface models a generic entity of the game.
 */
public interface GameEntity {

    /**
     * Get the height of the entity.
     *
     * @return the height of the entity
     */
    int getHeight();

    /**
     * Get the width of the entity.
     *
     * @return the width of the entity
     */
    int getWidth();

    /**
     * Get the position of the entity.
     *
     * @return the position of the entity
     */
    Pos2d getPosition();

    /**
     * Set the position of the entity.
     *
     * @param pos the new position of the entity
     */
    void setPosition(Pos2d pos);

    /**
     * Get the hitbox of the entity.
     *
     * @return the hitbox of the entity
     */
    Hitbox getHitbox();

    /**
     * Get the type of block that the entity is.
     *
     * @return the type of block that the entity is
     */
    GameEntityType getGameEntityType();

    /**
     * Check if the entity is colliding with another entity.
     *
     * @param otherGameEntity the other entity to check collision with
     * @return true if the entities are colliding, false otherwise
     */
    boolean isCollidingWith(GameEntity otherGameEntity);

    /**
     * Notify the entity that it is colliding with another entity.
     *
     * @param collidingGameEntity the other entity that the entity is colliding with
     */
    void notifyCollision(GameEntity collidingGameEntity);

}
