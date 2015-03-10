package Hotel;

/**
 * Created by Slavko_O on 09.03.2015.
 */
public class Payment {
    private int idPayment;
    private int idBooking;
    private int idCheckInOut;
    private double totalPrice;
    private int roomNum;

    public int getRoomNum() {
        return roomNum;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getIdCheckInOut() {
        return idCheckInOut;
    }

    public Payment(int idPayment, int idBooking, int idCheckInOut, double totalPrice, int roomNum) {
        this.idPayment = idPayment;
        this.idBooking = idBooking;
        this.idCheckInOut = idCheckInOut;
        this.totalPrice = totalPrice;
        this.roomNum = roomNum;
    }

    public Payment(int idPayment, int idBooking, int idCheckInOut, double totalPrice) {

        this.idPayment = idPayment;
        this.idBooking = idBooking;
        this.idCheckInOut = idCheckInOut;
        this.totalPrice = totalPrice;
    }
}
