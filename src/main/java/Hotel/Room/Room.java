package Hotel.Room;

/**
 * Created by Slavko_O on 03.02.2015.
 */
public class Room {

    private int idRoom;
    private int roomNum;
    private int level;
    private int placeNum;
    private double price;
    private boolean available;

    public Room(){}

    public int getRoomNum() {
        return roomNum;
    }
    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getPlaceNum() {
        return placeNum;
    }
    public void setPlaceNum(int placeNum) {
        this.placeNum = placeNum;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }


}
