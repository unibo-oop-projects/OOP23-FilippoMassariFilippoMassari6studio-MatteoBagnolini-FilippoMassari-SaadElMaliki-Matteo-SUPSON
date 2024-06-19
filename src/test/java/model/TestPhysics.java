package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import supson.common.GameEntityType;
import supson.common.impl.Pos2dImpl;
import supson.common.impl.Vect2dImpl;
import supson.model.entity.impl.moveable.AbstractMoveableEntity;
import supson.model.physics.impl.PhysicsImpl;

/**
 * THis class tests the Physics class.
 */
class TestPhysics {

    // CHECKSTYLE: MagicNumber OFF

    private static final long TIME = 1000;
    private static final int MAX_SPEED = 20;
    private static final double ACC = 1;
    private static final double DEC = 1;
    private static final double FRICTION = 1;
    private static final int JUMP_FORCE = 2;

    private final AbstractMoveableEntity jumpingEntity = new AbstractMoveableEntity(new Pos2dImpl(0, 0), 0, 0,
            GameEntityType.PLAYER, new Vect2dImpl(0, 0), 0, new PhysicsImpl(MAX_SPEED, ACC, DEC, FRICTION, JUMP_FORCE, 0)) {

        @Override
        protected void updateVelocity() {
            getPhysicsComponent().startJumping(this);
        }

    };

    private final AbstractMoveableEntity leftMovingEntity = new AbstractMoveableEntity(new Pos2dImpl(0, 0), 0, 0,
            GameEntityType.PLAYER, new Vect2dImpl(0, 0), 0, new PhysicsImpl(MAX_SPEED, ACC, DEC, FRICTION, JUMP_FORCE, 0)) {

        @Override
        protected void updateVelocity() {
            getPhysicsComponent().moveLeft(this);
        }

    };

    private final AbstractMoveableEntity rightMovingEntity = new AbstractMoveableEntity(new Pos2dImpl(0, 0), 0, 0,
            GameEntityType.PLAYER, new Vect2dImpl(0, 0), 0, new PhysicsImpl(MAX_SPEED, ACC, DEC, FRICTION, JUMP_FORCE, 0)) {

        @Override
        protected void updateVelocity() {
            getPhysicsComponent().moveRight(this);
        }

    };

    private final AbstractMoveableEntity zigzagMovingEntity = new AbstractMoveableEntity(new Pos2dImpl(0, 0), 0, 0,
            GameEntityType.PLAYER, new Vect2dImpl(0, 0), 0, new PhysicsImpl(MAX_SPEED, ACC, DEC, FRICTION, JUMP_FORCE, 0)) {

        private int count;

        @Override
        protected void updateVelocity() {
            if (count % 6 < 3) {                            //entity moves right 3 times, then move left 3 times and so on
                getPhysicsComponent().moveRight(this);
            } else {
                getPhysicsComponent().moveLeft(this);
            }
            count++;
        }

    };

    private final AbstractMoveableEntity frictionEntity = new AbstractMoveableEntity(new Pos2dImpl(0, 0), 0, 0,
            GameEntityType.PLAYER, new Vect2dImpl(0, 0), 0, new PhysicsImpl(MAX_SPEED, ACC, DEC, FRICTION, JUMP_FORCE, 0)) {

        @Override
        protected void updateVelocity() {
            getPhysicsComponent().applyFriction(this);
        }

    };

    private final AbstractMoveableEntity gravityMovingEntity = new AbstractMoveableEntity(new Pos2dImpl(0, 0), 0, 0,
            GameEntityType.PLAYER, new Vect2dImpl(0, 0), 0, new PhysicsImpl(MAX_SPEED, ACC, DEC, FRICTION, JUMP_FORCE, 1)) {

        @Override
        protected void updateVelocity() {
            getPhysicsComponent().applyGravity(this);
        }

    };

