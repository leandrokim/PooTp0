package main.view.ABMOrdenPago;

import main.java.models.Documentos.OrdenPago;
import main.java.util.DateUtil;
import main.view.abm.AbstractABMDialog;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class OrdenPagoABMDialog extends AbstractABMDialog<OrdenPago.DTOOrdenPago> {

    private JLabel fechaLabel;
    private JFormattedTextField fechaField;
    private JButton documentosAsociadosButton;
    private JButton retenciones;
    private JLabel totalLabel;
    private JTextField totalField;
    private JLabel formaPagoLabel;
    private JComboBox formaPagoField;

    public OrdenPagoABMDialog(JFrame frame) {
        super(frame, "Orden de Pago", true);
    }

    @Override
    protected void inicializarCampos() {
        fechaField = new JFormattedTextField();
        fechaField.setColumns(10);
        try {
            fechaField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        documentosAsociadosButton.addActionListener(e -> {
            Documento documentosAsociados= new Documento();
            documentosAsociados.frame.setVisible(true);
        });

        retencionesLabel = new JLabel("Retenciones");

        retencionesField = new JTextField();
        retencionesField.setColumns(10);

        totalLabel = new JLabel("Total a Cancelar");

        totalField = new JTextField();
        totalField.setColumns(10);

        String[] tipoStrings = {FormaDePago.CHEQUE.name(), FormaDePago.EFECTIVO.name()};
        formaPagoField = new JComboBox(tipoStrings);
        formaPagoField.setSelectedIndex(0);


    }

    @Override
    protected GroupLayout.SequentialGroup getHorizontalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fechaLabel)
                        .addComponent(documentosAsociadosLabel)
                        .addComponent(retencionesLabel)
                        .addComponent(totalLabel)
                        .addComponent(formaPagoLabel)
                        .addComponent(chequeLabel)
                        .addComponent(efectivoLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fechaField)
                        .addComponent(documentosAsociadosField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(retencionesField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(totalField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(formaPagoField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE);
    }

    @Override
    protected GroupLayout.SequentialGroup getVerticalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(fechaLabel)
                        .addComponent(fechaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(4)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(documentosAsociadosLabel)
                        .addComponent(documentosAsociadosField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(retencionesLabel)
                        .addComponent(retencionesField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(totalLabel)
                        .addComponent(totalField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(formaPagoLabel)
                        .addComponent(fechaField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE))
                .addContainerGap(60, Short.MAX_VALUE);
    }

    @Override
    protected void asignarDatosEntidad() {
        OrdenPago.DTOOrdenPago ordenPago= dto;
        if (ordenPago == null) {
            ordenPago = new OrdenPago.DTOOrdenPago();
        }
        ordenPago.fecha = DateUtil.toDate(fechaField.getText());
//        ordenPago.documentosAsociados = documentosAsociadosField.getText(); 
//        ordenPago.retenciones = Double.parseDouble(retencionesField.getText());
        ordenPago.totalACancelar = Double.parseDouble(totalField.getText());
        ordenPago.tipo = index == 0 ? FormaDePago.CHEQUE : FormaDePago.EFECTIVO;
        dto = ordenPago;
    }

    @Override
    protected void asignarDatosForm() {
        OrdenPago.DTOOrdenPago ordenPago = dto;
        fechaField.setText(DateUtil.toString(ordenPago.fecha));
        documentosAsociadosField.setText(String.valueOf(ordenPago.documentosAsociados));
        retencionesField.setText(String.valueOf(ordenPago.retenciones));
        totalField.setText(String.valueOf(ordenPago.totalACancelar));
        formaPagoField.setSelectedIndex(formaPago
                .tipo.equals(FromaDePago.CHEQUE) ? 0 : 1);
    }

    @Override
    public OrdenPago.DTOOrdenPago getDTO() {
        return dto;
    }
}
