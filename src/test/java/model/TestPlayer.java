package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import supson.common.impl.Vect2dImpl;
import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.common.impl.Pos2dImpl;
import supson.model.entity.impl.Player;

public class TestPlayer {

    private static final long FRAME_RATE = 20;

    private Player plr;

    @BeforeEach
    void init() {
        this.plr = new Player(new Pos2dImpl(0, 0), new Vect2dImpl(0, 0), 3);
    }

    @Test
    void testMove1() {
        plr.setMoveRight(true);
        plr.move(FRAME_RATE);
        plr.move(FRAME_RATE);
        plr.move(FRAME_RATE);
        assertEquals(0.03, plr.getVelocity().x());
        plr.setMoveRight(false);
        plr.setMoveLeft(true);
        plr.move(FRAME_RATE);           //player stops here (change in direction)
        plr.move(FRAME_RATE);
        plr.move(FRAME_RATE);
        assertEquals(-0.02, plr.getVelocity().x());
    }

    @Test
    void testJump() {
        Pos2d initialPos = plr.getPosition();
        Vect2d initialVel = plr.getVelocity(); 
        plr.setJump(true);
        plr.setOnGround(true);
        plr.move(FRAME_RATE);
        assertTrue(plr.getPosition().y() > initialPos.y());
        assertTrue(plr.getVelocity().y() > initialVel.y());     //player can jump

        initialPos = plr.getPosition();
        initialVel = plr.getVelocity();
        plr.setJump(true);
        plr.setOnGround(false);
        plr.move(FRAME_RATE);
        assertTrue(plr.getVelocity().y() > initialPos.y());  //player cannot jump, but move up beacuse of initial vel
        assertTrue(plr.getVelocity().y() < initialVel.y());  //player velocity has decreased

        //again no jump
        plr.move(FRAME_RATE);
        assertTrue(plr.getVelocity().y() > initialPos.y());  //player cannot jump, but move up beacuse of initial vel
        assertTrue(plr.getVelocity().y() < initialVel.y());  //player velocity has decreased

        initialPos = plr.getPosition();
        initialVel = plr.getVelocity();
        plr.setJump(true);
        plr.setOnGround(true);
        plr.move(FRAME_RATE);
        assertTrue(plr.getPosition().y() > initialPos.y());
        assertTrue(plr.getVelocity().y() > initialVel.y());
    }

    @Test
    void testScore() {
        plr.setScore(300);
        assertEquals(300, plr.getScore());
    }

}
