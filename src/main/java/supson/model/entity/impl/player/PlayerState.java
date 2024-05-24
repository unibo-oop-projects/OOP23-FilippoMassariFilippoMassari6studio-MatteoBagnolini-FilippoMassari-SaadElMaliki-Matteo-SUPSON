package supson.model.entity.impl.player;

import supson.common.api.Vect2d;

/**
 * This class model the player state. It is immutable, therefore a new instance 
 * should be created every time the state changes.
 */
public record PlayerState(Vect2d vel,boolean right, boolean left, boolean jump,
        boolean onGround, boolean isJumping, boolean isInvulnerable) { }