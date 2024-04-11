package supson.model.physics.api;

/**
 * This interface models the physics of every moveable entity of the game.
 * Classes that implements this interface update the velocity vector of the moveable
 * entity they are attached to.
 */
public interface Physics {

    /**
     * This method move the character to the rigth, modifying the velocity vector
     * of the attached entity.
     */
    void moveRight();

    /**
     * This method move the character to the left, modifying the velocity vector
     * of the attached entity.
     */
    void moveLeft();

    /**
     * This method begins the jumping action of the character, modifying the
     * velocity vector of the attached entity.
     */
    void startJumping();

    /**
     * This method applies gravity to the attached entity, decreasing the vertical
     * component of the velocity vector of the entity.
     */
    void applyGravity();

}
