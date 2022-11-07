import java.util.Comparator;

/**
 * Class to override comparator interface to compare points according to x-coordinate of Point.
 * @author Nikhil Chauhan, Esha Gavali
 */
public class XComparator implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        return Double.compare(p1.getxCoordinate(),p2.getxCoordinate());
    }
}
