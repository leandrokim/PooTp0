package main.view.ABMOrdenPago;

import main.java.controllers.ABMController;
import main.java.controllers.OrdenesYDocumentosController;
import main.java.models.Documentos.OrdenPago;
import main.view.abm.AbstractABMWindow;
import main.view.abm.ModalResult;
import main.view.abm.TableColumn;
import main.view.util.JTableButtonMouseListener;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class OrdenPagoABM extends AbstractABMWindow {

    private OrdenPagoABMTable tableModel;
    private ArrayList<OrdenPago.DTOOrdenPago> ordenesDePago;
    private OrdenesYDocumentosController controller;

    public OrdenPagoABM() {
        super();
    }

    @Override
    protected String getTitle() {
        return "Orden De Pago";
    }

    @Override
    protected OrdenPagoABMTable getTableModel() {
        controller = OrdenesYDocumentosController.getInstancia();
        ordenesDePago = controller.getOrdenesDePago();
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Nro", int.class));
        tableColumns.add(new TableColumn("Cuit Proveedor", int.class));
        tableColumns.add(new TableColumn("Fecha", String.class));
        tableColumns.add(new TableColumn("Total A Cancelar", double.class));
        tableColumns.add(new TableColumn("Documentos Asociados", JButton.class));
        tableColumns.add(new TableColumn("Retenciones", JButton.class));
        tableColumns.add(new TableColumn("Formas de Pago", JButton.class));
        tableModel = new OrdenPagoABMTable(ordenesDePago, tableColumns);

        return tableModel;
    }

    @Override
    protected void initialize() {
        super.initialize();

        TableCellRenderer buttonRenderer = new JTableButtonRenderer();
        table.getColumn("Documentos Asociados").setCellRenderer(buttonRenderer);
        table.getColumn("Retenciones").setCellRenderer(buttonRenderer);
        table.getColumn("Formas de Pago").setCellRenderer(buttonRenderer);
        table.addMouseListener(new JTableButtonMouseListener(table));
    }

    @Override
    protected void agregar() {
        try {
            OrdenPagoABMDialog dialog = new OrdenPagoABMDialog(frame);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            //Esto se ejecuta cuando se apaga el modal
            if (dialog.getModalResult() == ModalResult.OK) {
                OrdenPago.DTOOrdenPago dto = dialog.getDTO();
                dto.nroOrdenPago = controller.nuevoNumeroOrdenPago();

                ABMController abmController = ABMController.getInstancia();
                abmController.guardarOrdenPago(dto);

                ordenesDePago = controller.getOrdenesDePago();
                tableModel.nuevaLista(ordenesDePago);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void borrar() {
        try {
            OrdenPago.DTOOrdenPago dto = tableModel.lista.get(table.getSelectedRow());
            ABMController abmController = ABMController.getInstancia();
            abmController.borrarOrdenPago(dto);

            ordenesDePago = controller.getOrdenesDePago();
            tableModel.nuevaLista(ordenesDePago);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void modificar() {
        //No se puede modificar
    }

    private static class JTableButtonRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (JButton) value;
        }
    }

    @Override
    protected boolean isModificar() {
        return false;
    }

}
