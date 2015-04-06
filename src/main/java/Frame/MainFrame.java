package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Den on 09.03.2015.
 */
public class MainFrame extends JFrame{

    private JLabel hotelNameLbl;
    private JPanel rootPane;
    private JButton btnSetFree;
    private JButton btnSettleClient;
    private JButton btnAddClient;
    private JButton btnPayments;
    private JButton btnGetFree;
    private JButton button4;

    public MainFrame(){
        super("Hotel \"Impression\"");
        setContentPane(rootPane);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(600, 400));
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        hotelNameLbl = new JLabel();
    }
    public void addListener(ActionListener l) {
        btnAddClient.addActionListener(l);
        btnSetFree.addActionListener(l);
        btnSettleClient.addActionListener(l);
        btnPayments.addActionListener(l);
        btnGetFree.addActionListener(l);
    }
    public JButton getBtnSetFree() {
        return btnSetFree;
    }

    public JButton getBtnSettleClient(){
        return btnSettleClient;
    }

    public JButton getBtnAddClient() {

        return btnAddClient;
    }

    public JButton getBtnGetFree() {
        return btnGetFree;
    }

    public JButton getBtnPayments() {
        return btnPayments;
    }
}
