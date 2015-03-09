package Model.Impl;

import Hotel.Booking;
import Model.Intefaces.IBookingDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Slavko_O on 09.03.2015.
 */
public class ImplBookingDAO implements IBookingDAO {
    private static Logger log = Logger.getLogger(ImplBookingDAO.class.toString());

    public ImplBookingDAO() {
        log.info("ImplBookingDAO constructor");
    }
    public void cancelBooking(Booking booking, Connection connection) throws SQLException {
        log.info("cancelBooking: "+ booking.getIdBooking());
        String query = "DELETE FROM booking WHERE idBooking = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,booking.getIdBooking());

        int count = preparedStatement.executeUpdate();
        if (count !=1) {
            log.error("No booking with id: "+ booking.getIdBooking());
        }
        return;
    }
}
