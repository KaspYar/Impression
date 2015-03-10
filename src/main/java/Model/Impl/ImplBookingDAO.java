package Model.Impl;

import Hotel.Booking;
import Hotel.Client;
import Hotel.Payment;
import Model.Intefaces.IBookingDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slavko_O on 09.03.2015.
 */
public class ImplBookingDAO implements IBookingDAO {
    private static Logger log = Logger.getLogger(ImplBookingDAO.class.toString());
    private Connection connection;

    public ImplBookingDAO(Connection connection) {
        log.info("ImplBookingDAO constructor");
        this.connection = connection;

    }

    public void cancelBooking(Booking booking) throws SQLException {
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

    @Override
    public List<Booking> getPaymentBooking(Client client) throws SQLException {
        ArrayList<Booking> result = new ArrayList<Booking>();
        log.info("getPaymentBooking idClient: "+ client.getClientCard());
        String query = "SELECT * " +
                "FROM payment P INNER JOIN booking B ON P.idBooking = B.idBooking " +
                "WHERE B.clientCard = ? " +
                "ORDER BY P.idPayment DESC ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, client.getClientCard());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            result.add(new Booking(
                    resultSet.getInt("idBooking"), resultSet.getInt("clientCard"),
                    resultSet.getDate("dateFrom"), resultSet.getDate("dateTo")));
        }
        return result;
    }
}
