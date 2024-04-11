package supson.model.entity.impl;

import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.common.impl.Vect2dImpl;

/**
 * This class, which extends the abstract class MoveableEntity, models
 * the player of the game.
 */
public final class Player extends AbstractMoveableEntity {

    private static final int MAX_SPEED = 10;
    private static final double ACC_SPEED = 0.01;
    private static final int JUMP_FORCE = 12;
    private static final double GRAVITY = 0.05;

    private static final int HEIGHT = 2;
    private static final int WIDTH = 1;

    private boolean left, right, jump;
    private boolean isJumping;

    /**
     * The constructor of the player class.
     * @param pos the starting positon of the player
     * @param vel the starting velocity of the player
     * @param life the number of life of the player
     */
    public Player(final Pos2d pos, final Vect2d vel, final int life) {
        super(pos, HEIGHT, WIDTH, vel, life);
    } 

    @Override
    public void updateVelocity() {
        if (left) {
            moveLeft();
        } else if (right) {
            moveRight();
        }
        if (jump) {
            startJumping();
        }
        if (isJumping) {
            applyGravity();
        }
    }

    /**
     * This method update the velocity vector, causing the player
     * to move right.
     */
    private void moveRight() {
        final Vect2d oldVel = getVelocity();
        double newXVel = oldVel.x();
        if (oldVel.x() < 0) {   //were moving left

            newXVel = 0;        //the player stops moving

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

            newXVel = 0;        //the player stops moving

        } else if (oldVel.x() > -MAX_SPEED) {

            newXVel -= ACC_SPEED;   //accelerate

            if (newXVel <= -MAX_SPEED) {

                newXVel = -MAX_SPEED;  //top speed reached

            }
        }
        setVelocity(new Vect2dImpl(newXVel, oldVel.y()));
    }

    /**
     * This method start the jumping action of the player, by setting the new y component
     * of the velocity.
     */
    private void startJumping() {
        setVelocity(new Vect2dImpl(getVelocity().x(), JUMP_FORCE));
    }

    /**
     * This method update the y component of the velocity, applying gravity force.
     */
    private void applyGravity() {
        setVelocity(new Vect2dImpl(getVelocity().x(), getVelocity().y() - GRAVITY));
    }

    /**
     * This method set all the movement flags to false.
     * This method should be called by the controller when the player
     * stops moving in the X axis.
     */
    public void setMovesToFalse() {
        this.left = false;
        this.right = false;
    }

    /**
     * This method sets the right flag. It should be used when 
     * the player moves right.
     * @param flag the boolean value representing right move
     */
    public void setMoveRight(final boolean flag) {
        this.right = flag;
    }

    /**
     * This method sets the left flag. It should be used when 
     * the player moves left.
     * @param flag the boolean value representing left move
     */
    public void setMoveLeft(final boolean flag) {
        this.left = flag;
    }

    /**
     * This method sets the jump flag. It should be used when 
     * the player jumps.
     * @param flag the boolean value representing jump
     */
    public void setJump(final boolean flag) {
        this.jump = flag;
    }

}
