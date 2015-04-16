package Frame;

import Model.GUI.DatabaseTableModel;
import Model.ManagerDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by Den on 16.03.2015.
 */
public class SettleClientFrame extends JFrame {
    private JPanel rootPane;
    private JTable clientsTable;
    private JScrollPane scrollPane;
    private JButton btnAssignRoom;
    private DatabaseTableModel model;
    private int selectedID;

    public SettleClientFrame(){
        super("Settle client");
        setContentPane(rootPane);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(800, 500));
        clientsTable.setSelectionBackground(Color.orange);
        clientsTable.setRowHeight(20);
        clientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = clientsTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ClientTableListener());
        model = new DatabaseTableModel(false);
        initModelSource();
        clientsTable.setModel(model);

        setVisible(true);
    }

    private class ClientTableListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            String strSource = e.getSource().toString();
            int start = strSource.indexOf("{") + 1;
            int stop = strSource.length() - 1;
            int selectedIndex = Integer.parseInt(strSource.substring(start, stop));
            Object o = model.getValueAt(selectedIndex,0);
            selectedID = (Integer)o;
        }
    }

    private void initModelSource(){
        try {
            model.setDataSource(ManagerDAO.getInstance().getAllClientsResultSet());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public JButton getBtnAssignRoom() {
        return btnAssignRoom;
    }

    public void addListener(ActionListener l){
        btnAssignRoom.addActionListener(l);
    }

    public int getSelectedID() {
        return selectedID;
    }
}
