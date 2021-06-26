package main.view.abm;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class AbstractModelTable<T> extends AbstractTableModel {

    public final ArrayList<T> lista;

    protected ArrayList<TableColumn> tableColumns;

    public String getColumnName(int col) {
        return tableColumns.get(col).columnName;
    }

    public Class getColumnClass(int col) {
        return tableColumns.get(col).columnClass;
    }

    public AbstractModelTable(ArrayList<T> lista, ArrayList<TableColumn> columnas) {
        this.lista = lista;
        tableColumns = columnas;
    }

    @Override
    public int getColumnCount() {
        return tableColumns.size();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //TODO setear aca el valor que se quiere mostrar en la fila/columna
        return null;
    }

    public void agregar(T dato) {
        lista.add(dato);
        fireTableDataChanged();
    }

    public void refresh() {
        fireTableDataChanged();
    }

    public void eliminar(int fila) {
        lista.remove(fila);
        fireTableDataChanged();
    }

}
