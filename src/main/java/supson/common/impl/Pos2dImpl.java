package supson.common.impl;

import supson.common.api.Pos2d;
import supson.common.api.Vect2d;

/**
 * This class implements the interface Pos2d, that represent a point (or position)
 * in a 2d space.
 * @param x the x coordinate of the point
 * @param y the y coordinate of the point
 */
public record Pos2dImpl(double x, double y) implements Pos2d {

    @Override
    public double getdistance(final Pos2d p) {
        return Math.sqrt((this.x - p.x()) * (this.x - p.x()) + (this.y - p.y() * (this.y - p.y())));
    }

    @Override
    public Pos2d sum(final Vect2d vect) {
        return new Pos2dImpl(this.x + vect.x(), this.y + vect.y());
    }
}
