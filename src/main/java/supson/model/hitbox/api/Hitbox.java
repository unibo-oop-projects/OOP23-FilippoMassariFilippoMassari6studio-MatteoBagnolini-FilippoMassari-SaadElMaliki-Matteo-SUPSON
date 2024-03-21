package supson.model.hitbox.api;

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

}
