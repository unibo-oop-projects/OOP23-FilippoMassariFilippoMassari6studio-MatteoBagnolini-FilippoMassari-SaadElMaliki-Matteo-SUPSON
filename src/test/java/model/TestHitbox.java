package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.model.hitbox.impl.RectHitbox;

/**
 * THis class tests hitboxes.
 */
class TestHitbox {
    
    private RectHitbox hb1;
    private RectHitbox hb2;
    private RectHitbox hb3;
    private RectHitbox hb4;

    @BeforeEach
    void init() {
        hb1 = new RectHitbox(new Pos2dImpl(1.5, 2), 4, 3);
        hb2 = new RectHitbox(new Pos2dImpl(3.5, 4.5), 3, 3);
        hb3 = new RectHitbox(new Pos2dImpl(7, 4.5), 5, 6);
        hb4 = new RectHitbox(new Pos2dImpl(11, 4.5), 5, 2);
    }

    @Test
    void testCorners() {
        final Pos2d ll1 = hb1.getLLCorner();
        final Pos2d ur1 = hb1.getURCorner();
        assertTrue(ll1.equals(new Pos2dImpl(0, 0)) && ur1.equals(new Pos2dImpl(3, 4)));

        final Pos2d ll2 = hb2.getLLCorner();
        final Pos2d ur2 = hb2.getURCorner();
        assertTrue(ll2.equals(new Pos2dImpl(2, 3)) && ur2.equals(new Pos2dImpl(5, 6)));

        final Pos2d ll3 = hb3.getLLCorner();
        final Pos2d ur3 = hb3.getURCorner();
        assertTrue(ll3.equals(new Pos2dImpl(4, 2)) && ur3.equals(new Pos2dImpl(10, 7)));

        final Pos2d ll4 = hb4.getLLCorner();
        final Pos2d ur4 = hb4.getURCorner();
        assertTrue(ll4.equals(new Pos2dImpl(10, 2)) && ur4.equals(new Pos2dImpl(12, 7)));
    }

    @Test
    void testCollisions() {
        assertTrue(hb1.isCollidingWith(hb2));
        assertFalse(hb1.isCollidingWith(hb3));
        assertTrue(hb2.isCollidingWith(hb3));
        assertFalse(hb4.isCollidingWith(hb1));
        assertFalse(hb4.isCollidingWith(hb2));
        assertTrue(hb4.isCollidingWith(hb3));      //here the two rects share the same side (left side)
        
    }
}
