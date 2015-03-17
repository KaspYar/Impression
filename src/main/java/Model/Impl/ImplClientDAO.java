package Model.Impl;

import Hotel.Client;
import Model.Intefaces.IClientDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String query = "SELECT * FROM Payment WHERE idBooking IN (SELECT B.idBooking FROM Booking B INNER JOIN Client C ON B.clientCard = C.clientCard WHERE B.clientCard = ?)";
        PreparedStatement st = null;
        ResultSet rs = null;
            st = connection.prepareStatement(query);
        st.setInt(1, client.getClientCard());
            rs = st.executeQuery();
        return rs;
    }
}