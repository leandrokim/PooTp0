package main.view.ABMFactura;

import main.java.controllers.ABMController;
import main.java.models.Documentos.Factura;
import main.java.models.IVA.ResponsableIVA;
import main.java.util.DateUtil;
import main.view.abm.AbstractABMDialog;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class FacturaABMDialog extends AbstractABMDialog<Factura.DTOFactura> {

    private JLabel cuitProveedorLabel;
    private JTextField cuitProveedorField;
    private JLabel dateLabel;
    private JFormattedTextField dateField;
    private JLabel totalLabel;
    private JTextField totalField;
    private JCheckBox paidBox;
    private JLabel ivaLabel;
    private JComboBox ivaField;

    public FacturaABMDialog(JFrame frame) {
        super(frame, "Factura", true);
    }

    @Override
    protected void inicializarCampos() {
        cuitProveedorLabel = new JLabel("Cuit Proveedor");

        cuitProveedorField = new JTextField();
        cuitProveedorField.setColumns(10);

        dateLabel = new JLabel("Fecha");

        dateField = new JFormattedTextField();
        dateField.setColumns(10);
        try {
            dateField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        totalLabel = new JLabel("Total");

        totalField = new JTextField();
        totalField.setColumns(10);

        paidBox = new JCheckBox("Paga");

        ivaLabel = new JLabel("Responsable IVA");

        String[] ivaStrings = {ResponsableIVA.MONOTRIBUTO.name(), ResponsableIVA.RESPONSABLE_INSCRIPTO.name()};
        ivaField = new JComboBox(ivaStrings);
        ivaField.setSelectedIndex(0);
    }

    @Override
    protected GroupLayout.SequentialGroup getHorizontalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cuitProveedorLabel)
                        .addComponent(dateLabel)
                        .addComponent(totalLabel)
                        .addComponent(paidBox)
                        .addComponent(ivaLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cuitProveedorField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(totalField)
                        .addComponent(ivaField))
                .addContainerGap(108, Short.MAX_VALUE);
    }

    @Override
    protected GroupLayout.SequentialGroup getVerticalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cuitProveedorLabel)
                        .addComponent(cuitProveedorField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dateLabel)
                        .addComponent(dateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(totalLabel)
                        .addComponent(totalField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(paidBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(ivaLabel)
                        .addComponent(ivaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE);
    }

    @Override
    protected void asignarDatosEntidad() {
        Factura.DTOFactura factura = dto;
        if (factura == null) {
            factura = new Factura.DTOFactura();
            ABMController controller = ABMController.getInstancia();
            factura.nFactura = controller.nuevoNumeroFactura();
        }
        factura.cuitProveedor = Integer.parseInt(cuitProveedorField.getText());
        factura.fecha = DateUtil.toDate(dateField.getText());
        factura.total = Double.parseDouble(totalField.getText());
        factura.facturaPaga = paidBox.isSelected();
        int index = ivaField.getSelectedIndex();
        factura.responsabilidadIVA = index == 0 ? ResponsableIVA.MONOTRIBUTO : ResponsableIVA.RESPONSABLE_INSCRIPTO;
        dto = factura;
    }

    @Override
    protected void asignarDatosForm() {
        Factura.DTOFactura factura = dto;
        cuitProveedorField.setText(String.valueOf(factura.cuitProveedor));
        dateField.setText(DateUtil.toString(factura.fecha));
        totalField.setText(String.valueOf(factura.total));
        paidBox.setSelected(factura.facturaPaga);
        ivaField.setSelectedIndex(factura.responsabilidadIVA.equals(ResponsableIVA.MONOTRIBUTO) ? 0 : 1);
    }

    @Override
    public Factura.DTOFactura getDTO() {
        return dto;
    }

}
