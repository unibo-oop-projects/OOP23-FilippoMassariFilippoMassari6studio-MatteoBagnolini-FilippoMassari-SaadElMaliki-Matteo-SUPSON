package supson.model.entity.impl.player;

import supson.common.api.Vect2d;

public record PlayerState(Vect2d vel,boolean right, boolean left, boolean jump,
        boolean onGround, boolean isJumping, boolean isInvulnerable) { }