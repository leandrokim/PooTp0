package src.main.view.ABMUsuario;

import src.main.java.models.IVA.ResponsableIVA;
import src.main.java.models.Usuario.Usuario;
import src.main.java.models.Usuario.TipoUsuario;
import src.main.view.abm.AbstractABMDialog;

import javax.swing.*;

public class UsuarioABMDialog extends AbstractABMDialog<Usuario.DTOUsuario> {

    private JLabel nombreLabel;
    private JTextField nombreField;
    private JLabel tipoLabel;
    private JTextField tipoField;

    public UsuarioABMDialog(JFrame frame) {
        super(frame, "Usuario", true);
    }

    @Override
    protected void inicializarCampos() {
        nombreLabel = new JLabel("Nombre");

        nombreField = new JTextField();
        nombreField.setColumns(10);

        tipoLabel = new JLabel("Contraseña");

        tipoField = new JTextField();
        tipoField.setColumns(10);

    }

    @Override
    protected GroupLayout.SequentialGroup getHorizontalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nombreLabel)
                        .addComponent(tipoLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nombreField)
                        .addComponent(tipoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE);
    }

    @Override
    protected GroupLayout.SequentialGroup getVerticalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nombreLabel)
                        .addComponent(nombreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(4)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(tipoLabel)
                        .addComponent(tipoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE);
    }

    @Override
    protected void asignarDatosEntidad() {
        Usuario.DTOUsuario usuario = dto;
        if (usuario == null) {
            usuario = new Usuario.DTOUsuario();
        }
        usuario.nombre = (nombreField).getText();
        int index = tipoField.getSelectedIndex();
        usuario.tipo = index == 0 ? TipoUsuario.USUARIO: TipoUsuario.SUPERVISOR;
        dto = usuario;
    }

    @Override
    protected void asignarDatosForm() {
        Usuario.DTOUsuario usuario = dto;
        nombreField.setText(usuario.nombre);
        tipoField.setSelectedIndex(usuario
                .tipo.equals(TipoUsuario.USUARIO) ? 0 : 1);
    }

    @Override
    public Usuario.DTOUsuario getDTO() {
        return dto;
    }
}
