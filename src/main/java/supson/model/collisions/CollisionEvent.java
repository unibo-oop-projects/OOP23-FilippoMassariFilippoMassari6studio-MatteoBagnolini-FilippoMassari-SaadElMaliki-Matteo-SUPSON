package supson.model.collisions;

/**
 * This enums represent all the notifiable events occuring during collisions.
 * @see supson.model.collisions.api.CollisionObserver
 */
public enum CollisionEvent {

    /**
     * Collision with a block from the right.
     */
    BLOCK_RIGHT_COLLISION,

    /**
     * Collision with a block from the left.
     */
    BLOCK_LEFT_COLLISION,

    /**
     * Collision with a block from above.
     */
    BLOCK_UPPER_COLLISION,

    /**
     * Collision with a block from below.
     */
    BLOCK_LOWER_COLLISION,

    /**
     * Collision with an obstacle (enemy or trap) from the left.
     */
    OBSTACLE_LEFT_COLLISION,

    /**
     * Collision with an obstacle (enemy or trap) from the right.
     */
    OBSTACLE_RIGHT_COLLISION

}
