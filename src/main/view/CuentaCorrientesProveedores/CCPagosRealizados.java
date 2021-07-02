package main.view.CuentaCorrientesProveedores;

import main.java.models.Documentos.OrdenPago;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;
import main.view.util.JTableButtonMouseListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class CCPagosRealizados extends AbstractABMWindow {

    private final ArrayList<OrdenPago.DTOOrdenPago> pagosRealizados;

    public CCPagosRealizados(ArrayList<OrdenPago.DTOOrdenPago> pagosRealizados) {
        this.pagosRealizados = pagosRealizados;
        initialize();
    }

    @Override
    protected String getTitle() {
        return "Pagos Realizados";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Numero Orden de Pago", int.class));
        tableColumns.add(new TableColumn("Cuit Proveedor", int.class));
        tableColumns.add(new TableColumn("Fecha", String.class));
        tableColumns.add(new TableColumn("Total a Cancelar", double.class));
        tableColumns.add(new TableColumn("Documentos Asociados", JButton.class));
        tableColumns.add(new TableColumn("Formas de Pago", JButton.class));
        tableColumns.add(new TableColumn("Retenciones", JButton.class));
        return new CCOrdenPagoTable(pagosRealizados, tableColumns);
    }

    @Override
    protected void initialize() {
        super.initialize();

        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        table.getColumn("Documentos Asociados").setCellRenderer(buttonRenderer);
        table.getColumn("Formas de Pago").setCellRenderer(buttonRenderer);
        table.getColumn("Retenciones").setCellRenderer(buttonRenderer);
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
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (JButton)value;
        }
    }

}
