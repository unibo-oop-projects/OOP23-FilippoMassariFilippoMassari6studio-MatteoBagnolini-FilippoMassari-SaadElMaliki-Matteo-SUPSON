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
public record PlayerState(Vect2d vel, boolean right, boolean left, boolean jump,
        boolean onGround, boolean isJumping, boolean isInvulnerable) { }
