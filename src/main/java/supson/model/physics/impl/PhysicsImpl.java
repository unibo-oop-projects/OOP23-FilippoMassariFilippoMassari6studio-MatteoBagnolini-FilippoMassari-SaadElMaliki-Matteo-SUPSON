package supson.model.physics.impl;

import supson.common.api.Vect2d;
import supson.common.impl.Vect2dImpl;
import supson.model.entity.api.MoveableEntity;
import supson.model.physics.api.Physics;

/**
 * This class, which implements the Physics interface, models the physics of
 * an entity of the game. This object should be instantiated in every moveable
 * entity to controll its physics.
 * This class moves the entity by modifying the velocity vector of the entity 
 * it is attached to. 
 */
public final class PhysicsImpl implements Physics {

    private final int maxSpeed;
    private final double acceleration;
    private final double deceleration;
    private final double friction;
    private final int jumpForce;
    private final double gravity;


    /**
     * This is the constructor of the PhysicsImpl class.
     * @param maxSpeed the max speed of the entity
     * @param acceleration the acceleration factor of the entity
     * @param deceleration te deceleration factor of the entity
     * @param friction the friction factor of the entity
     * @param jumpForce the jump force of the entity
     * @param gravity the gravity factor of the entity
     */
    public PhysicsImpl(final int maxSpeed, final double acceleration,
            final double deceleration, final double friction,
            final int jumpForce, final double gravity) {
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.friction = friction;
        this.jumpForce = jumpForce;
        this.gravity = gravity;
    }

    @Override
    public void moveRight(final MoveableEntity entity) {
        final Vect2d oldVel = entity.getVelocity();
        double newXVel = oldVel.x();
        if (oldVel.x() < 0) {   //were moving left

            newXVel += deceleration;        //the player decelerate

        } else if (oldVel.x() < maxSpeed) {

            newXVel += acceleration;  //accelerate

            if (newXVel >= maxSpeed) {

                newXVel = maxSpeed;    //top speed reached

            }
        }
        entity.setVelocity(new Vect2dImpl(newXVel, oldVel.y()));
    }

    @Override
    public void moveLeft(final MoveableEntity entity) {
        final Vect2d oldVel = entity.getVelocity();
        double newXVel = oldVel.x();
        if (oldVel.x() > 0) {   //were moving right

            newXVel -= deceleration;        //the player decelerate

        } else if (oldVel.x() > -maxSpeed) {

            newXVel -= acceleration;   //accelerate

            if (newXVel <= -maxSpeed) {

                newXVel = -maxSpeed;  //top speed reached

            }
        }
        entity.setVelocity(new Vect2dImpl(newXVel, oldVel.y()));
    }
    @Override
    public void applyFriction(final MoveableEntity entity) {
        final Vect2d initialVel = entity.getVelocity();
        final double newXVel = initialVel.x() - Math.min(Math.abs(initialVel.x()), friction) * Math.signum(initialVel.x());
        entity.setVelocity(new Vect2dImpl(newXVel, initialVel.y()));
    }

    @Override
    public void startJumping(final MoveableEntity entity) {
        entity.setVelocity(new Vect2dImpl(entity.getVelocity().x(), jumpForce));
    }

    @Override
    public void applyGravity(final MoveableEntity entity) {
        entity.setVelocity(new Vect2dImpl(entity.getVelocity().x(), entity.getVelocity().y() - gravity));
    }

    @Override
    public void adjustAirSpeed(MoveableEntity entity) {
        Vect2d vel = entity.getVelocity();
        if (Math.abs(vel.x()) > maxSpeed/2) {
            entity.setVelocity(new Vect2dImpl(Math.signum(vel.x())*maxSpeed/2, vel.y()));
        } 
    }

}
