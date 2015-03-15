package Controller;

import Frame.AddClientFrame;
import Frame.GetPaymentFrame;
import Frame.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * Created by KaspYar on 15.03.2015.
 */
public class Controller {
    private static Logger log = Logger.getLogger(Controller.class.toString());

    private MainFrame frame;
    private GetPaymentFrame paymentFrame;
    private AddClientFrame addClientFrame;

    public Controller(MainFrame mc) {
        this.frame = mc;
        frame.setVisible(true);
        frame.addListener(new FrameListener());
    }
    private class FrameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == frame.getBtnSetFree()){
                log.info("setFree pressed");
                paymentFrame = new GetPaymentFrame();
                paymentFrame.addListener(new PaymentFrameListener());
            }
            if (source == frame.getBtnAddClient()){
                log.info("addClient pressed");
                addClientFrame = new AddClientFrame();
            }
        }

    }
    private class PaymentFrameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if(source == paymentFrame.getButton1()){
                log.info("Performed button");
            }
        }
    }
}
