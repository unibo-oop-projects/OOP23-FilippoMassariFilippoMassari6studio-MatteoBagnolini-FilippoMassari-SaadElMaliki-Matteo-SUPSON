package supson.model.entity.impl;

import supson.common.api.Observer;
import supson.common.impl.Vect2dImpl;
import supson.model.entity.api.PlayerManager;
import supson.model.hitbox.impl.CollisionEvents;

public class PlayerManagerImpl implements PlayerManager, Observer {

    private final Player player;

    public PlayerManagerImpl(final Player player) {
        this.player = player;
    }

    @Override
    public void moveRight() {
        player.setMoveRight(true);
    }

    @Override
    public void moveLeft() {
        player.setMoveRight(false);
        player.setMoveLeft(true);
    }

    @Override
    public void stop() {
        player.setMoveLeft(false);
        player.setMoveRight(false);
    }

    @Override
    public void jump() {
        player.setIsJumping(true);
    }

    @Override
    public boolean isJumping() {
        return player.isJumping();
    }

    @Override
    public boolean isInvulnerable() {
        return player.isInvulnerable();
    }

    @Override
    public void setInvulnerable() {
        player.setVulnerability(true);
    }

    @Override
    public void setVulnerable() {
        player.setVulnerability(false);
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
        player.setVelocity(new Vect2dImpl(0, player.getVelocity().y()));
        stop();
    }

    private void rightCollision() {
        player.setVelocity(new Vect2dImpl(0, player.getVelocity().y()));
        stop();
    }

    private void lowerCollision() {
        player.setVelocity(new Vect2dImpl(player.getVelocity().x(), 0));
        player.setOnGround(true);
        player.setJump(false);
    }

    private void upperCollision() {
        player.setVelocity(new Vect2dImpl(player.getVelocity().x(), 0));
    }

}
