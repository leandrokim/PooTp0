package main.view.OrdenDeCompra;


import main.java.models.Documentos.OrdenCompra;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;
import main.view.util.JTableButtonMouseListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class OrdenesDeCompra extends AbstractABMWindow {

    private final ArrayList<OrdenCompra.DTOOrdenCompra> ordenCompras;
    private OrdenesCompraTable tableModel;

    public OrdenesDeCompra(ArrayList<OrdenCompra.DTOOrdenCompra> ordenCompras) {
        this.ordenCompras = ordenCompras;
        initialize();
    }

    @Override
    protected String getTitle() {
        return "Ordenes de Compra";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("id OrdenCompra", int.class));
        tableColumns.add(new TableColumn("Productos", JButton.class));
        tableColumns.add(new TableColumn("totalPrecioAcordado", double.class));
        tableModel = new OrdenesCompraTable(ordenCompras, tableColumns);
        return tableModel;
    }

    @Override
    protected void initialize() {
        super.initialize();

        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        table.getColumn("Productos").setCellRenderer(buttonRenderer);
        table.addMouseListener(new JTableButtonMouseListener(table));
    }

    //Is Not ABM
    @Override
    protected void agregar() {
    }

    @Override
    protected void borrar() {
    }

    @Override
    protected void modificar() {
    }

    @Override
    protected boolean isABM() {
        return false;
    }

    private static class JTableButtonRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (JButton) value;
        }
    }

}
