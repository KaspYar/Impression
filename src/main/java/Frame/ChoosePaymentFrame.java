package Frame;

import Hotel.Client;
import Model.GUI.DatabaseTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by KaspYar on 17.03.2015.
 */
public class ChoosePaymentFrame extends JFrame {
    private JPanel rootPane;
    private JLabel lblSetClient;
    private JComboBox comboBoxSelectClient;
    private JButton btnChooseClientsPayment;
    private JTable tableClientPayments;
    private ArrayList<Client> lst;
    private DatabaseTableModel model;

    public DatabaseTableModel getModel() {
        return model;
    }

    public ChoosePaymentFrame(ArrayList<Client> lst){
        super("Hotel");
        setContentPane(rootPane);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 400));

        tableClientPayments.setSelectionBackground(Color.orange);
        tableClientPayments.setRowHeight(20);
        model = new DatabaseTableModel(false);
        tableClientPayments.setModel(model);


        setVisible(true);
        this.lst = lst;
        createUIComponents();
    }
    public void addListener(ActionListener l) {
        comboBoxSelectClient.addActionListener(l);
        btnChooseClientsPayment.addActionListener(l);
    }
    private void createUIComponents() {
        for(Client s: lst) {
            comboBoxSelectClient.addItem("Lastname: "+s.getLastname()+" Firstname: "+s.getFirstname());
        }
    }
    public void initModelSource(ResultSet resultSet){
        try {
            model.setDataSource(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public JComboBox getComboBoxSelectClient() {
        return comboBoxSelectClient;
    }

    public JButton getBtnChooseClientsPayment() {
        return btnChooseClientsPayment;
    }

    public ArrayList<Client> getLst() {
        return lst;
    }

    public JTable getTableClientPayments() {
        return tableClientPayments;
    }
}
