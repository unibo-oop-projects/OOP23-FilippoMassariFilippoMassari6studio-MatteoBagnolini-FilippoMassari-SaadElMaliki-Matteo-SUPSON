package supson.model.entity.impl;

import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.model.physics.impl.PhysicsImpl;

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
    private int score;

    /**
     * The constructor of the player class.
     * @param pos the starting positon of the player
     * @param vel the starting velocity of the player
     * @param life the number of life of the player
     */
    public Player(final Pos2d pos, final Vect2d vel, final int life) {
        super(pos, HEIGHT, WIDTH, vel, life);
        setPhysics(new PhysicsImpl(MAX_SPEED, ACC_SPEED, JUMP_FORCE, GRAVITY));
        this.score = 0;
    } 

    @Override
    public void updateVelocity() {
        if (left) {
            getPhysicsComponent().moveLeft(this);
        } else if (right) {
            getPhysicsComponent().moveRight(this);
        }
        if (jump) {
            getPhysicsComponent().startJumping(this);
        }
        if (isJumping) {
            getPhysicsComponent().applyGravity(this);
        }
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

    /**
     * This method is used to set the score.
     * @param score the score to be set
     */
    public void setScore(final int score) {
        this.score = score;
    }

    /**
     * This method return the score.
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

}
