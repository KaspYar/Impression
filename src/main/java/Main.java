import Controller.Controller;
import Frame.MainFrame;
import org.apache.log4j.Logger;


/**
 * Created by Slavko_O on 03.02.2015.
 */
public class Main {
    private static Logger LOG = Logger.getLogger(Main.class.toString());
    public static void main(String [] args){
        MainFrame mf  = new MainFrame();
        Controller controller = new Controller(mf);
    }
}
