/**
 * Class to represent a point.
 * A point is represented by its x-coordinate and y-coordinate.
 * @author Nikhil Chauhan, Esha Gavali
 */
public class Point {
    final private double xCoordinate;
    final private double yCoordinate;

    /**
     * Constructor
     * pre: None
     * post: Instance variables are initialized
     */
    public Point(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }


    /**
     * Accessor for x-coordinate
     * pre: None
     * post: Returns xCoordinate
     */
    public double getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Accessor for y-coordinate
     * pre: None
     * post: Returns yCoordinate
     */
    public double getyCoordinate() {
        return yCoordinate;
    }

}
