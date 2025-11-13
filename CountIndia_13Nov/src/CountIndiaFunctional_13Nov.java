import java.io.IOException;
import java.nio.file.*;
import java.util.regex.*;

public class CountIndiaFunctional_13Nov {
    public static void main(String[] args) {
       
        String filename = "C:\\Users\\KIIT\\Downloads\\chubb_workspace\\CountIndia_13Nov\\text.txt";

        Pattern p = Pattern.compile("\\bIndia\\b", Pattern.CASE_INSENSITIVE);

        try {
            long totalCount = Files.lines(Paths.get(filename))     
                .flatMap(line -> p.matcher(line).results())       
                .count();                                        

            System.out.println("=== FUNCTIONAL STREAM VERSION ===");
            System.out.println("File: " + filename);
            System.out.println("Total 'India' count: " + totalCount);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
