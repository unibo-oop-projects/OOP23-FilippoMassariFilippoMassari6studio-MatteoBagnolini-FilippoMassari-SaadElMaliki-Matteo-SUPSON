package supson.model.physics.api;

import supson.model.entity.api.MoveableEntity;

/**
 * This interface models the physics of every moveable entity of the game.
 * Classes that implements this interface update the velocity vector of the moveable
 * entity.
 */
public interface Physics {

    /**
     * This method move the character to the rigth, modifying the velocity vector
     * of the attached entity.
     * @param entity the entity to move.
     */
    void moveRight(MoveableEntity entity);

    /**
     * This method move the character to the left, modifying the velocity vector
     * of the attached entity.
     * @param entity the entity to move.
     */
    void moveLeft(MoveableEntity entity);

    /**
     * This method begins the jumping action of the character, modifying the
     * velocity vector of the attached entity.
     * @param entity the entity to move.
     */
    void startJumping(MoveableEntity entity);

    /**
     * This method applies gravity to the attached entity, decreasing the vertical
     * component of the velocity vector of the entity.
     * @param entity the entity to move.
     */
    void applyGravity(MoveableEntity entity);

}
