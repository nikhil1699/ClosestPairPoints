import java.util.Arrays;

import static java.lang.Double.min;

public class Algorithm {
    public void minimumDistanceBetweenPoints(Point[] pointsByX, int numberOfPoints) {
        XComparator comparatorFxn = new XComparator();
        YComparator comparatorFyn = new YComparator();
        Arrays.sort(pointsByX,comparatorFxn);
        Point[] pointsByY = new Point[numberOfPoints];
        System.arraycopy(pointsByX, 0, pointsByY, 0, numberOfPoints);
        Arrays.sort(pointsByY, comparatorFyn);
        divideAndConquerHelper(pointsByX, pointsByY, 0, numberOfPoints-1);
    }

    private double divideAndConquerHelper(Point[] pointsByX, Point[] pointsByY, int low, int high) {
        int numberOfPoints = pointsByX.length;
        if (numberOfPoints <= 3){
            return minBruteforceDistance(pointsByX,low,high,numberOfPoints);
        }

        int mid = (numberOfPoints+1)/2;
        int midIndex = low+mid;
        Point middleElement = pointsByX[mid];
        Point[] leftSetOfX = new Point[mid];
        Point[] leftSetOfY = new Point[mid];
        Point[] rightSetOfX = new Point[numberOfPoints - mid];
        Point[] rightSetOfY = new Point[numberOfPoints - mid];
        Point[] range = new Point[numberOfPoints];
        partitionOfX(pointsByX, leftSetOfX, rightSetOfX);
        partitionOfY(pointsByY, leftSetOfY, rightSetOfY, middleElement);
        double distLeft = divideAndConquerHelper(leftSetOfX, leftSetOfY, low, midIndex-1);
        double distRight = divideAndConquerHelper(rightSetOfX, rightSetOfY, midIndex, high);
        double distanceDelta = min(distRight,distLeft);
        int rangeLength = 0;
        for (int i = 0; i < numberOfPoints; i++) {
            if (pointsByY[i].getxCoordinate() - middleElement.getxCoordinate() < distanceDelta){
                range[rangeLength] = pointsByY[i];
                rangeLength+=1;

            }
        }
        return getMinDistanceInRange(range,rangeLength,low,high,distanceDelta);
    }

    private double minBruteforceDistance(Point[] pointsByX, int low, int high, int numberOfPoints) {
        double minDistance = Double.POSITIVE_INFINITY;
        for(int i = 0;i<numberOfPoints;++i){
            for(int j = i+1; j<numberOfPoints;++j){
                double distance = distanceBetweenTwoPoints(pointsByX[i],pointsByX[j]);
                if(distance < minDistance){
                    minDistance = distance;
                }
            }
        }
        System.out.println("D["+low+","+high+"]: " + String.format("%.4f",minDistance));
        return minDistance;
//        System.out.println(minDistance);
    }

    private double getMinDistanceInRange(Point[] range, int rangeLength, int low, int high, double distanceDelta) {
        double currMin = distanceDelta;
        for (int i = 0; i < rangeLength; i++) {
            for (int j = i+1; (j < rangeLength) && (j<=i+7); ++j) {
                if(distanceBetweenTwoPoints(range[i],range[j]) < currMin){
                    currMin = distanceBetweenTwoPoints(range[i],range[j]);
                }
            }
        }
        System.out.println("D["+low+","+high+"]: " + String.format("%.4f",currMin));
        return currMin;
    }

    private void partitionOfY(Point[] pointsByY, Point[] lY, Point[] rY, Point middleElement) {
        int left = 0;
        int right = 0;
        for (Point point : pointsByY) {
            if (point.getxCoordinate() < middleElement.getxCoordinate() && left < lY.length) {
                lY[left++] = point;
            } else {
                rY[right++] = point;
            }
        }
    }

    private void partitionOfX(Point[] pointsByX, Point[] leftSetOfX, Point[] rightSetOfX) {
        for (int i = 0; i < pointsByX.length; i++) {
            if (i < leftSetOfX.length){
                leftSetOfX[i] = pointsByX[i];
            }else{
                rightSetOfX[i-leftSetOfX.length] = pointsByX[i];
            }
            
        }
    }
    private static double distanceBetweenTwoPoints(Point x, Point y) {
        return Math.sqrt(Math.pow((x.getxCoordinate()-y.getxCoordinate()),2) + Math.pow((x.getyCoordinate()-y.getyCoordinate()),2));
    }
}
