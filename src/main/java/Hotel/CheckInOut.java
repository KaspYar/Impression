package Hotel;

import java.util.Date;

/**
 * Created by Slavko_O on 09.03.2015.
 */
public class CheckInOut {
    private int idCheckInOut;
    private int clientCard;
    private int roomNum;
    private Date checkInDate;
    private Date checkOutDate;

    public int getIdCheckInOut() {
        return idCheckInOut;
    }

    public int getClientCard() {
        return clientCard;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public CheckInOut(int idCheckInOut, int clientCard, int roomNum, Date checkInDate, Date checkOutDate) {

        this.idCheckInOut = idCheckInOut;
        this.clientCard = clientCard;
        this.roomNum = roomNum;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
}
