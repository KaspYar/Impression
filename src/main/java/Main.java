import Controller.Controller;
import Frame.MainFrame;
import org.apache.log4j.Logger;

import javax.swing.*;


/**
 * Created by Slavko_O on 03.02.2015.
 */
public class Main {
    private static Logger LOG = Logger.getLogger(Main.class.toString());
    public static void main(String [] args){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        MainFrame mf  = new MainFrame();
        Controller controller = new Controller(mf);
    }
}
