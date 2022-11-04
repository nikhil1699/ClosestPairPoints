import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ClosestPair {
    public static void main(String[] args) throws FileNotFoundException {
        final String inputFileName = "program2trivialdata.txt";
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        int numberOfRecords = 0;
        try {
            fileReader = new FileReader(inputFileName);
            bufferedReader = new BufferedReader(fileReader);
            numberOfRecords = Integer.parseInt(bufferedReader.readLine().trim());
            if (numberOfRecords == 0)
                throw new IllegalArgumentException("File is empty");
            if (numberOfRecords == 1)
                throw new IllegalArgumentException("Number of coordinates should be " + "at least two to find distance");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}