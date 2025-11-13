import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountIndia {

    // Toggle this to true to count "India" case-insensitively (India, INDIA, india...)
    private static final boolean CASE_INSENSITIVE = true;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java CountIndia <path-to-file>");
            System.exit(1);
        }

        String filePath = args[0];
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            System.err.println("File not found or not a file: " + filePath);
            System.exit(2);
        }

        // build regex for whole word "India"
        String regex = "\\bIndia\\b";
        Pattern pattern = CASE_INSENSITIVE
                ? Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
                : Pattern.compile(regex);

        long totalMatches = 0L;

        // Use InputStreamReader with UTF-8 to support most text files
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                Matcher m = pattern.matcher(line);
                while (m.find()) {
                    totalMatches++;
                }
            }

            System.out.println("Total occurrences of the word \"India\": " + totalMatches);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
            System.exit(3);
        }
    }
}
