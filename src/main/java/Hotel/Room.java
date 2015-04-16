package Hotel;

/**
 * Created by Slavko_O on 09.03.2015.
 */
public class Room {
    private int roomNum;
    private String roomType;
    private int level;
    private int placeNum;
    private double price;
    private boolean available;

    public int getRoomNum() {
        return roomNum;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getLevel() {
        return level;
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public double getPrice() {
        return price;
    }

    public String getRoomType() {
        return roomType;
    }

    public Room(int roomNum, String roomType, int level, int placeNum, double price, boolean avaliable) {
        this.roomNum = roomNum;
        this.roomType = roomType;
        this.level = level;
        this.placeNum = placeNum;
        this.price = price;
        this.available = avaliable;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNum=" + roomNum +
                ", roomType='" + roomType + '\'' +
                ", level=" + level +
                ", placeNum=" + placeNum +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
