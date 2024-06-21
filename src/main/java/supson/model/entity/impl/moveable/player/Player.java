package supson.model.entity.impl.moveable.player;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.impl.Vect2dImpl;
import supson.model.entity.api.moveable.player.MainPlayer;
import supson.model.entity.impl.moveable.AbstractMoveableEntity;
import supson.model.physics.api.Physics;
import supson.model.physics.impl.PhysicsImpl;

/**
 * This class, which extends the abstract class MoveableEntity, models
 * the player of the game.
 */
public final class Player extends AbstractMoveableEntity implements MainPlayer {

    private static final int MAX_SPEED = 20;
    private static final double ACC_SPEED = 0.8;
    private static final double DEC_SPEED = 1.2;
    private static final double FRICTION = 0.8;
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
     */
    public Player(final Pos2d pos) {
        super(pos, HEIGHT, WIDTH, TYPE, new Vect2dImpl(0, 0), MAX_LIVES,
            new PhysicsImpl(MAX_SPEED, ACC_SPEED, DEC_SPEED, FRICTION,  JUMP_FORCE, GRAVITY));
        this.score = 0;
    }

    /**
     * This constructor creates a new instance of Player.
     * The new object is a copy of the parameter player
     * @param player the player to copy
     */
    public Player(final Player player) {
        super(player.getPosition(), HEIGHT, WIDTH, TYPE, player.getVelocity(), player.getLife(), player.getPhysicsComponent());
        setState(player.getState());
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

    @Override
    public void incrementScore(final int score) {
        this.score += score;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void incrementLife(final int lives) {
        if (getLife() + lives <= MAX_LIVES) {
            this.setLife(getLife() + lives);
        }
    }

    @Override
    public PlayerState getState() {
        return new PlayerState(this.getVelocity(), right, left, jump,
        onGround, isJumping, isInvulnerable);
    }

    @Override
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
