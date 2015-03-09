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
    private boolean avaliable;

    public int getRoomNum() {
        return roomNum;
    }

    public boolean isAvaliable() {
        return avaliable;
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
        this.avaliable = avaliable;
    }
}
