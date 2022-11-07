import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

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
            System.err.println("Input Error. " + e.getMessage());
        }
    }

    /**
     * Reads point coordinates from the file.
     * pre: Correctly formatted file.
     * post: Returns an array of Points.
     */
    private static Point[] getInputFromFile(BufferedReader bufferedReader, int numberOfPoints) throws IOException {
        Point[] points = new Point[numberOfPoints];
        String line;
        for (int i = 0; i < numberOfPoints; i++) {
            line = getNextStringIfValid(bufferedReader);
            if(!Objects.equals(line, "")) {
                String[] lines = line.trim().split("\\s+");
                double xCoord = Double.parseDouble(lines[0]);
                double yCoord = Double.parseDouble(lines[1]);
                Point point = new Point(xCoord, yCoord);
                points[i] = point;
            } else {
                throw new IOException("Entries don't match number of points.");
            }
        }

        String extraLine = getNextStringIfValid(bufferedReader);
        if(!Objects.equals(extraLine, "")){
            throw new IOException("Entries don't match number of points.");
        }
        return points;
    }

    /**
     * Check if next line has a pair of points.
     * pre: Buffered reader is allocated.
     * post: Returns string containing a pair of points if exists.
     */
    private static String getNextStringIfValid(BufferedReader bufferedReader) throws IOException {
        String line;
        if((line = bufferedReader.readLine())!= null && !Objects.equals(line, "")) {
            return line;
        } else {
            return "";
        }
    }
}