    @BeforeEach
    void init() {
        jumpingEntity.setPosition(new Pos2dImpl(0, 0));
        leftMovingEntity.setPosition(new Pos2dImpl(0, 0));
        rightMovingEntity.setPosition(new Pos2dImpl(0, 0));
        zigzagMovingEntity.setPosition(new Pos2dImpl(0, 0));
        gravityMovingEntity.setPosition(new Pos2dImpl(0, 0));
        frictionEntity.setVelocity(new Vect2dImpl(MAX_SPEED, 0));
        jumpingEntity.setVelocity(new Vect2dImpl(0, 0));
        leftMovingEntity.setVelocity(new Vect2dImpl(0, 0));
        rightMovingEntity.setVelocity(new Vect2dImpl(0, 0));
        zigzagMovingEntity.setVelocity(new Vect2dImpl(0, 0));
        gravityMovingEntity.setVelocity(new Vect2dImpl(0, 0));
    }

    @Test
    void testMoveRight() {
        rightMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(1, 0), rightMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(1, 0), rightMovingEntity.getPosition());
        rightMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(2, 0), rightMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(3, 0), rightMovingEntity.getPosition());
        rightMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(3, 0), rightMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(6, 0), rightMovingEntity.getPosition());
    }

    @Test
    void testMoveLeft() {
        leftMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(-1, 0), leftMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(-1, 0), leftMovingEntity.getPosition());
        leftMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(-2, 0), leftMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(-3, 0), leftMovingEntity.getPosition());
        leftMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(-3, 0), leftMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(-6, 0), leftMovingEntity.getPosition());
    }

    @Test
    void testZigZag() {
        zigzagMovingEntity.move(TIME);
        zigzagMovingEntity.move(TIME);
        zigzagMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(3, 0), zigzagMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(6, 0), zigzagMovingEntity.getPosition());
        //here the entity decelerate
        zigzagMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(2, 0), zigzagMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(8, 0), zigzagMovingEntity.getPosition());
        zigzagMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(1, 0), zigzagMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(9, 0), zigzagMovingEntity.getPosition());
        zigzagMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(0, 0), zigzagMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(9, 0), zigzagMovingEntity.getPosition());
        //here the entity stops
        zigzagMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(1, 0), zigzagMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(10, 0), zigzagMovingEntity.getPosition());
    }

    @Test
    void testMaxSpeed() {
        for (int i = 0; i < 25; i++) {
            rightMovingEntity.move(TIME);
        }
        assertEquals(new Vect2dImpl(20, 0), rightMovingEntity.getVelocity());
        for (int i = 0; i < 10; i++) {
            rightMovingEntity.move(TIME);
        }
        assertEquals(new Vect2dImpl(20, 0), rightMovingEntity.getVelocity());
    }

    @Test
    void testFriction() {
        frictionEntity.move(TIME);
        assertEquals(MAX_SPEED - FRICTION, frictionEntity.getVelocity().x());
        frictionEntity.move(TIME);
        assertEquals(MAX_SPEED - 2 * FRICTION, frictionEntity.getVelocity().x());
        frictionEntity.move(TIME);
        assertEquals(MAX_SPEED - 3 * FRICTION, frictionEntity.getVelocity().x());
    }

    @Test
    void testJump() {
        jumpingEntity.move(TIME);
        assertEquals(new Vect2dImpl(0, 2), jumpingEntity.getVelocity());
        assertEquals(new Pos2dImpl(0, 2), jumpingEntity.getPosition());
        jumpingEntity.move(TIME);
        assertEquals(new Vect2dImpl(0, 2), jumpingEntity.getVelocity());
        assertEquals(new Pos2dImpl(0, 4), jumpingEntity.getPosition());
        jumpingEntity.move(TIME);
        assertEquals(new Vect2dImpl(0, 2), jumpingEntity.getVelocity());
        assertEquals(new Pos2dImpl(0, 6), jumpingEntity.getPosition());
    }

    @Test
    void testGravity() {
        gravityMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(0, -1), gravityMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(0, -1), gravityMovingEntity.getPosition());
        gravityMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(0, -2), gravityMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(0, -3), gravityMovingEntity.getPosition());
        gravityMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(0, -3), gravityMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(0, -6), gravityMovingEntity.getPosition());
        gravityMovingEntity.move(TIME);
        assertEquals(new Vect2dImpl(0, -4), gravityMovingEntity.getVelocity());
        assertEquals(new Pos2dImpl(0, -10), gravityMovingEntity.getPosition());
    }

}
