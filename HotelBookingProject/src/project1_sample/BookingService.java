package project1_sample;

import java.util.*;

public class BookingService {
    private final List<Hotel> hotels;

    public BookingService() {
        hotels = new ArrayList<>(Arrays.asList(
            new Hotel(1, "Sunrise Inn", "Goa", 200),
            new Hotel(2, "Sea Breeze Resort", "Goa", 200),
            new Hotel(3, "Palm Haven", "Goa", 200),
            new Hotel(4, "Mountain View", "Manali", 200),
            new Hotel(5, "Snow Crest Lodge", "Manali", 200),
            new Hotel(6, "Pine Retreat", "Manali", 200),
            new Hotel(7, "City Central", "Delhi", 200),
            new Hotel(8, "Heritage Palace", "Delhi", 200),
            new Hotel(9, "Riverside Hotel", "Delhi", 200)
        ));
    }

  
    public List<Hotel> findByDestination(String dest) {
        List<Hotel> result = new ArrayList<>();
        for (Hotel h : hotels) {
            if (h.getDestination().equalsIgnoreCase(dest)) {
                result.add(h);
            }
        }
        return result;
    }

   
    public static int readIntLine(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}