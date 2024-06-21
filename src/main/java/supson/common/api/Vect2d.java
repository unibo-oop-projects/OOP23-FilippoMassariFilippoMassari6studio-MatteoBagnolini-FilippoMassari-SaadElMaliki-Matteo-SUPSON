package supson.common.api;

/**
 * This interface models a vector in a 2 dimensional space.
 */
public interface Vect2d {

    /**
     * @return the x component of the vector
     */
    double x();

    /**
     * @return the y component of the vector
     */
    double y();

    /**
     * @return the module of the vector
     */
    double module();

    /**
     * @return a new Vect2d, which is the normalization of the current vector 
     */
    Vect2d normalized();

    /**
     * @param vect the vector to be summed
     *
     * @return a new Vect2d, obtained by summing the current vector and
     *         the input vector
     */
    Vect2d sum(Vect2d vect);

    /**
     * @param factor the multiplication factor
     * @return a new Vect2d, which components are obtained by multiplying 
     *         the current vector components by the multiplication factor
     */
    Vect2d mul(double factor);

}
