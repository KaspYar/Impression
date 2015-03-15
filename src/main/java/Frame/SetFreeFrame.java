package Frame;

import Hotel.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by KaspYar on 15.03.2015.
 */
public class SetFreeFrame extends  JFrame{


    private JComboBox comboBoxRoom;
    private JLabel lblSelectRoom;
    private JTextPane textPaneInfo;
    private JPanel rootPane;
    private JButton btnSetFree;

    private ArrayList<Room> lst;


    public SetFreeFrame(ArrayList<Room> lst){
        super("Hotel");
        setContentPane(rootPane);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 400));
        setVisible(true);
        textPaneInfo.setEditable(false);
        this.lst = lst;
        this.btnSetFree.setVisible(false);
        createUIComponents();
    }
    public void addListener(ActionListener l) {
        btnSetFree.addActionListener(l);
        comboBoxRoom.addActionListener(l);
    }
    private void createUIComponents() {
        for(Room s: lst) {
            comboBoxRoom.addItem("Room#"+s.getRoomNum());
        }
    }

    public JButton getBtnSetFree() {
        return btnSetFree;
    }
    public JComboBox getComboBoxRoom() {
        return comboBoxRoom;
    }

    public JTextPane getTextPaneInfo() {
        return textPaneInfo;
    }

    public ArrayList<Room> getLst() {
        return lst;
    }
}
