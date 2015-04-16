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
 * Created by Den on 14.04.2015.
 */
public class FreeRoomsFrame extends JFrame {
    private JPanel rootPane;
    private JTable freeRoomsTable;
    private JPanel btnPanel;
    private JButton btnApply;
    private DatabaseTableModel model;
    private int selectedID;

    public FreeRoomsFrame(){
        super("Free rooms");
        setContentPane(rootPane);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(600, 400));
        model = new DatabaseTableModel(false);
        freeRoomsTable.setSelectionBackground(Color.orange);
        freeRoomsTable.setRowHeight(20);
        freeRoomsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = freeRoomsTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ApplyTableListener());
        try {
            model.setDataSource(ManagerDAO.getInstance().getRoomsResultSet(true));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        freeRoomsTable.setModel(model);
        setVisible(true);
    }

    private class ApplyTableListener implements ListSelectionListener {

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

    public void addListener(ActionListener l){
        btnApply.addActionListener(l);
    }

    public JButton getBtnApply() {
        return btnApply;
    }

    public int getSelectedID() {
        return selectedID;
    }
}
