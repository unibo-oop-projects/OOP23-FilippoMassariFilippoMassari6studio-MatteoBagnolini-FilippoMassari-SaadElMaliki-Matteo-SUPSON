package supson.model.hitbox.api;

import supson.common.api.Pos2d;

/**
 * This interface models an hitbox.
 */
public interface Hitbox {

    /**
     * 
     * @param other the hitbox to check the collision with
     * @return true if the two hitboxes are colliding, false otherwise
     */
    boolean isCollidingWith(Hitbox other);

    /**
     * Move the hitbox to a new position.
     * @param newPosition the new position where the hitbox should be
     */
    void setPosition(Pos2d newPosition);
    
}
