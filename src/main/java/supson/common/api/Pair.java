package supson.common.api;

/**
 * Interface to represent a generic pair of values.
 * 
 * @param <X> the type of the first element of the pair
 * @param <Y> the type of the second element of the pair
 */
public interface Pair<X, Y> {

    /**
     * Returns the value of the first element of the pair.
     * 
     * @return the value of the first element of the pair
     */
    X getX();

    /**
     * Returns the value of the second element of the pair.
     * 
     * @return the value of the second element of the pair
     */
    Y getY();

    /**
     * Computes and returns the hash code of the object.
     * 
     * @return the hash code of the object
     */
    @Override
    int hashCode();

    /**
     * Checks if the object is equal to another specified object.
     * 
     * @param obj the object to compare with this pair
     * @return true if the two objects are equal, false otherwise
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns a textual representation of the object.
     * 
     * @return a string representing the object
     */
    @Override
    String toString();
}
