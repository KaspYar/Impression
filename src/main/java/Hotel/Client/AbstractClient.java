package Hotel.Client;

/**
 * Created by Slavko_O on 03.02.2015.
 */
public abstract class AbstractClient {
    int idClient;
    String firstname;
    String lastname;
    String email;
    String phone;
    String country;

    public AbstractClient(){}

    public AbstractClient(int idClient, String firstname, String lastname, String email, String phone, String country) {
        this.idClient = idClient;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.country = country;
    }

    public String getFirsname() {
        return firstname;
    }
    public void setFirsname(String firsname) {
        this.firstname = firsname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AbstractClient{" +
                "idClient=" + idClient +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
