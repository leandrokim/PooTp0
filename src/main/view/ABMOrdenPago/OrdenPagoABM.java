package main.view.ABMOrdenPago;

import main.java.controllers.ABMController;
import main.java.controllers.OrdenesYDocumentosController;
import main.java.models.Documentos.OrdenPago;
import main.view.abm.AbstractABMWindow;
import main.view.abm.ModalResult;
import main.view.abm.TableColumn;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrdenPagoABM extends AbstractABMWindow {

    private OrdenPagoABMTable tableModel;
    private ArrayList<OrdenPago.DTOOrdenPago> ordenesDePago;

    @Override
    protected String getTitle() {
        return "Orden De Pago";
    }

    @Override
    protected OrdenPagoABMTable getTableModel() {
        OrdenesYDocumentosController controller = OrdenesYDocumentosController.getInstancia();
        ordenesDePago = controller.getOrdenesDePago();
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Fecha", int.class));
        tableColumns.add(new TableColumn("Documentos Asociados", int.class));
        tableColumns.add(new TableColumn("Total A Cancelar", String.class));
        tableColumns.add(new TableColumn("Retenciones", int.class));
        tableColumns.add(new TableColumn("Formas de Pago", LocalDate.class));
        tableModel = new OrdenPagoABMTable(ordenesDePago, tableColumns);

        return tableModel;
    }

    @Override
    protected void agregar() {
        try {
            OrdenPagoABMDialog dialog = new OrdenPagoABMDialog(frame);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            //Esto se ejecuta cuando se apaga el modal
            if (dialog.getModalResult() == ModalResult.OK) {
                tableModel.agregar(dialog.getDTO());

                ABMController controller = ABMController.getInstancia();
                controller.guardarOrdenesPago(tableModel.lista);
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
            controller.guardarOrdenesPago(tableModel.lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void modificar() {
        try {
            OrdenPagoABMDialog dialog = new OrdenPagoABMDialog(frame);
            dialog.setDTO(ordenesDePago.get(table.getSelectedRow()));
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            if (dialog.getModalResult() == ModalResult.OK) {
                tableModel.refresh();
                ABMController controller = ABMController.getInstancia();
                controller.guardarOrdenesPago(tableModel.lista);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setVisible(boolean b) { 
    }
}
