package Frame;

import Hotel.Client;
import Model.GUI.DatabaseTableModel;
import Model.ManagerDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Den on 17.04.2015.
 */
public class CheckBookingFrame extends JFrame{
    private JPanel rootPane;
    private JScrollPane scrollPane;
    private JTable clientTable;
    private JLabel bookingInfoLbl;
    private DatabaseTableModel model;
    private int selectedID;

    public CheckBookingFrame(){
        super("Check booking");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(rootPane);
        setSize(new Dimension(600, 400));

        model = new DatabaseTableModel(false);
        try {
            model.setDataSource(ManagerDAO.getInstance().getAllClientsResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clientTable.setSelectionBackground(Color.orange);
        clientTable.setRowHeight(20);
        clientTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = clientTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ClientTableListener());
        clientTable.setModel(model);
        setVisible(true);
    }

    private class ClientTableListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            String strSource = e.getSource().toString();
            int start = strSource.indexOf("{") + 1;
            int stop = strSource.length() - 1;
            int selectedIndex = Integer.parseInt(strSource.substring(start, stop));
            Object o = model.getValueAt(selectedIndex,0);
            selectedID = (Integer)o;

            Client client = ManagerDAO.getInstance().getClientByID(selectedID);
            if(ManagerDAO.getInstance().getBookingByClientId(client.getClientCard()) == null){
                bookingInfoLbl.setText("Client doesn't have booking");
            } else
                bookingInfoLbl.setText(
                        ManagerDAO.getInstance().getBookingByClientId(client.getClientCard()).toString());
        }
    }


    public int getSelectedID() {
        return selectedID;
    }

    public JLabel getBookingInfoLbl() {
        return bookingInfoLbl;
    }
}
