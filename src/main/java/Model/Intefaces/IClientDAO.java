package Model.Intefaces;

import Hotel.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Slavko_O on 05.02.2015.
 */
public interface IClientDAO {

    void addClient(Client client);
    List<Client> getAllClients()throws SQLException;
    ResultSet getAllClientsResultSet();
    ResultSet getPaymentResultSet(Client client) throws SQLException;
}
