import java.util.Arrays;

import static java.lang.Double.min;


/**
 * Class to contain all methods related to the closest pair algorithm.
 * @author Nikhil Chauhan, Esha Gavali
 */
public class ClosestPairAlgorithm {

    /**
     * Starting point of algorithm.
     * Sorts arrays by x and y coordinates and begins divide and conquer recursion.
     * pre: Points array is populated with more than 1 point.
     * post: The closest pair of points and their distance will be outputted to console.
     */
    public void minimumDistanceBetweenPoints(Point[] pointsSortedByX, int numberOfPoints) {

        XComparator comparatorFxn = new XComparator();
        YComparator comparatorFyn = new YComparator();

        Arrays.sort(pointsSortedByX,comparatorFxn);

        Point[] pointsSortedByY = new Point[numberOfPoints];
        System.arraycopy(pointsSortedByX, 0, pointsSortedByY, 0, numberOfPoints);
        Arrays.sort(pointsSortedByY, comparatorFyn);

        divideAndConquerHelper(pointsSortedByX, pointsSortedByY, 0, numberOfPoints-1);
    }

    /**
     * Method for recursively finding the closest pair of points.
     * pre: Points are sorted by both x and y coordinates in separate arrays.
     * post: The closest pair of points and their distance will be outputted to console.
     */
    private double divideAndConquerHelper(Point[] pointsSortedByX, Point[] pointsSortedByY, int low, int high) {
        int numberOfPoints = pointsSortedByX.length;

        if (numberOfPoints <= 3){
            return minBruteforceDistance(pointsSortedByX,low,high,numberOfPoints);
        }

        int mid = (numberOfPoints+1)/2;
        int midIndex = low+mid;
        Point middleElement = pointsSortedByX[mid];
        Point[] leftSetOfX = new Point[mid];
        Point[] leftSetOfY = new Point[mid];
        Point[] rightSetOfX = new Point[numberOfPoints - mid];
        Point[] rightSetOfY = new Point[numberOfPoints - mid];
        Point[] pointsInDeltaRange = new Point[numberOfPoints];

        partitionOfX(pointsSortedByX, leftSetOfX, rightSetOfX);
        partitionOfY(pointsSortedByY, leftSetOfY, rightSetOfY, middleElement);

        double distLeft = divideAndConquerHelper(leftSetOfX, leftSetOfY, low, midIndex-1);
        double distRight = divideAndConquerHelper(rightSetOfX, rightSetOfY, midIndex, high);

        double delta = min(distRight,distLeft);

        int numPointsInDeltaRange = 0;
        for (int i = 0; i < numberOfPoints; i++) {
            if (pointsSortedByY[i].getxCoordinate() - middleElement.getxCoordinate() < delta){
                pointsInDeltaRange[numPointsInDeltaRange] = pointsSortedByY[i];
                numPointsInDeltaRange+=1;

            }
        }
        return getMinDistanceInRange(pointsInDeltaRange,numPointsInDeltaRange,low,high,delta);
    }

    /**
     * Finds minimum distance for case where there are 3 or fewer points.
     * pre: Array of points should be populated.
     * post: Returns minimum distance between points. Prints the pair of points with the minimum distance
     *       along with their distance to the console.
     */
    private double minBruteforceDistance(Point[] pointsSortedByX, int low, int high, int numberOfPoints) {

        double minDistance = Double.POSITIVE_INFINITY;
        for(int i = 0; i < numberOfPoints; ++i){
            for(int j = i+1; j < numberOfPoints; ++j){
                double distance = distanceBetweenTwoPoints(pointsSortedByX[i],pointsSortedByX[j]);
                if(distance < minDistance){
                    minDistance = distance;
                }
            }
        }
        System.out.println("D["+low+","+high+"]: " + String.format("%.4f",minDistance));
        return minDistance;
    }

    /**
     * Finds minimum distance between points lying in the delta range, across the median line.
     * pre: Sorted array of points lying in the delta range.
     * post: Returns minimum distance between points. Prints the pair of points with the minimum distance
     *       along with their distance to the console.
     */
    private double getMinDistanceInRange(Point[] pointsInDeltaRange, int numPoints, int low, int high, double delta) {
        double currrentMin = delta;
        for (int i = 0; i < numPoints; i++) {
            for (int j = i+1; (j < numPoints) && (j<=i+7); ++j) {
                if(distanceBetweenTwoPoints(pointsInDeltaRange[i],pointsInDeltaRange[j]) < currrentMin){
                    currrentMin = distanceBetweenTwoPoints(pointsInDeltaRange[i],pointsInDeltaRange[j]);
                }
            }
        }
        System.out.println("D["+low+","+high+"]: " + String.format("%.4f",currrentMin));
        return currrentMin;
    }

    /**
     * Partitions points sorted by y-coordinate according the median x-coordinate.
     * pre: Array of points should be sorted by the y-coordinate
     * post: Populates leftSetOfY and rightSetOfY; divisions of the passed array.
     */
    private void partitionOfY(Point[] pointsSortedByY, Point[] leftSetOfY, Point[] rightSetOfY, Point middleElement) {
        int left = 0;
        int right = 0;
        for (Point point : pointsSortedByY) {
            if (point.getxCoordinate() < middleElement.getxCoordinate() && left < leftSetOfY.length) {
                leftSetOfY[left++] = point;
            } else {
                rightSetOfY[right++] = point;
            }
        }
    }

    /**
     * Partitions points sorted by x-coordinate according the median x-coordinate.
     * pre: Array of points should be sorted by the x-coordinate
     * post: Populates leftSetOfX and rightSetOfX; divisions of the passed array.
     */
    private void partitionOfX(Point[] pointsByX, Point[] leftSetOfX, Point[] rightSetOfX) {
        for (int i = 0; i < pointsByX.length; i++) {
            if (i < leftSetOfX.length){
                leftSetOfX[i] = pointsByX[i];
            }else{
                rightSetOfX[i-leftSetOfX.length] = pointsByX[i];
            }
            
        }
    }

    /**
     * Calculates Euclidean distance between 2 points.
     * pre: Both coordinates are populated for both points.
     * post: Returns distance between 2 points.
     */
    private static double distanceBetweenTwoPoints(Point x, Point y) {
        return Math.sqrt(Math.pow((x.getxCoordinate()-y.getxCoordinate()),2) +
                Math.pow((x.getyCoordinate()-y.getyCoordinate()),2));
    }
}
