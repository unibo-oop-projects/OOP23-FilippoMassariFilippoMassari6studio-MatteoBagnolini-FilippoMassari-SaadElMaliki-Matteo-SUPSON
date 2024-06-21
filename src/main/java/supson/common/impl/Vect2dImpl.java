package supson.common.impl;

import supson.common.api.Vect2d;

/**
 * This class implements the interface Vect2d, which represent a vector
 * in a 2d space.
 * @param x the x component of the vector
 * @param y the y component of the vector 
 */
public record Vect2dImpl(double x, double y) implements Vect2d {

    @Override
    public double module() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    @Override
    public Vect2d normalized() {
        final double module = this.module();
        return new Vect2dImpl(this.x / module, this.y / module);
    }

    @Override
    public Vect2d sum(final Vect2d vect) {
        return new Vect2dImpl(this.x + vect.x(), this.y + vect.y());
    }

    @Override
    public Vect2d mul(final double factor) {
        return new Vect2dImpl(this.x * factor, this.y * factor);
    }

}
