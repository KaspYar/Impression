package Model.Intefaces;

import Hotel.Client;
import Hotel.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Slavko_O on 10.03.2015.
 */
public interface IPaymentDAO {
    public List<Payment> getPayment(Client client) throws SQLException;
}
