package supson.model.entity.impl.moveable.player;

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

    /**
     * This method retuns a new instance of PlayerState, with the same flags of the calling
     * object, but with a new velocity.
     * @param velocity the new velocity.
     * @return a new instance with the same flags of the original object, but with updated velocity.
     */
    public PlayerState withVelocity(final Vect2d velocity) {
        return new PlayerState(velocity, right, left, jump, onGround, isJumping, isInvulnerable);
    }

    /**
     * This method returns an instance of PlayerState, with the right flag set to true.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for right flag.
     */
    public PlayerState setRight() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, true, left, jump, onGround, isJumping, isInvulnerable);
    }

    /**
     * This method returns an instance of PlayerState, with the right flag set to false.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for right flag.
     */

    public PlayerState setNotRight() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, false, left, jump, onGround, isJumping, isInvulnerable);
    }

    /**
     * This method returns an instance of PlayerState, with the left flag set to true.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for left flag.
     */

    public PlayerState setLeft() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, right, true, jump, onGround, isJumping, isInvulnerable);
    }

    /**
     * This method returns an instance of PlayerState, with the left flag set to false.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for left flag.
     */

    public PlayerState setNotLeft() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, right, false, jump, onGround, isJumping, isInvulnerable);
    }

    /**
     * This method returns an instance of PlayerState, with the jump flag set to true.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for jump flag.
     */
    public PlayerState setJump() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, right, left, true, onGround, isJumping, isInvulnerable);
    }

    /**
     * This method returns an instance of PlayerState, with the jump flag set to false.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for jump flag.
     */
    public PlayerState setNotJump() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, right, left, false, onGround, isJumping, isInvulnerable);
    }

    /**
     * This method returns an instance of PlayerState, with the onGround flag set to true.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for onGround flag.
     */
    public PlayerState setOnGround() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, right, left, jump, true, isJumping, isInvulnerable);
    }

    /**
     * This method returns an instance of PlayerState, with the onGround flag set to false.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for onGround flag.
     */
    public PlayerState setNotOnGround() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, right, left, jump, false, isJumping, isInvulnerable);
    }

    /**
     * This method returns an instance of PlayerState, with the isJumping flag set to true.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for isJumping flag.
     */
    public PlayerState setIsJumping() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, right, left, jump, onGround, true, isInvulnerable);
    }

    /**
     * This method returns an instance of PlayerState, with the isJumping flag set to false.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for isJumping flag.
     */
    public PlayerState setNotIsJumping() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, right, left, jump, onGround, false, isInvulnerable);
    }

    /**
     * This method returns an instance of PlayerState, with the invulnerable flag set to true.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for invulnerable flag.
     */
    public PlayerState setInvulnerable() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, right, left, jump, onGround, isJumping, true);
    }

    /**
     * This method returns an instance of PlayerState, with the invulnerable flag set to false.
     * All the other flags are the same of the caller object.
     * @return a new instance of PlayerState with the same flags of the original object
     * except for invulnerable flag.
     */
    public PlayerState setNotInvulnerable() {     // NOPMD suppressed as this method should be used similar to a setter
        return new PlayerState(vel, right, left, jump, onGround, isJumping, false);
    }

}
