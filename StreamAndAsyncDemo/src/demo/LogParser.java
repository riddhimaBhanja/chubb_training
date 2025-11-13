package demo;

import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class LogParser {
    public static void main(String[] args) throws IOException {
        Path logFile = Paths.get("app.log");
        Files.write(logFile, List.of(
                "INFO: Server started",
                "ERROR: Database not found",
                "INFO: Request handled",
                "ERROR: Timeout while connecting",
                "INFO: Server stopped"
        ));

        System.out.println("📜 Error Logs:");
        try (Stream<String> lines = Files.lines(logFile)) {
            lines.filter(line -> line.startsWith("ERROR"))
                 .map(line -> line.toUpperCase())
                 .forEach(System.out::println);
        }
    }
}
