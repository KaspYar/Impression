package Model.GUI;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Den on 16.03.2015.
 */
public class DatabaseTableModel extends AbstractTableModel {

    private ArrayList<String> columnNames = new ArrayList<String>();//названия столбцов
    private ArrayList<Class> columnTypes = new ArrayList<Class>();//список типов столбцов
    private ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();//хранилище полученых данных с базы

    private boolean editable; //признак редактирования таблицы

    public DatabaseTableModel(boolean editable) {
        this.editable = editable;
    }

    @Override
    public int getRowCount() {//количество строк
        return data.size();
    }

    @Override
    public int getColumnCount() {//количество столбцов
        return columnNames.size();
    }

    @Override
    public Class getColumnClass(int column) {//тип данных столбца
        return columnTypes.get(column);
    }

    @Override
    public String getColumnName(int column) {//название столбца
        return columnNames.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {//данные в ячейке
        return (data.get(rowIndex)).get(columnIndex);
    }

    @Override
    public boolean isCellEditable(int row, int column) {//возможность редактирования
        return editable;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {//замена значения ячейки
        (data.get(rowIndex)).set(columnIndex, aValue);
    }

    public void addRow(Object obj){
        ArrayList<Object> row = new ArrayList<Object>();
        row.add(obj);
        data.add(row);
    }

    public void setDataSource(ResultSet rs) throws SQLException, ClassNotFoundException {
        //удаляем прежние данные
        data.clear();
        columnNames.clear();
        columnTypes.clear();

        //получаем воспомогательную информацию о столбцах
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        for (int i = 0; i < columnCount; i++) {
            columnNames.add(rsmd.getColumnName(i + 1));
            Class type = Class.forName(rsmd.getColumnClassName(i + 1));
            columnTypes.add(type);
        }

        //получаем данные
        while (rs.next()) {
            ArrayList<Object> row = new ArrayList<Object>();
            for (int i = 0; i < columnCount; i++) {
                row.add(rs.getObject(i + 1));
            }
            data.add(row);
        }

        fireTableStructureChanged();
    }
}