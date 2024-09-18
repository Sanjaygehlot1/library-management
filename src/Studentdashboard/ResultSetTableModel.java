package Studentdashboard;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetTableModel extends AbstractTableModel {
    private List<Object[]> data;
    private String[] columnNames;

    /**
     * Constructs a table model from the given ResultSet.
     *
     * @param resultSet the ResultSet containing data to display in the table.
     * @throws SQLException if a database access error occurs or this method is called on a closed result set.
     */
    public ResultSetTableModel(ResultSet resultSet) throws SQLException {
        data = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        columnNames = new String[columnCount];

        
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }

       
        while (resultSet.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = resultSet.getObject(i);
            }
            data.add(rowData);
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
