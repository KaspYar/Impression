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
        String query = "SELECT B.clientCard AS 'Client Card', BR.roomNum AS 'Room #', P.totalPrice AS 'Price', B.dateFrom AS 'From', B.dateTo AS 'To' " +
                "FROM (Payment P INNER JOIN Booking B ON P.idBooking = B.idBooking) INNER JOIN bookingroom BR ON B.idBooking = BR.idBooking " +
                "WHERE P.idBooking IN (?)";
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
    public void addClientToRoom(Client client, Room room) {
        String query = "INSERT INTO CheckInOut(clientCard, roomNum, checkInDate) VALUES(?,?,?)";
        String updateQuery = "UPDATE Room SET available = ? WHERE roomNum = ?";
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(query);
            st.setInt(1, client.getClientCard());
            st.setInt(2, room.getRoomNum());
            st.setDate(3, new Date(System.currentTimeMillis()));
            st.execute();
            log.info("Client added to room");
            st = connection.prepareStatement(updateQuery);
            st.setBoolean(1,false);
            st.setInt(2,room.getRoomNum());
            st.executeUpdate();
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