package main.view.ABMUsuario;

import main.java.models.IVA.ResponsableIVA;
import main.java.models.Usuario.TipoUsuario;
import main.java.models.Usuario.Usuario;
import main.view.abm.AbstractABMDialog;

import javax.swing.*;

public class UsuarioABMDialog extends AbstractABMDialog<Usuario.DTOUsuario> {

    private JLabel nombreLabel;
    private JTextField nombreField;
    private JLabel passwordLabel;
    private JTextField passwordField;
    private JLabel tipoLabel;
    private JComboBox tipoField;

    public UsuarioABMDialog(JFrame frame) {
        super(frame, "Usuario", true);
    }

    @Override
    protected void inicializarCampos() {
        nombreLabel = new JLabel("Nombre");

        nombreField = new JTextField();
        nombreField.setColumns(10);

        passwordLabel = new JLabel("Contraseña");

        passwordField = new JTextField();
        passwordField.setColumns(10);

        tipoLabel = new JLabel("Tipo de usuario");

        String[] tipoStrings = {TipoUsuario.USUARIO.name(), TipoUsuario.SUPERVISOR.name()};
        tipoField = new JComboBox(tipoStrings);
        tipoField.setSelectedIndex(0);
    }

    @Override
    protected GroupLayout.SequentialGroup getHorizontalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nombreLabel)
                        .addComponent(passwordLabel)
                        .addComponent(tipoLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nombreField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(tipoField))
                .addContainerGap(108, Short.MAX_VALUE);
    }

    @Override
    protected GroupLayout.SequentialGroup getVerticalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nombreLabel)
                        .addComponent(nombreField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(passwordLabel)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
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
        usuario.nombre = nombreField.getText();
        usuario.password = passwordField.getText();
        int index = tipoField.getSelectedIndex();
        usuario.tipo = index == 0 ? TipoUsuario.USUARIO : TipoUsuario.SUPERVISOR;
        dto = usuario;
    }

    @Override
    protected void asignarDatosForm() {
        Usuario.DTOUsuario usuario = dto;
        nombreField.setText(usuario.nombre);
        passwordField.setText(usuario.password);
        tipoField.setSelectedIndex(usuario
                .tipo.equals(TipoUsuario.USUARIO) ? 0 : 1);
    }

    @Override
    public Usuario.DTOUsuario getDTO() {
        return dto;
    }

}
