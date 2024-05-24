package supson.model.entity.impl;

import supson.model.entity.api.PlayerManager;

public class PlayerManagerImpl implements PlayerManager {

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
    
}
