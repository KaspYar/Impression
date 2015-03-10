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
        String query = "SELECT P.idPayment, P.idBooking, P.idCheckInOut, P.totalPrice, R.roomNum " +
                "FROM payment P INNER JOIN (checkInOut C INNER JOIN room R ON C.roomNum = R.roomNum ) ON P.idCheckInOut = C.idCheckInOut\n" +
                "WHERE C.clientCard = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, client.getClientCard());
        ResultSet resultSet = preparedStatement.executeQuery();
        Payment payment = null;
        while (resultSet.next()){
            payment = new Payment(
                    resultSet.getInt("idPayment"), resultSet.getInt("idBooking"),
                    resultSet.getInt("idCheckInOut"), resultSet.getDouble("totalPrice"), resultSet.getInt("roomNum"));
           result.add(payment);
        }
        return result;
    }
}
