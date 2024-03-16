package supson.common.api;

/**
 * This interface models a point in a 2 dimensional space.
 */
public interface Pos2d {

    /**
     * @return the x coordinate of the point 
     */
    double x();
    /**
     * @return the y coordinate of the point
     */
    double y();

    /**
     * 
     * @param p the point to compute the distance with
     * @return the distance between two points
     */
    double getdistance(Pos2d p);

    /**
     * This method shifts (sums) the point for a given vector.
     * @param vect the vector to be summed
     * @return the new point, representing the sum of the current point
     *         and the vector
     */
    Pos2d sum(Vect2d vect);
}
