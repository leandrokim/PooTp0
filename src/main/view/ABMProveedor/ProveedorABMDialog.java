package main.view.ABMProveedor;

import main.java.models.Proveedor.Proveedor;
import main.view.abm.AbstractABMDialog;

import javax.swing.*;

public class ProveedorABMDialog extends AbstractABMDialog<Proveedor.DTOProveedor> {

    private JLabel cuitLabel;
    private JTextField cuitField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel addressLabel;
    private JTextField addressField;
    private JLabel phoneLabel;
    private JTextField phoneField;
    private JLabel deudaLabel;
    private JTextField deudaField;
    private JCheckBox ivaField;

    public ProveedorABMDialog(JFrame frame) {
        super(frame, "Proveedor", true);
    }

    @Override
    protected void inicializarCampos() {
        cuitLabel = new JLabel("Cuit");

        cuitField = new JTextField();
        cuitField.setColumns(10);

        nameLabel = new JLabel("Nombre");

        nameField = new JTextField();
        nameField.setColumns(10);

        emailLabel = new JLabel("Email");

        emailField = new JTextField();
        emailField.setColumns(10);

        addressLabel = new JLabel("Direccion");

        addressField = new JTextField();
        addressField.setColumns(10);

        phoneLabel = new JLabel("Telefono");

        phoneField = new JTextField();
        phoneField.setColumns(10);

        deudaLabel = new JLabel("Deuda");

        deudaField = new JTextField();
        deudaField.setColumns(10);

        ivaField = new JCheckBox("Responsable IVA");
    }

    @Override
    protected GroupLayout.SequentialGroup getHorizontalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cuitLabel)
                        .addComponent(nameLabel)
                        .addComponent(emailLabel)
                        .addComponent(addressLabel)
                        .addComponent(phoneLabel)
                        .addComponent(deudaLabel)
                        .addComponent(ivaField))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cuitField)
                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(addressField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(phoneField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(deudaField))
                .addContainerGap(108, Short.MAX_VALUE);
    }

    @Override
    protected GroupLayout.SequentialGroup getVerticalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cuitLabel)
                        .addComponent(cuitField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(4)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel)
                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(emailLabel)
                        .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(addressLabel)
                        .addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(phoneLabel)
                        .addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(deudaLabel)
                        .addComponent(deudaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(ivaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE);
    }

    @Override
    protected void asignarDatosEntidad() {
        Proveedor.DTOProveedor proveedor = dto;
        if (proveedor == null) {
            proveedor = new Proveedor.DTOProveedor();
        }
        proveedor.cuitProveedor = Integer.parseInt(cuitField.getText());
        proveedor.nombreProveedor = nameField.getText();
        proveedor.emailProvedor = emailField.getText();
        proveedor.dirProvedor = addressField.getText();
        proveedor.telProvedor = phoneField.getText();
        proveedor.topeDeudaEmpresa = Double.parseDouble(deudaField.getText());
        proveedor.responsableIva = ivaField.isSelected();
        dto = proveedor;
    }

    @Override
    protected void asignarDatosForm() {
        Proveedor.DTOProveedor proveedor = dto;
        cuitField.setText(String.valueOf(proveedor.cuitProveedor));
        nameField.setText(proveedor.nombreProveedor);
        emailField.setText(proveedor.emailProvedor);
        addressField.setText(proveedor.dirProvedor);
        phoneField.setText(proveedor.telProvedor);
        deudaField.setText(String.valueOf(dto.topeDeudaEmpresa));
        ivaField.setSelected(dto.responsableIva);
    }

    @Override
    public Proveedor.DTOProveedor getDTO() {
        return dto;
    }
}
