package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import supson.common.impl.Vect2dImpl;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.entity.impl.moveable.player.PlayerState;
import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.common.impl.Pos2dImpl;

/**
 * This class tests the player class.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestPlayer {

    private static final long FRAME_RATE = 20;

    private Player plr;
    private double acc;

    // CHECKSTYLE: MagicNumber OFF

    /**
     * This method set the acceleration of the entity, so we can use it in tests
     * without using a magic number.
     */
    @BeforeAll
    void init() {
        this.plr = new Player(new Pos2dImpl(0, 0));
        plr.setState(new PlayerState(plr.getState().vel(), true, false,
        false, false, false, false));
        plr.move(FRAME_RATE);
        acc = plr.getVelocity().x();
    }

    /**
     * This method reset the state of the player.
     */
    @BeforeEach
    void initMovingValues() {
        plr.setState(new PlayerState(new Vect2dImpl(0, 0),
            false, false, false, false, false, false));
    }

    /**
     * This method tests the correct moving of the player.
     */
    @Test
    void testMove() {
        // move right
        plr.setState(plr.getState().setRight());
        plr.move(FRAME_RATE);
        plr.move(FRAME_RATE);
        plr.move(FRAME_RATE);
        assertEquals(3 * acc, plr.getVelocity().x());
        //move left
        plr.setState(new PlayerState(new Vect2dImpl(0, 0), false, true,
        false, false, false, false));
        plr.setState(plr.getState().withVelocity(new Vect2dImpl(0, 0)).setLeft().setNotRight());
        plr.move(FRAME_RATE);
        plr.move(FRAME_RATE);
        plr.move(FRAME_RATE);
        assertEquals(-3 * acc, plr.getVelocity().x());
    }

    /**
     * This method tests the correct jumping of the player.
     */
    @Test
    void testJump() {
        Pos2d initialPos = plr.getPosition();
        Vect2d initialVel = plr.getVelocity();
        plr.setState(plr.getState().setJump().setOnGround());
        plr.move(FRAME_RATE);
        assertTrue(plr.getPosition().y() > initialPos.y());
        assertTrue(plr.getVelocity().y() > initialVel.y());     //player can jump

        initialPos = plr.getPosition();
        initialVel = plr.getVelocity();
        plr.setState(plr.getState().withVelocity(new Vect2dImpl(0, 0)).setJump().setNotOnGround());
        plr.move(FRAME_RATE);
        assertTrue(plr.getVelocity().y() < initialPos.y());  //player cannot jump, and falls because of gravity
        assertTrue(plr.getVelocity().y() < initialVel.y());  //player velocity has decreased because of gravity
    }

    /**
     * This method tests the score methods.
     */
    @Test
    void testScore() {
        plr.incrementScore(300);
        assertEquals(300, plr.getScore());
        plr.incrementScore(500);
        assertEquals(800, plr.getScore());
        plr.incrementScore(-150);
        assertEquals(650, plr.getScore());
    }

    /**
     * This method tests the lives methods.
     */
    @Test
    void testLives() {
        assertEquals(3, plr.getLife());
        plr.incrementLife(1);
        assertEquals(3, plr.getLife());
        plr.incrementLife(-1);
        assertEquals(2, plr.getLife());
        plr.incrementLife(1);
        assertEquals(3, plr.getLife());
    }

}
