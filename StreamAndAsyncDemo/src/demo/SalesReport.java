package demo;

import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class SalesReport {
    public static void main(String[] args) throws IOException {
        // Create sample CSV
        Path file = Paths.get("sales.csv");
        Files.write(file, List.of(
                "Product,Quantity,Price",
                "Laptop,2,50000",
                "Mouse,5,500",
                "Keyboard,3,1500",
                "Monitor,1,12000"
        ));

        // Read CSV using Streams
        try (Stream<String> lines = Files.lines(file)) {
            double totalSales = lines
                    .skip(1) // skip header
                    .map(line -> line.split(","))
                    .mapToDouble(arr -> Integer.parseInt(arr[1]) * Double.parseDouble(arr[2]))
                    .sum();

            System.out.println("💰 Total Sales: ₹" + totalSales);
        }
    }
}
