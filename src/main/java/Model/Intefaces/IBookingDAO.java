package Model.Intefaces;

import Hotel.Booking;
import Hotel.Client;
import Hotel.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Slavko_O on 09.03.2015.
 */
public interface IBookingDAO {
    public void cancelBooking(Booking booking) throws SQLException;
    public List<Booking> getPaymentBooking(Client client) throws SQLException;
}
