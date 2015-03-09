package Hotel;

/**
 * Created by Slavko_O on 09.03.2015.
 */
public class Client {
    private int clientCard;
    private String firstname;
    private String lastname;
    private String organization;
    private String email;

    public int getClientCard() {
        return clientCard;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getOrganization() {
        return organization;
    }

    public String getEmail() {
        return email;
    }

    public Client(int clientCard, String firstname, String lastname, String organization, String email) {

        this.clientCard = clientCard;
        this.firstname = firstname;
        this.lastname = lastname;
        this.organization = organization;
        this.email = email;
    }
}
