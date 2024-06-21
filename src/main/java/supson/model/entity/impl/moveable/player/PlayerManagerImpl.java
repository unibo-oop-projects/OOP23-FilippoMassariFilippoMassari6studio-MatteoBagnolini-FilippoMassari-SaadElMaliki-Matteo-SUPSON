package supson.model.entity.impl.moveable.player;

import supson.common.api.Vect2d;
import supson.common.impl.Vect2dImpl;
import supson.model.collisions.CollisionEvent;
import supson.model.collisions.api.CollisionObserver;
import supson.model.entity.api.moveable.player.PlayerManager;

/**
 * This class represents a player manager. It is used to update the state of 
 * the player, based on events happening during collisions.
 */
public final class PlayerManagerImpl implements PlayerManager, CollisionObserver {

    private static final Vect2d PUSH_BACK_LEFT_VEL = new Vect2dImpl(25, 10);
    private static final Vect2d PUSH_BACK_RIGHT_VEL = new Vect2dImpl(-25, 10);

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
    public void moveRight(final Player player, final boolean flag) {
        if (flag) {
            player.setState(player.getState().setRight());
        } else {
            player.setState(player.getState().setNotRight());
        }
    }

    @Override
    public void moveLeft(final Player player, final boolean flag) {
        if (flag) {
            player.setState(player.getState().setLeft());
        } else {
            player.setState(player.getState().setNotLeft());
        }
    }


    @Override
    public void jump(final Player player, final boolean flag) {
        if (flag) {
            player.setState(player.getState().setJump());
        } else {
            player.setState(player.getState().setNotJump());
        }
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

    private void stopOnOrizontal() {
        newState(state.withVelocity(new Vect2dImpl(0, state.vel().y())).setNotRight().setNotLeft());
    }

    private void stopOnVertical() {
        newState(state.withVelocity(new Vect2dImpl(state.vel().x(), 0)).setNotJump());
    }

    private void leftCollision() {
        stopOnOrizontal();
    }

    private void rightCollision() {
        stopOnOrizontal();
    }

    private void lowerCollision() {
        stopOnVertical();
        newState(state.setOnGround().setNotIsJumping());
    }

    private void upperCollision() {
        stopOnVertical();
    }

    private void pushBackRight() {
        newState(state.withVelocity(PUSH_BACK_RIGHT_VEL).setLeft().setNotJump().setNotOnGround().setNotIsJumping());
    }

    private void pushBackLeft() {
        newState(state.withVelocity(PUSH_BACK_LEFT_VEL).setRight().setNotJump().setNotOnGround().setNotIsJumping());
    }

    @Override
    public void setState(final PlayerState state) {
        this.state = state;
    }

    @Override
    public PlayerState getUpdatedState() {
        return this.state;
    }

    private void newState(final PlayerState state) {
        this.state = state;
    }

}
