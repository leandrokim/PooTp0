package main.view.ABMProveedor;

import main.java.controllers.ABMController;
import main.java.models.Proveedor.Proveedor;
import main.view.abm.AbstractABMWindow;
import main.view.abm.ModalResult;
import main.view.abm.TableColumn;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProveedorABM extends AbstractABMWindow {

    private ProveedorABMTable tableModel;
    private ArrayList<Proveedor.DTOProveedor> proveedores;

    @Override
    protected String getTitle() {
        return "Proveedor";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ABMController controller = ABMController.getInstancia();
        proveedores = controller.getProveedores();
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Cuit", String.class));
        tableColumns.add(new TableColumn("Nombre", String.class));
        tableColumns.add(new TableColumn("Email", String.class));
        tableColumns.add(new TableColumn("Direccion", String.class));
        tableColumns.add(new TableColumn("Telefono", String.class));
        tableColumns.add(new TableColumn("ResponsableIva", boolean.class));
        tableColumns.add(new TableColumn("Tope Deuda", double.class));
        tableModel = new ProveedorABMTable(proveedores, tableColumns);

        return tableModel;
    }

    @Override
    protected void agregar() {
        try {
            ProveedorABMDialog dialog = new ProveedorABMDialog(frame);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            //Esto se ejecuta cuando se apaga el modal
            if (dialog.getModalResult() == ModalResult.OK) {
                tableModel.agregar(dialog.getDTO());

                ABMController controller = ABMController.getInstancia();
                controller.guardarProveedores(tableModel.lista);
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
            controller.guardarProveedores(tableModel.lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void modificar() {
        try {
            ProveedorABMDialog dialog = new ProveedorABMDialog(frame);
            dialog.setDTO(proveedores.get(table.getSelectedRow()));
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            if (dialog.getModalResult() == ModalResult.OK) {
                tableModel.refresh();
                ABMController controller = ABMController.getInstancia();
                controller.guardarProveedores(tableModel.lista);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
