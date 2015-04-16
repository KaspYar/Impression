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
    private IClientDAO clientDAO;
    private IRoomDAO roomDAO;
    private IBookingDAO bookingDAO;
    private IPaymentDAO paymentDAO;

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
        clientDAO = new ImplClientDAO(connection);
        roomDAO = new ImplRoomDAO(connection);
        bookingDAO = new ImplBookingDAO(connection);
        paymentDAO = new ImplPaymentDAO(connection);
    }

    public static ManagerDAO getInstance() {
        if (instance == null) instance = new ManagerDAO();
        return instance;
    }
    @Override
    public void setRoomFree(Room room) throws SQLException {
        this.roomDAO.setRoomFree(room);
        return;
    }

    @Override
    public List<Room> getRooms(boolean available) throws SQLException {
        return roomDAO.getRooms(available);
    }

    @Override
    public ResultSet getRoomsResultSet(boolean available) {
        return roomDAO.getRoomsResultSet(available);
    }

    @Override
    public Room getRoomByNum(int roomNum) {
        return roomDAO.getRoomByNum(roomNum);
    }

    @Override
    public void cancelBooking(Booking booking) throws SQLException {
        this.bookingDAO.cancelBooking(booking);
        return;
    }

    @Override
    public List<Booking> getPaymentBooking(Client client) throws SQLException {
        return this.bookingDAO.getPaymentBooking(client);
    }

    @Override
    public List<Payment> getPayment(Client client) throws SQLException {
        return this.paymentDAO.getPayment(client);
    }

    @Override
    public void addClient(Client client) {
        this.clientDAO.addClient(client);
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        return this.clientDAO.getAllClients();
    }

    @Override
    public ResultSet getAllClientsResultSet() {
        return this.clientDAO.getAllClientsResultSet();
    }

    @Override
    public ResultSet getPaymentResultSet(Client client) throws  SQLException{
        return this.clientDAO.getPaymentResultSet(client);
    }

    @Override
    public Client getClientByID(int clientID) {
        return clientDAO.getClientByID(clientID);
    }

    @Override
    public void addClientToRoom(Client client, Room room) {
        clientDAO.addClientToRoom(client,room);
    }
}
