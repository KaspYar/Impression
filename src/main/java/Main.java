import Frame.AddClientFrame;
import Frame.MainFrame;
import Hotel.Client;
import Model.ManagerDAO;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;


/**
 * Created by Slavko_O on 03.02.2015.
 */
public class Main {
    private static Logger LOG = Logger.getLogger(Main.class.toString());
    public static void main(String [] args){
        BasicConfigurator.configure();
//        LOG.info("Hello world");
//        Client client = new Client(1, "Yaroslav","Kaspryshyn","NaUKMA","kaspyar@yahoo.com");
//        LOG.info(client.toString());
//        ManagerDAO dao = ManagerDAO.getInstance();

        MainFrame mf  = new MainFrame();
        AddClientFrame addClientFrame = new AddClientFrame();
    }
}
