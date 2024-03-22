package supson.model.entity.api;

import supson.common.api.Vect2d;

/**
 * This interface models a moveable entity. A moveable entity is an entity with life points 
 * and able to move through the {@code move} method.
 */
public interface MoveableEntity extends GameEntity {

    /**
     * 
     * @return the actual velocity of the object as a 2d vector
     */
    Vect2d getVelocity();

    /**
     * Set the velocity of the entity.
     * @param vel the 2d vector representing the velocity vector
     */
    void setVelocity(Vect2d vel);

    /**
     * 
     * @return the number of lives remaining
     */
    int getLife();

    /**
     * Set the number of lives of the entity.
     * @param numLife the number of lives
     */
    void setLife(int numLife);

    /**
     * Move the entity based on a certain time delta.
     * @param timeDelta the time elapsed between now and the last frame
     */
    void move(long timeDelta);

    /**
     * Update the velocity vector based on input/behavior.
     */
    void updateVelocity();

}
