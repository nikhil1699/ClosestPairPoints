import java.util.Comparator;

public class XComparator implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        return Double.compare(p1.getxCoordinate(),p2.getxCoordinate());
    }
}
