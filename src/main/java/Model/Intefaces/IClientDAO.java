package Model.Intefaces;

import Hotel.Client;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Slavko_O on 05.02.2015.
 */
public interface IClientDAO {

    void addClient(Client client);
    List<Client> getAllClients();
    ResultSet getAllClientsResultSet();
}
