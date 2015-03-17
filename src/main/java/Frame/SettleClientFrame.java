package Frame;

import Model.GUI.DatabaseTableModel;
import Model.ManagerDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

/**
 * Created by Den on 16.03.2015.
 */
public class SettleClientFrame extends JFrame {
    private JPanel rootPane;
    private JTable clientsTable;
    private JScrollPane scrollPane;
    private JButton btnChooseRoom;
    private DatabaseTableModel model;

    public SettleClientFrame(){
        super("Settle client");
        setContentPane(rootPane);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new Dimension(800, 500));
        clientsTable.setSelectionBackground(Color.orange);
        clientsTable.setRowHeight(20);
        model = new DatabaseTableModel(false);
        initModelSource();
        clientsTable.setModel(model);

        setVisible(true);
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
}
