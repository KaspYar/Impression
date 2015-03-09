package Hotel.Client;

/**
 * Created by Slavko_O on 03.02.2015.
 */
public class Client extends AbstractClient{
    public Client(int idClient, String firstname, String lastname, String email, String phone, String country) {
        super(idClient, firstname, lastname, email, phone, country);
    }

    @Override
    public String toString() {
        return "Client{} "+super.toString();
    }
}
