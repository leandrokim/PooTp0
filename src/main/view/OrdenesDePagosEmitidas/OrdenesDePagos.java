package main.view.OrdenesDePagosEmitidas;

import main.java.controllers.ConsultaGeneralController;
import main.java.models.dto.DTODocumentosPagosYDeudas;
import main.view.CuentaCorrientesProveedores.CuentaCorrientesProveedoresTable;
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
        ConsultaGeneralController controller = ConsultaGeneralController.getInstancia();
        ArrayList<DTODocumentosPagosYDeudas> datos = controller.consultaCuentaCorrienteProveedores();
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Retenciones", int.class)); //JButton.class    //TODO
        tableColumns.add(new TableColumn("Total a cancelar", double.class));
        tableColumns.add(new TableColumn("Documentos asociados", JButton.class));
        tableColumns.add(new TableColumn("Formas de pago", int.class)); //JButton.class //TODO
        return new CuentaCorrientesProveedoresTable(datos, tableColumns);
    }

    @Override
    protected void initialize() {
        super.initialize();

        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        table.getColumn("Documentos asociados").setCellRenderer(buttonRenderer);
        table.getColumn("Formas de pago").setCellRenderer(buttonRenderer);
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
