package main.view.ABMFactura;

import main.java.controllers.ABMController;
import main.java.models.Documentos.Factura;
import main.java.models.IVA.ResponsableIVA;
import main.view.ABMOrdenPago.OrdenPagoABM;
import main.view.abm.AbstractABMWindow;
import main.view.abm.ModalResult;
import main.view.abm.TableColumn;
import main.view.util.JTableButtonMouseListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FacturaABM extends AbstractABMWindow {

    private FacturaABMTable tableModel;
    private ArrayList<Factura.DTOFactura> facturas;

    public FacturaABM() {
        super();
    }

    @Override
    protected String getTitle() {
        return "Factura";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ABMController controller = ABMController.getInstancia();
        facturas = controller.getFacturas();
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Nro Factura", int.class));
        tableColumns.add(new TableColumn("Cuit Proveedor", int.class));
        tableColumns.add(new TableColumn("Fecha", LocalDate.class));
        tableColumns.add(new TableColumn("Total", double.class));
        tableColumns.add(new TableColumn("Paga", boolean.class));
        tableColumns.add(new TableColumn("Responsable IVA", ResponsableIVA.class));
        tableColumns.add(new TableColumn("Productos", JButton.class));
        tableModel = new FacturaABMTable(facturas, tableColumns);

        return tableModel;
    }

    @Override
    protected void initialize() {
        super.initialize();

        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        table.getColumn("Productos").setCellRenderer(buttonRenderer);
        table.addMouseListener(new JTableButtonMouseListener(table));
    }

    @Override
    protected void agregar() {
        try {
            FacturaABMDialog dialog = new FacturaABMDialog(frame);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            //Esto se ejecuta cuando se apaga el modal
            if (dialog.getModalResult() == ModalResult.OK) {
                tableModel.agregar(dialog.getDTO());

                ABMController controller = ABMController.getInstancia();
                controller.guardarFacturas(tableModel.lista);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void borrar() {
        try {
            tableModel.eliminar(table.getSelectedRow());
            ABMController controller = ABMController.getInstancia();
            controller.guardarFacturas(tableModel.lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void modificar() {
        try {
            FacturaABMDialog dialog = new FacturaABMDialog(frame);
            dialog.setDTO(facturas.get(table.getSelectedRow()));
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            if (dialog.getModalResult() == ModalResult.OK) {
                tableModel.refresh();
                ABMController controller = ABMController.getInstancia();
                controller.guardarFacturas(tableModel.lista);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class JTableButtonRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (JButton) value;
        }
    }

}
