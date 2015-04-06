package Frame;

import Hotel.Room;
import Model.GUI.DatabaseTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by KaspYar on 07.04.2015.
 */
public class FreeRoomForToday extends JFrame{
    private JPanel rootPane;
    private JComboBox comboBox1;
    private JTextArea textArea1;
    private ArrayList<Room> lst;

    public FreeRoomForToday(ArrayList<Room> lst){
        super("Hotel");
        setContentPane(rootPane);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 400));
        this.lst = lst;
        setVisible(true);
        createUIComponents();
    }
    private void createUIComponents() {
        for(Room r: lst){
            comboBox1.addItem("Room #"+r.getRoomNum());
        }
    }

    public ArrayList<Room> getLst() {
        return lst;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }
    public JTextArea getTextArea1() {
        return textArea1;
    }
    public void addListener(ActionListener l) {
        comboBox1.addActionListener(l);
    }
}
