package project1_sample;

public class Hotel {
    private final int id;
    private final String name;
    private final String destination;
    private int roomsAvailable;

    public Hotel(int id, String name, String destination, int initialRooms) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.roomsAvailable = initialRooms;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDestination() { return destination; }
    public int getRoomsAvailable() { return roomsAvailable; }

    /** Books exactly one room if available. Returns true if success. */
    public boolean bookOneRoom() {
        if (roomsAvailable <= 0) return false;
        roomsAvailable--;
        return true;
    }
}