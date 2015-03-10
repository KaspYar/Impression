package Model.Intefaces;

import Hotel.Booking;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Slavko_O on 09.03.2015.
 */
public interface IBookingDAO {
    public void cancelBooking(Booking booking) throws SQLException;
}
