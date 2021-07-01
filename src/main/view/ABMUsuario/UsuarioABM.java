package main.view.ABMUsuario;

import main.java.controllers.ABMController;
import main.java.models.Usuario.Usuario;
import main.view.abm.AbstractABMWindow;
import main.view.abm.AbstractModelTable;
import main.view.abm.ModalResult;
import main.view.abm.TableColumn;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class UsuarioABM extends AbstractABMWindow {

    private UsuarioABMTable tableModel;
    private ArrayList<Usuario.DTOUsuario> usuarios;

    @Override
    protected String getTitle() {
        return "Usuario";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ABMController controller = ABMController.getInstancia();
        usuarios = controller.getUsuarios();
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Usuario", String.class));
        tableColumns.add(new TableColumn("Contraseña", String.class));
        tableColumns.add(new TableColumn("Tipo", String.class));
        tableModel = new UsuarioABMTable(usuarios, tableColumns);

        return tableModel;
    }

    @Override
    protected void agregar() { // es necesario agregar si ya tenemos el registarse en el login?
        try {
            UsuarioABMDialog dialog = new UsuarioABMDialog(frame);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            //Esto se ejecuta cuando se apaga el modal
            if (dialog.getModalResult() == ModalResult.OK) {
                tableModel.agregar(dialog.getDTO());

                ABMController controller = ABMController.getInstancia();
                controller.guardarUsuarios(tableModel.lista);
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
            controller.guardarUsuarios(tableModel.lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void modificar() {
        try {
            UsuarioABMDialog dialog = new UsuarioABMDialog(frame);
            dialog.setDTO(usuarios.get(table.getSelectedRow()));
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            if (dialog.getModalResult() == ModalResult.OK) {
                tableModel.refresh();
                ABMController controller = ABMController.getInstancia();
                controller.guardarUsuarios(tableModel.lista);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
