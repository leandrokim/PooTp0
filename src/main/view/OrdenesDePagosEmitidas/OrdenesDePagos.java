package main.view.OrdenesDePagosEmitidas;

import main.java.controllers.OrdenesYDocumentosController;
import main.java.models.Documentos.OrdenPago;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;
import main.view.util.JTableButtonMouseListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class OrdenesDePagos extends AbstractABMWindow {
    public OrdenesDePagos() {
        super();
    }

    @Override
    protected String getTitle() {
        return "Ordenes de Pagos Emitidas";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        OrdenesYDocumentosController controller = OrdenesYDocumentosController.getInstancia();
        ArrayList<OrdenPago.DTOOrdenPago> datos = controller.getOrdenesDePago();
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Numero", int.class));
        tableColumns.add(new TableColumn("Cuit Proveedor", int.class));
        tableColumns.add(new TableColumn("Fecha", String.class));
        tableColumns.add(new TableColumn("Total", double.class));
        tableColumns.add(new TableColumn("Documentos asociados", JButton.class));
        tableColumns.add(new TableColumn("Formas de pago", JButton.class));
        tableColumns.add(new TableColumn("Retenciones", JButton.class));
        return new OrdenesDePagoTable(datos, tableColumns);
    }

    @Override
    protected void initialize() {
        super.initialize();

        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        table.getColumn("Documentos asociados").setCellRenderer(buttonRenderer);
        table.getColumn("Formas de pago").setCellRenderer(buttonRenderer);
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
