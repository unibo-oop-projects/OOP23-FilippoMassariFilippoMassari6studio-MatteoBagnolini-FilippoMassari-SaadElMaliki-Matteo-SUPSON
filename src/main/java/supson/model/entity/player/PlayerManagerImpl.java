package supson.model.entity.player;

import supson.common.api.Vect2d;
import supson.common.impl.Vect2dImpl;
import supson.model.collisions.CollisionEvent;
import supson.model.collisions.api.CollisionObserver;
import supson.model.entity.api.PlayerManager;

/**
 * This class represents a player manager. It is used to update the state of 
 * the player, based on events happening during collisions.
 */
public final class PlayerManagerImpl implements PlayerManager, CollisionObserver {

    private static final Vect2d PUSH_BACK_VEL = new Vect2dImpl(25, 10);

    private PlayerState state;

    /**
     * This is the cosntructor of this class. State attribute is initialized
     * with the player state to avoid the attribute to be empty.
     * @param player the player to manage
     */
    public PlayerManagerImpl(final Player player) {
        this.state = player.getState();
    }

    @Override
    public void moveRight() {
        this.state = new PlayerState(state.vel(), true, state.left(),
        state.jump(), state.onGround(), state.isJumping(), state.isInvulnerable());
    }

    @Override
    public void moveLeft() {
        this.state = new PlayerState(state.vel(), state.right(), true,
        state.jump(), state.onGround(), state.isJumping(), state.isInvulnerable());
    }

    @Override
    public void stopOnOrizontal() {
        this.state = new PlayerState(new Vect2dImpl(0, state.vel().y()), false, false,
        state.jump(), state.onGround(), state.isJumping(), state.isInvulnerable());
    }

    @Override
    public void stopOnVertical() {
        this.state = new PlayerState(new Vect2dImpl(state.vel().x(), 0), state.right(),
        state.left(), false, state.onGround(), state.isJumping(), state.isInvulnerable());
    }

    @Override
    public void jump() {
        this.state = new PlayerState(state.vel(), state.right(), state.left(),
        true, state.onGround(), state.isJumping(), state.isInvulnerable());
    }

    @Override
    public boolean isJumping() {
        return state.isJumping();
    }

    @Override
    public boolean isInvulnerable() {
        return state.isInvulnerable();
    }

    @Override
    public void setInvulnerable() {
        this.state = new PlayerState(state.vel(), state.right(), state.left(),
        state.jump(), state.onGround(), state.isJumping(), true);

    }

    @Override
    public void setVulnerable() {
        this.state = new PlayerState(state.vel(), state.right(), state.left(),
        state.jump(), state.onGround(), state.isJumping(), false);

    }

    @Override
    public void onNotify(final CollisionEvent event) {
        // CHECKSTYLE: MissingSwitchDefault OFF
        //Shouldn't be a default case. All events must be handled by the manager.
        //In addition, a warning should be raised when not all events are handled.
        switch (event) {
            case BLOCK_UPPER_COLLISION -> upperCollision();
            case BLOCK_LOWER_COLLISION -> lowerCollision();
            case BLOCK_RIGHT_COLLISION -> rightCollision();
            case BLOCK_LEFT_COLLISION -> leftCollision();
            case OBSTACLE_LEFT_COLLISION -> pushBackLeft();
            case OBSTACLE_RIGHT_COLLISION -> pushBackRight();
        }
        // CHECKSTYLE: MissignSwitchDefault ON
    }

    private void leftCollision() {
        stopOnOrizontal();
    }

    private void rightCollision() {
        stopOnOrizontal();
    }

    private void lowerCollision() {
        stopOnVertical();
        this.state = new PlayerState(state.vel(), state.right(), state.left(),
        state.jump(), true, false, state.isInvulnerable());
    }

    private void upperCollision() {
        stopOnVertical();
    }

    private void pushBackRight() {
        moveLeft();
        final Vect2d vel = new Vect2dImpl(-PUSH_BACK_VEL.x(), PUSH_BACK_VEL.y());
        this.state = new PlayerState(vel, state.right(), state.left(),
        false, false, false, isInvulnerable());
    }

    private void pushBackLeft() {
        moveRight();
        this.state = new PlayerState(PUSH_BACK_VEL, state.right(), state.left(),
        false, false, false, isInvulnerable());
    }

    @Override
    public void setState(final PlayerState state) {
        this.state = state;
    }

    @Override
    public PlayerState getUpdatedState() {
        return this.state;
    }

}
