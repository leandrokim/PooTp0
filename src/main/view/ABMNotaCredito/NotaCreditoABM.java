
package main.view.ABMNotaCredito;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import main.java.controllers.ABMController;
import main.java.models.Documentos.NotaCredito.DTONotaCredito;
import main.view.abm.AbstractABMWindow;
import main.view.abm.ModalResult;
import main.view.abm.TableColumn;

public class NotaCreditoABM extends AbstractABMWindow {
    private NotaCreditoABMTable tableModel;
    private ArrayList<DTONotaCredito> notasCredito;

    public NotaCreditoABM() {
    }

    protected String getTitle() {
        return "Nota de Credito";
    }

    protected AbstractTableModel getTableModel() {
        ABMController controller = ABMController.getInstancia();
        this.notasCredito = controller.getNotasCredito();
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("nNotaCredito", String.class));
        tableColumns.add(new TableColumn("vigente", String.class));
        tableColumns.add(new TableColumn("fecha", String.class));
        tableColumns.add(new TableColumn("cuitProveedor", String.class));
        tableColumns.add(new TableColumn("total", String.class));
        this.tableModel = new NotaCreditoABMTable(this.notasCredito, tableColumns);
        return this.tableModel;
    }

    protected void agregar() {
        try {
            NotaCreditoABMDialog dialog = new NotaCreditoABMDialog(this.frame);
            dialog.setDefaultCloseOperation(2);
            dialog.setVisible(true);
            if (dialog.getModalResult() == ModalResult.OK) {
                this.tableModel.agregar(dialog.getDTO());
                ABMController controller = ABMController.getInstancia();
                controller.guardarNotasCredito(this.tableModel.lista);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    protected void borrar() {
        try {
            this.tableModel.eliminar(this.table.getSelectedRow());
            ABMController controller = ABMController.getInstancia();
            controller.guardarNotasCredito(this.tableModel.lista);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    protected void modificar() {
        try {
            NotaCreditoABMDialog dialog = new NotaCreditoABMDialog(this.frame);
            dialog.setDTO((DTONotaCredito)this.notasCredito.get(this.table.getSelectedRow()));
            dialog.setDefaultCloseOperation(2);
            dialog.setVisible(true);
            if (dialog.getModalResult() == ModalResult.OK) {
                this.tableModel.refresh();
                ABMController controller = ABMController.getInstancia();
                controller.guardarNotasCredito(this.tableModel.lista);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }
}