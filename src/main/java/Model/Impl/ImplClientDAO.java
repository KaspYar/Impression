package Model.Impl;

import Hotel.Client;
import Hotel.Room;
import Model.Intefaces.IClientDAO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by Slavko_O on 05.02.2015.
 */
public class ImplClientDAO implements IClientDAO {
    Logger log = Logger.getLogger(ImplClientDAO.class.toString());
    private Connection connection;
    public ImplClientDAO(Connection connection) {
        log.info("ImplClientDAO constructor");
        this.connection = connection;
    }

    @Override
    public void addClient(Client client) {
        log.info("addClient() called");
        String query = "INSERT INTO Client(clientCard,firstname,lastname,organization,email,password) VALUES(?,?,?,?,?,?)";
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(query);
            st.setInt(1, client.getClientCard());
            st.setString(2,client.getFirstname());
            st.setString(3,client.getLastname());
            st.setString(4,client.getOrganization());
            st.setString(5,client.getEmail());
            st.setString(6,client.getPassword());
            st.execute();
            log.info("Client added");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
             try {
                 if(st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Client> getAllClients() throws SQLException{

        String query = "SELECT clientCard,firstname,lastname,organization,email FROM Client";
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Client> clientList = new ArrayList<Client>();
        try {
            st = connection.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()){
                Client client = new Client();
                client.setClientCard(rs.getInt(1));
                client.setFirstname(rs.getString(2));
                client.setLastname(rs.getString(3));
                client.setOrganization(rs.getString(4));
                client.setEmail(rs.getString(5));
                //client.setPassword(rs.getString(6));
                clientList.add(client);
            }
        }  finally {
            try {
                if(st != null) st.close();
                if(rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clientList;
    }

    @Override
    public ResultSet getAllClientsResultSet() {
        String query = "SELECT clientCard,firstname,lastname,organization,email FROM Client";
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = connection.prepareStatement(query);
            rs = st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public ResultSet getPaymentResultSet(Client client) throws  SQLException{
        String query = "select CH.clientCard, CH.roomNum, CH.checkInDate, CH.checkOutDate, P.totalPrice\n" +
                "from checkinout CH left join payment P on P.idCheckInOut = CH.idCheckInOut\n" +
                "where CH.clientCard = ?";
        PreparedStatement st = null;
        ResultSet rs = null;
            st = connection.prepareStatement(query);
        st.setInt(1, client.getClientCard());
            rs = st.executeQuery();
        return rs;
    }

    @Override
    public Client getClientByID(int clientID) {

        String query = "SELECT clientCard,firstname,lastname,organization,email FROM Client WHERE clientCard=?";
        PreparedStatement st = null;
        ResultSet rs = null;
        Client client = null;
        try {
            st = connection.prepareStatement(query);
            st.setInt(1,clientID);
            rs = st.executeQuery();

            while (rs.next()){
                client = new Client();
                client.setClientCard(rs.getInt(1));
                client.setFirstname(rs.getString(2));
                client.setLastname(rs.getString(3));
                client.setOrganization(rs.getString(4));
                client.setEmail(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(st != null) st.close();
                if(rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    @Override
    public void addClientToRoom(Client client, Room room, int days) {
        String insertCheckInOut = "INSERT INTO CheckInOut(clientCard, roomNum, checkInDate) VALUES(?,?,?)";
        String updateRoom = "UPDATE Room SET available = ? WHERE roomNum = ?";
        String insertBooking = "INSERT INTO Booking(clientCard, dateFrom, dateTo) VALUES(?,?,?)";
        String insertBookingRoom = "INSERT INTO BookingRoom(idBooking, roomNum) VALUES(?,?)";
        String insertPayment = "INSERT INTO Payment(idBooking, idCheckInOut, totalPrice) VALUES(?,?,?)";
        PreparedStatement st = null;
        ResultSet rs = null;
        int idBooking=0;
        int idCheckInOut=0;
        try {
            Date current = new Date(System.currentTimeMillis());
            Calendar cal = Calendar.getInstance();
            cal.setTime(current);
            cal.add(Calendar.DATE, days);

            st = connection.prepareStatement(insertBooking,Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, client.getClientCard());
            st.setDate(2, current);
            st.setDate(3, new java.sql.Date(cal.getTimeInMillis()));
            st.execute();
            rs = st.getGeneratedKeys();
            if(rs.next())
                idBooking = rs.getInt(1);

            st = connection.prepareStatement(insertCheckInOut,Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, client.getClientCard());
            st.setInt(2, room.getRoomNum());
            st.setDate(3, current);
            st.execute();
            rs = st.getGeneratedKeys();
            if(rs.next())
                idCheckInOut = rs.getInt(1);

            st = connection.prepareStatement(insertBookingRoom);
            st.setInt(1, idBooking);
            st.setInt(2, room.getRoomNum());
            st.execute();

            st = connection.prepareStatement(updateRoom);
            st.setBoolean(1, false);
            st.setInt(2, room.getRoomNum());
            st.executeUpdate();

            st = connection.prepareStatement(insertPayment);
            st.setInt(1, idBooking);
            st.setInt(2, idCheckInOut);
            st.setDouble(3, room.getPrice() * days);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}