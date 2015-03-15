package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by KaspYar on 15.03.2015.
 */
public class GetPaymentFrame extends  JFrame{
    private JComboBox comboBoxRoom;
    private JLabel lblSelectRoom;
    private JTextPane textPaneInfo;
    private JPanel rootPane;
    private JButton button1;

    public GetPaymentFrame(){
        super("Hotel");
        setContentPane(rootPane);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 400));
        setVisible(true);
        textPaneInfo.setEditable(false);
        createUIComponents();
    }
    public void addListener(ActionListener l) {
        button1.addActionListener(l);
    }
    private void createUIComponents() {
        System.out.println("creareUI");
        ArrayList<Integer> lst = new ArrayList<Integer>();
        lst.add(1);
        lst.add(2);
        for(Integer s: lst) {
            comboBoxRoom.addItem(s);
        }
    }

    public JButton getButton1() {
        return button1;
    }
}
