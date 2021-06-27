package main.view.CuentaCorrientesProveedores;

import main.java.controllers.ConsultaGeneralController;
import main.java.models.dto.DTODocumentosPagosYDeudas;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;
import main.view.util.JTableButtonMouseListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class CuentaCorrientesProveedores extends AbstractABMWindow {

    public CuentaCorrientesProveedores() {
        super();
    }

    @Override
    protected String getTitle() {
        return "Cuentas Corrientes de Proveedores";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ConsultaGeneralController controller = ConsultaGeneralController.getInstancia();
        ArrayList<DTODocumentosPagosYDeudas> datos = controller.consultaCuentaCorrienteProveedores();
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Cuit Proveedor", int.class));
        tableColumns.add(new TableColumn("Deuda Proveedor", double.class));
        tableColumns.add(new TableColumn("Documentos recibidos", JButton.class));
        tableColumns.add(new TableColumn("Documentos impagos", JButton.class));
        tableColumns.add(new TableColumn("Pagos realizados", JButton.class));
        return new CuentaCorrientesProveedoresTable(datos, tableColumns);
    }

    @Override
    protected void initialize() {
        super.initialize();

        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        table.getColumn("Documentos recibidos").setCellRenderer(buttonRenderer);
        table.getColumn("Documentos impagos").setCellRenderer(buttonRenderer);
        table.getColumn("Pagos realizados").setCellRenderer(buttonRenderer);
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
