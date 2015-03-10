package Model.Impl;

import Hotel.Client;
import Hotel.Payment;
import Model.Intefaces.IPaymentDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Slavko_O on 10.03.2015.
 */
public class ImplPaymentDAO implements IPaymentDAO {
    private static Logger log = Logger.getLogger(ImplPaymentDAO.class.toString());
    private Connection connection;

    public ImplPaymentDAO(Connection connection) {
        log.info("ImplPaymentDAO constructor");
        this.connection = connection;
    }
    @Override
    public List<Payment> getPayment(Client client) throws SQLException {
        ArrayList<Payment> result = new ArrayList<Payment>();
        log.info("getPayment idClient: "+ client.getClientCard());
        String query = "SELECT * FROM payment WHERE idBooking IN (SELECT idBooking FROM booking WHERE clientCard = ?) ORDER BY idPayment DESC";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, client.getClientCard());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            result.add(new Payment(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDouble(3)));
        }
        return result;
    }
}
