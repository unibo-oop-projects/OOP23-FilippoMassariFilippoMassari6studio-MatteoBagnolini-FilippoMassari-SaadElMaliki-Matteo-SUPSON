package supson.common.impl;

import supson.common.api.Pair;

/**
 * A standard generic Pair<X,Y>, with well-implemented getters, hashCode, equals, and toString.
 * 
 * @param <X> the type of the first element of the pair
 * @param <Y> the type of the second element of the pair
 */
public class PairImpl<X, Y> implements Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Constructs a new Pair with the specified values.
     * 
     * @param x the value of the first element
     * @param y the value of the second element
     */
    public PairImpl(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the value of the first element of the pair.
     * 
     * @return the value of the first element of the pair
     */
    public X getX() {
        return x;
    }

    /**
     * Returns the value of the second element of the pair.
     * 
     * @return the value of the second element of the pair
     */
    public Y getY() {
        return y;
    }

    /**
     * Computes and returns the hash code of this Pair.
     * 
     * @return the hash code of this Pair
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    /**
     * Compares this Pair to the specified object for equality.
     * 
     * @param obj the object to compare with this Pair
     * @return true if the specified object is equal to this Pair, false otherwise
     */
    //@SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PairImpl other = (PairImpl) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            if (other.y != null) {
                return false;
            }
        } else if (!y.equals(other.y)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a string representation of this Pair.
     * 
     * @return a string representation of this Pair
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }
}
