package Hotel;

import java.util.Date;

/**
 * Created by Slavko_O on 09.03.2015.
 */
public class Booking {
    private int idBooking;
    private int clientCard;
    private Date dateFrom;
    private Date dateTo;

    public int getIdBooking() {
        return idBooking;
    }

    public int getClientCard() {
        return clientCard;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public Booking(int idBooking, int clientCard, Date dateFrom, Date dateTo) {

        this.idBooking = idBooking;
        this.clientCard = clientCard;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }
}
