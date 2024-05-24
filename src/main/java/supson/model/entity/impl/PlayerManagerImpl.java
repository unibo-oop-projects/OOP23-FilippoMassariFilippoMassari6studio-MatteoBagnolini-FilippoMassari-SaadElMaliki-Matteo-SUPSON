package supson.model.entity.impl;

import supson.common.api.Observer;
import supson.common.impl.Vect2dImpl;
import supson.model.entity.api.PlayerManager;
import supson.model.hitbox.impl.CollisionEvents;
import supson.model.hitbox.impl.PlayerState;

public class PlayerManagerImpl implements PlayerManager, Observer {

    private PlayerState state;

    public PlayerManagerImpl() { }

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
    public void onNotify(final CollisionEvents event) {
        switch (event) {
            case UPPER_COLLISION -> upperCollision();
            case LOWER_COLLISION -> lowerCollision();
            case RIGHT_COLLISION -> rightCollision();
            case LEFT_COLLISION -> leftCollision();
        }
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

    public void setState(PlayerState state) {
        this.state = state;
    }

    public PlayerState getUpdatedState() {
        return this.state;
    }

}
