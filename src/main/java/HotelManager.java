import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    public static void main(String[] args) {
        Logger.getInstance().log("Managing hotel...");

        // create hotel rooms
        // create hotel floors
        // add hotel rooms to hotel floors
        // take actions on rooms and floors and examine your output to ensure you implemented the desired
        // behaviors

        HotelFloor baseFloor = new HotelFloor();
        HotelFloor start = baseFloor;

        for(int f = 0; f < 10; f++){
            HotelFloor newFloor = new HotelFloor();
            for(int r = 0; r < 10; r++){
                HotelRoomInterface newRoom = new HotelRoom();
                newRoom.book("Guest " + String.valueOf(f) + String.valueOf(r));
                newFloor.addHotelRoom(newRoom);
            }
            Logger.getInstance().log("Floor ----- " +  String.valueOf(f));
            baseFloor.addFloor(newFloor);
            baseFloor = newFloor;
        }
        while(start.getNextFloor() != null){
            start.clean();
            start = start.getNextFloor();
        }
    }
}

interface HotelRoomInterface {
    void book(String guestName);
    void clean();
}

class HotelRoom implements HotelRoomInterface {
    public void book(String guestName) {
        Logger.getInstance().log("Booked a room for " + guestName);
    }

    public void clean() {
        Logger.getInstance().log("Cleaned room");
    }
}

class HotelFloor implements HotelRoomInterface {
    private List<HotelRoomInterface> hotelRooms = new ArrayList<HotelRoomInterface>();
    private HotelFloor nextFloor;
    public void book(String guestName) {
        hotelRooms.forEach(child -> {
            child.book(guestName);
        });
    }
    public HotelFloor getNextFloor(){
        return nextFloor;
    }
    public void clean() {
        hotelRooms.forEach(child -> child.clean());
    }

    public void addFloor(HotelFloor hotelFloor){
        nextFloor = hotelFloor;
    }

    public void addHotelRoom(HotelRoomInterface hotelRoom) {
        hotelRooms.add(hotelRoom);
    }

    public void removeHotelRoom(HotelRoomInterface hotelRoom) {
        hotelRooms.remove(hotelRoom);
    }
}