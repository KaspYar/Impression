package Hotel.Client;

/**
 * Created by Slavko_O on 03.02.2015.
 */
public class OrganizationClient extends AbstractClient {
    String organization;

    public OrganizationClient(int idClient, String firstname, String lastname, String email, String phone, String country, String organization) {
        super(idClient, firstname, lastname, email, phone, country);
        this.organization = organization;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "OrganizationClient{" +
                "organization='" + organization + '\'' +
                '}' + super.toString();
    }
}
