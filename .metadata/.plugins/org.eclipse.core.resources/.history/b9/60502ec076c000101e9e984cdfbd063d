import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;

public class CountIndiaFunctional {
    public static void main(String[] args) {
        // ✅ Change this to your actual file path
        String filePath = "C:\\Users\\KIIT\\Downloads\\chubb_workspace\\CountIndiaProject\\data.txt";

        try {
            // Read all lines, split into words, filter 'India', and count
            long count = Files.lines(Paths.get(filePath))
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .filter(word -> word.equalsIgnoreCase("India"))
                    .count();

            System.out.println("Number of times 'India' appears: " + count);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
