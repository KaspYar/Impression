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
    private String password;

    public Client(){}

    public Client(int clientCard, String firstname, String lastname, String organization, String email, String password) {

        this.clientCard = clientCard;
        this.firstname = firstname;
        this.lastname = lastname;
        this.organization = organization;
        this.email = email;
        this.password = password;
    }

    public void setClientCard(int clientCard) {
        this.clientCard = clientCard;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    @Override
    public String toString() {
        return "Client{" +
                "clientCard=" + clientCard +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", organization='" + organization + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
