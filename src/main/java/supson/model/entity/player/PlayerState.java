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
public record PlayerState(Vect2d vel, boolean right, boolean left, boolean jump, boolean onGround, boolean isJumping, boolean isInvulnerable) { 

    public PlayerState(Builder builder) {
        this(builder.vel, builder.right, builder.left, builder.jump,
        builder.onGround, builder.isJumping, builder.isInvulnerable);
    }

    public static class Builder {
        private Vect2d vel;
        private boolean right;
        private boolean left;
        private boolean jump;
        private boolean onGround;
        private boolean isJumping;
        private boolean isInvulnerable;

        public Builder(PlayerState state) {
            this.vel = state.vel;
            this.right = state.right;
            this.left = state.left;
            this.jump = state.jump;
            this.onGround = state.onGround;
            this.isJumping = state.isJumping;
            this.isInvulnerable = state.isInvulnerable;
        }

        public Builder vel(Vect2d vel) {
            this.vel = vel;
            return this;
        }

        public Builder right(boolean right) {
            this.right = right;
            return this;
        }

        public Builder left(boolean left) {
            this.left = left;
            return this;
        }

        public Builder jump(boolean jump) {
            this.jump = jump;
            return this;
        }

        public Builder onGround(boolean onGround) {
            this.onGround = onGround;
            return this;
        }

        public Builder isJumping(boolean isJumping) {
            this.isJumping = isJumping;
            return this;
        }

        public Builder isInvulnerable(boolean isInvulnerable) {
            this.isInvulnerable = isInvulnerable;
            return this;
        }

        public PlayerState build() {
            return new PlayerState(this);
        }

    }

}
