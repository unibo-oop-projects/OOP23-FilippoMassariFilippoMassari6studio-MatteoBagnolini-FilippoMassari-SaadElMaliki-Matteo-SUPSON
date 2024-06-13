package supson.model.entity.player;

import supson.common.api.Vect2d;

/**
 * This class model the player state. It is immutable, therefore a new instance 
 * should be created every time the state changes.
 * 
 * @param vel the velocity of the player
 * @param right bool flag representing if the player should move right
 * @param left bool flag representing if the player should move left
 * @param jump bool flag representing if the player should jump
 * @param onGround bool flag representing if the player is on ground
 * @param isJumping bool flag representing if the player is jumping
 * @param isInvulnerable bool flag representing if the player is invulnerable
 */
public record PlayerState(Vect2d vel, boolean right, boolean left,
    boolean jump, boolean onGround, boolean isJumping, boolean isInvulnerable) {

    public PlayerState withVelocity(final Vect2d velocity) {
        return new PlayerState(velocity, right, left, jump, onGround, isJumping, isInvulnerable);
    }

    public PlayerState setRight() {
        return new PlayerState(vel, true, left, jump, onGround, isJumping, isInvulnerable);
    }

    public PlayerState setNotRight() {
        return new PlayerState(vel, false, left, jump, onGround, isJumping, isInvulnerable);
    }

    public PlayerState setLeft() {
        return new PlayerState(vel, right, true, jump, onGround, isJumping, isInvulnerable);
    }

    public PlayerState setNotLeft() {
        return new PlayerState(vel, right, false, jump, onGround, isJumping, isInvulnerable);
    }

    public PlayerState setJump() {
        return new PlayerState(vel, right, left, true, onGround, isJumping, isInvulnerable);
    }

    public PlayerState setNotJump() {
        return new PlayerState(vel, right, left, false, onGround, isJumping, isInvulnerable);
    }

    public PlayerState setOnGround() {
        return new PlayerState(vel, right, left, jump, true, isJumping, isInvulnerable);
    }

    public PlayerState setNotOnGround() {
        return new PlayerState(vel, right, left, jump, false, isJumping, isInvulnerable);
    }

    public PlayerState setIsJumping() {
        return new PlayerState(vel, right, left, jump, onGround, true, isInvulnerable);
    }

    public PlayerState setNotIsJumping() {
        return new PlayerState(vel, right, left, jump, onGround, false, isInvulnerable);
    }

    public PlayerState setInvulnerable() {
        return new PlayerState(vel, right, left, jump, onGround, isJumping, true);
    }

    public PlayerState setNotInvulnerable() {
        return new PlayerState(vel, right, left, jump, onGround, isJumping, false);
    }

}
