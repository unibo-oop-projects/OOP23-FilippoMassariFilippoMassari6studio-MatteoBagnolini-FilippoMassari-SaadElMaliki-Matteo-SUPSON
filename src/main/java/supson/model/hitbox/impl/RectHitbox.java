package supson.model.hitbox.impl;

import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.model.hitbox.api.Hitbox;

/**
 * This class, which implements hitbox, models a rectangular hitbox.
 */
public final class RectHitbox implements Hitbox {

    private Pos2d pos;
    private final int height;
    private final int width;

    /**
     * Constructor that creates a rectangular hitbox, with the starting position (center),
     * height and width. The position of the hitbox can be moved with the appropriate method.
     * @param pos the center of the rectangle that represents the hitbox
     * @param height the height of the rectangle that represents the hitbox
     * @param width the height of the rectangle that represents the hitbox
     */
    public RectHitbox(final Pos2d pos, final int height, final int width) {
        this.pos = pos;
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean isCollidingWith(final Hitbox box) {
        final Pos2d ur = getURCorner();
        final Pos2d ll = getLLCorner();
        final Pos2d ur1 = box.getURCorner();
        final Pos2d ll1 = box.getLLCorner();
        return  ll.x() <= ur1.x() && ll1.x() <= ur.x()
            && ll.y() <= ur1.y() && ll1.y() <= ur.y();
    }

    @Override
    public void setPosition(final Pos2d newPosition) {
        this.pos = newPosition;
    }

    @Override
    public Pos2d getURCorner() {
        return new Pos2dImpl(pos.x() + (float) width / 2, pos.y() + (float) height / 2);
    }

    @Override
    public Pos2d getLLCorner() {
        return new Pos2dImpl(pos.x() - (float) width / 2, pos.y() - (float) height / 2.0);
    }

}
