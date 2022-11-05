import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

public class ClosestPair {
    public static void main(String[] args) {
        final String inputFileName = "src/program2trivialdata.txt";
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
            Algorithm closestPairs = new Algorithm();
            closestPairs.minimumDistanceBetweenPoints(points,numberOfPoints);
        }
        catch (IOException e) {
            System.err.println("Input Error.");
        }
    }



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
