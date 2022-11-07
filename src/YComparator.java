import java.util.Comparator;

/**
 * Class to override comparator interface to compare points according to y-coordinate of Point.
 * @author Nikhil Chauhan, Esha Gavali
 */
public class YComparator implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        return Double.compare(p1.getyCoordinate(),p2.getyCoordinate());
    }
}
