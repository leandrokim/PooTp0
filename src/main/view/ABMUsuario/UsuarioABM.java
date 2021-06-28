package src.main.view.ABMUsuario;

import src.main.java.controllers.ABMController;
import src.main.java.models.Usuario.Usuario;
import src.main.view.ABMOrdenPago.OrdenPagoABMTable;
import src.main.view.abm.AbstractABMWindow;
import src.main.view.abm.ModalResult;
import src.main.view.abm.TableColumn;

import javax.swing.*;
import java.util.ArrayList;

public class UsuarioABM extends AbstractABMWindow {

    private UsuarioABMTable tableModel;
    private ArrayList<Usuario.DTOUsuario> usuarios;

    public static void setVisible(boolean b) { // me lo crea el intellij para que no de error. Chequear
    }



    @Override
    protected String getTitle() {
        return "Usuario";
    }

    @Override
    protected OrdenPagoABMTable getTableModel() {
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
    protected void agregar() { // es necesario agregar si ya tenemos el registarse en el login
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
