package main.view.ABMNotaDebito;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import main.java.controllers.ABMController;
import main.java.models.Documentos.NotaDebito.DTONotaDebito;
import main.view.abm.AbstractABMWindow;
import main.view.abm.ModalResult;
import main.view.abm.TableColumn;

public class NotaDebitoABM extends AbstractABMWindow {
    private NotaDebitoABMTable tableModel;
    private ArrayList<DTONotaDebito> notasDebito;

    public NotaDebitoABM() {
    }

    protected String getTitle() {
        return "Nota de Debito";
    }

    protected AbstractTableModel getTableModel() {
        ABMController controller = ABMController.getInstancia();
        this.notasDebito = controller.getNotasDebito();
        ArrayList<TableColumn> tableColumns = new ArrayList();
        tableColumns.add(new TableColumn("nNotaDebito", String.class));
        tableColumns.add(new TableColumn("vigente", String.class));
        tableColumns.add(new TableColumn("nombreEmpresa", String.class));
        tableColumns.add(new TableColumn("cuitEmpresa", String.class));
        tableColumns.add(new TableColumn("fecha", String.class));
        tableColumns.add(new TableColumn("cuitProveedor", String.class));
        tableColumns.add(new TableColumn("total", String.class));
        this.tableModel = new NotaDebitoABMTable(this.notasDebito, tableColumns);
        return this.tableModel;
    }

    protected void agregar() {
        try {
            NotaDebitoABMDialog dialog = new NotaDebitoABMDialog(this.frame);
            dialog.setDefaultCloseOperation(2);
            dialog.setVisible(true);
            if (dialog.getModalResult() == ModalResult.OK) {
                this.tableModel.agregar(dialog.getDTO());
                ABMController controller = ABMController.getInstancia();
                controller.guardarNotasDebito(this.tableModel.lista);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    protected void borrar() {
        try {
            this.tableModel.eliminar(this.table.getSelectedRow());
            ABMController controller = ABMController.getInstancia();
            controller.guardarNotasDebito(this.tableModel.lista);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    protected void modificar() {
        try {
            NotaDebitoABMDialog dialog = new NotaDebitoABMDialog(this.frame);
            dialog.setDTO((DTONotaDebito)this.notasDebito.get(this.table.getSelectedRow()));
            dialog.setDefaultCloseOperation(2);
            dialog.setVisible(true);
            if (dialog.getModalResult() == ModalResult.OK) {
                this.tableModel.refresh();
                ABMController controller = ABMController.getInstancia();
                controller.guardarNotasDebito(this.tableModel.lista);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }
}