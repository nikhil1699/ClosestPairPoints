import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

/**
 * Class to accept a file of Points and calculate the closest pair of points.
 * @author Nikhil Chauhan, Esha Gavali
 */
public class ClosestPair {

    /**
     * Main Method: Reads input file for the closest pair of points calculation.
     * pre: Requires file with number of points, and points written.
     * post: The closest pair of points and their distance will be outputted to console.
     */
    public static void main(String[] args) {

        //TODO: remember to remove 'src' before submitting
        final String inputFileName = "src/program2data.txt";
        FileReader fileReader;
        BufferedReader bufferedReader;
        int numberOfPoints;
        try {
            fileReader = new FileReader(inputFileName);
            bufferedReader = new BufferedReader(fileReader);
            numberOfPoints = Integer.parseInt(bufferedReader.readLine().trim());
            if (numberOfPoints == 0)
                throw new IllegalArgumentException("Input file is empty");
            if (numberOfPoints == 1)
                throw new IllegalArgumentException("Incorrect input format");
            Point[] points = getInputFromFile(bufferedReader,numberOfPoints);

            ClosestPairAlgorithm closestPairs = new ClosestPairAlgorithm();
            //calculate closest pair minimum distance and output intermediate steps.
            closestPairs.minimumDistanceBetweenPoints(points,numberOfPoints);
        }
        catch (IOException e) {
            System.err.println("Input Error.");
        }
    }

    /**
     * Reads point coordinates from the file.
     * pre: Correctly formatted file.
     * post: Returns an array of Points.
     */
    private static Point[] getInputFromFile(BufferedReader bufferedReader, int numberOfPoints) throws IOException {
        Point[] points = new Point[numberOfPoints];
        for (int i = 0; i < numberOfPoints; i++) {
            String[] lines = bufferedReader.readLine().trim().split("\\s+");
            double xCord = Double.parseDouble(lines[0]);
            double yCord = Double.parseDouble(lines[1]);
            Point dummy = new Point(xCord,yCord);
            points[i] = dummy;
        }
        return points;
    }
}
