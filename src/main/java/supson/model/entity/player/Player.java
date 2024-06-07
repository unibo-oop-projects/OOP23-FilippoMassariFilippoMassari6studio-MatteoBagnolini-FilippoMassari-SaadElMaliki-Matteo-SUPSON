package supson.model.entity.player;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.common.impl.Vect2dImpl;
import supson.model.entity.impl.AbstractMoveableEntity;
import supson.model.physics.api.Physics;
import supson.model.physics.impl.PhysicsImpl;

/**
 * This class, which extends the abstract class MoveableEntity, models
 * the player of the game.
 */
public final class Player extends AbstractMoveableEntity {

    private static final int MAX_SPEED = 20;
    private static final double ACC_SPEED = 0.8;
    private static final Vect2d VELOCITY = new Vect2dImpl(0, 0);
    private static final double DEC_SPEED = 1.2;
    private static final double FRICTION = 0.4;
    private static final int JUMP_FORCE = 12;
    private static final double GRAVITY = 0.8;

    private static final int HEIGHT = 2;
    private static final int WIDTH = 1;
    private static final int MAX_LIVES = 3;

    private static final GameEntityType TYPE = GameEntityType.PLAYER;

    private boolean left, right, jump;
    private boolean onGround, isJumping;
    private boolean isInvulnerable;
    private int score;

    /**
     * The constructor of the player class.
     * @param pos the starting positon of the player
     * @param vel the starting velocity of the player
     * @param life the number of life of the player
     */
    public Player(final Pos2d pos) {
        super(pos, HEIGHT, WIDTH, TYPE, VELOCITY, MAX_LIVES,
            new PhysicsImpl(MAX_SPEED, ACC_SPEED, DEC_SPEED, FRICTION,  JUMP_FORCE, GRAVITY));
        this.score = 0;
    } 

    @Override
    public void updateVelocity() {
        final Physics physicsComponent = getPhysicsComponent();
        if (left) {
            physicsComponent.moveLeft(this);
        }
        if (right) {
            physicsComponent.moveRight(this);
        }
        if (!left && !right) {
            physicsComponent.applyFriction(this);
        }
        if (canJump()) {
            physicsComponent.startJumping(this);
            isJumping = true;
            jump = false;
            onGround = false;
        }
        if (!onGround) {
            physicsComponent.adjustAirSpeed(this);
        }
        physicsComponent.applyGravity(this);
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
     * This method is used to increment (or decrement) the score.
     * @param score the score to be incremented
     */
    public void incrementScore(final int score) {
        this.score += score;
    }

    /**
     * This method return the score.
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * This method increments (or decrements) the lives of the player. It does 
     * nothing when the player has already the max number of lives.
     * @param lives an integer representing how many lives to increment
     */
    public void incrementLife(final int lives) {
        if (getLife() + lives <= MAX_LIVES) {
            this.setLife(getLife() + lives);
        }
    }

    /**
     * This method returns the current player state.
     * @return the player current state
     */
    public PlayerState getState() {
        return new PlayerState(this.getVelocity(), right, left, jump,
        onGround, isJumping, isInvulnerable);
    }

    /**
     * This method set the state of the player.
     * @param state the state to be set
     */
    public void setState(final PlayerState state) {
        this.setVelocity(state.vel());
        this.right = state.right();
        this.left = state.left();
        this.jump = state.jump();
        this.onGround = state.onGround();
        this.isJumping = state.isJumping();
        this.isInvulnerable = state.isInvulnerable();
    }

    private boolean canJump() {
        return jump && onGround && getVelocity().y() == 0;
    }

}
