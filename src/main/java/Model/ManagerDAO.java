package Model;

import Hotel.Booking;
import Hotel.Client;
import Hotel.Payment;
import Hotel.Room;
import Model.Impl.ImplBookingDAO;
import Model.Impl.ImplClientDAO;
import Model.Impl.ImplPaymentDAO;
import Model.Impl.ImplRoomDAO;
import Model.Intefaces.IBookingDAO;
import Model.Intefaces.IClientDAO;
import Model.Intefaces.IPaymentDAO;
import Model.Intefaces.IRoomDAO;
import config.ConfigurationHotel;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;


/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ManagerDAO implements IClientDAO, IRoomDAO, IBookingDAO, IPaymentDAO{
    private static Logger log = Logger.getLogger(ManagerDAO.class.toString());
    private static ManagerDAO instance = null;
    private Connection connection;
    private IClientDAO clientDAO = new ImplClientDAO();
    private IRoomDAO roomDAO = new ImplRoomDAO();
    private IBookingDAO bookingDAO= new ImplBookingDAO();
    private IPaymentDAO paymentDAO= new ImplPaymentDAO();

    private ManagerDAO() {
        log.info("ManagerDAO Constructor");
        try {
            Class.forName(ConfigurationHotel.driver);
            connection = DriverManager.getConnection(ConfigurationHotel.server+ConfigurationHotel.base_name,ConfigurationHotel.base_user, ConfigurationHotel.base_pass);
        } catch (ClassNotFoundException e) {
            log.error("No such class: "+e.toString());
        } catch (SQLException e) {
            log.error("Can't connect to database: " + e.toString());
        }
    }

    public static ManagerDAO getInstance() {
        if (instance == null) instance = new ManagerDAO();
        return instance;
    }

    public void setRoomFree(Room room) throws SQLException {
        this.setRoomFree(room, connection);
        return;
    }
    @Override
    public void setRoomFree(Room room, Connection connection) throws SQLException {
        roomDAO.setRoomFree(room, connection);
        return;
    }

    public void cancelBooking(Booking booking) throws SQLException {
        this.cancelBooking(booking, connection);
        return;
    }
    @Override
    public void cancelBooking(Booking booking, Connection connection) throws SQLException {
        bookingDAO.cancelBooking(booking, connection);
        return;
    }

    public List<Payment> getPayment(Client client) throws SQLException {
        return this.getPayment(client,connection);
    }
    @Override
    public List<Payment> getPayment(Client client, Connection connection) throws SQLException {
        return paymentDAO.getPayment(client,connection);
    }
}
