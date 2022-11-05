import java.util.Comparator;

public class YComparator implements Comparator<Point> {
    @Override
    public int compare(Point p1, Point p2) {
        return Double.compare(p1.getyCoordinate(),p2.getyCoordinate());
    }
}
