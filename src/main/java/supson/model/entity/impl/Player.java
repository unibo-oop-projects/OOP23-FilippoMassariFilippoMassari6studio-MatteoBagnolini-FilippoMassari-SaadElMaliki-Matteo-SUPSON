package supson.model.entity.impl;

import supson.common.api.Vect2d;
import supson.common.impl.Vect2dImpl;

/**
 * This class, which extends the abstract class MoveableEntity, models
 * the player of the game.
 */
public final class Player extends AbstractMoveableEntity {

    private static final int MAX_SPEED = 10;
    private static final double ACC_SPEED = 0.001;

    private boolean left, right;
    private boolean isJumping;
    private boolean jump;

    @Override
    public void updateVelocity() {
        if (left) {
            moveLeft();
        } else if (right) {
            moveRight();
        }
        if (jump) {
            jump();
        }
        setMovesToFalse();
    }

    /**
     * This method update the velocity vector, causing the player
     * to move right.
     */
    private void moveRight() {
        final Vect2d oldVel = getVelocity();
        double newXVel = oldVel.x();
        if (oldVel.x() < 0) {   //were moving left

            stopOrizVel();

        } else if (oldVel.x() < MAX_SPEED) {

            newXVel += ACC_SPEED;  //accelerate

            if (newXVel >= MAX_SPEED) {

                newXVel = MAX_SPEED;    //top speed reached

            }
        }
        setVelocity(new Vect2dImpl(newXVel, oldVel.y()));
    }

    /**
     * This method update the velocity vector, causing the player
     * to move left.
     */
    private void moveLeft() {
        final Vect2d oldVel = getVelocity();
        double newXVel = oldVel.x();
        if (oldVel.x() > 0) {   //were moving right

            stopOrizVel();

        } else if (oldVel.x() > -MAX_SPEED) {

            newXVel -= ACC_SPEED;   //accelerate

            if (newXVel <= -MAX_SPEED) {

                newXVel = -MAX_SPEED;  //top speed reached

            }
        }
        setVelocity(new Vect2dImpl(newXVel, oldVel.y()));
    }

    /**
     * This method stops the player on the X axis. It is called everytime the 
     * player changes direction (from left to right and vice versa).
     */
    private void stopOrizVel() {
        setVelocity(new Vect2dImpl(0.0, getVelocity().y()));
    }

    /**
     * This method update the velocity vector, causing the player to jump.
     */
    private void jump() {
        if (!isJumping) {
            setVelocity(getVelocity());
        }
    }

    /**
     * This method set all the movement flags to false.
     * It should be called only at the end of updateVelocity method.
     */
    private void setMovesToFalse() {
        this.left = false;
        this.right = false;
        this.jump = false;
    }

}
