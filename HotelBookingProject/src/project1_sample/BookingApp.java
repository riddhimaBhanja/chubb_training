package project1_sample;

import java.util.*;

public class BookingApp {
    public static void main(String[] args) {
        BookingService service = new BookingService();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter your name: ");
            String name = sc.nextLine().trim();
            if (name.isEmpty()) name = "Guest";

            System.out.print("Enter destination (Goa/Manali/Delhi): ");
            String dest = sc.nextLine().trim();

    
            List<Hotel> options = service.findByDestination(dest);
            if (options.isEmpty()) {
                System.out.println("No hotels found for destination: " + dest);
                System.out.println("Available: Goa, Manali, Delhi");
                return;
            }

            Map<Integer, Integer> booked = new LinkedHashMap<>();
            boolean keepBooking = true;

            while (keepBooking) {
                
                System.out.println("\n--- Hotels in " + options.get(0).getDestination() + " ---");
                for (int i = 0; i < options.size(); i++) {
                    Hotel h = options.get(i);
                    System.out.printf("%d) %s (Rooms left: %d)%n",
                        i + 1, h.getName(), h.getRoomsAvailable());
                }

              
                int choice = BookingService.readIntLine(sc, "Select a hotel by number: ");
                if (choice < 1 || choice > options.size()) {
                    System.out.println("Invalid option. Try again.");
                    continue;
                }
                Hotel selected = options.get(choice - 1);

               
                if (selected.bookOneRoom()) {
                    booked.put(selected.getId(), booked.getOrDefault(selected.getId(), 0) + 1);
                    System.out.println("Booked 1 room at \"" + selected.getName()
                        + "\". Rooms left: " + selected.getRoomsAvailable());
                } else {
                    System.out.println("Sorry, \"" + selected.getName() + "\" is sold out.");
                }

             
                System.out.print("Do you want another room? (yes/no): ");
                String ans = sc.nextLine().trim().toLowerCase();
                keepBooking = ans.equals("yes") || ans.equals("y");
            }

            System.out.println("\n===== Booking Summary =====");
            System.out.println("Name: " + name);
            System.out.println("Destination: " + options.get(0).getDestination());

            if (booked.isEmpty()) {
                System.out.println("No rooms booked.");
            } else {
                int totalRooms = 0;
                for (Hotel h : options) {
                    int count = booked.getOrDefault(h.getId(), 0);
                    if (count > 0) {
                        System.out.printf("- %s: %d room(s) booked (Rooms left: %d)%n",
                            h.getName(), count, h.getRoomsAvailable());
                        totalRooms += count;
                    }
                }
                System.out.println("Total rooms booked: " + totalRooms);
            }
            System.out.println("===========================\nThank you for booking!");
        }
    }
}