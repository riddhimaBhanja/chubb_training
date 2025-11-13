import java.io.*;
import java.util.regex.*;

public class CountIndiaWhile_13Nov {
    public static void main(String[] args) {
        // Use the full path of your file
        String filename = "C:\\Users\\KIIT\\Downloads\\chubb_workspace\\CountIndia_13Nov\\text.txt";

        Pattern p = Pattern.compile("\\bIndia\\b", Pattern.CASE_INSENSITIVE);
        long totalCount = 0;
        long totalLines = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {  // read line by line
                totalLines++;
                Matcher m = p.matcher(line);
                while (m.find()) {  // count matches in this line
                    totalCount++;
                }
            }
            System.out.println("=== WHILE LOOP VERSION ===");
            System.out.println("File: " + filename);
            System.out.println("Lines read: " + totalLines);
            System.out.println("Total 'India' count: " + totalCount);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
