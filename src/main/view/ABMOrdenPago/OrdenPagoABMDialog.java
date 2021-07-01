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
    private JLabel documentosAsociadosLabel;
    private JTextField documentosAsociadosField;
    private JLabel retencionesLabel;
    private JTextField retencionesField;
    private JLabel totalLabel;
    private JTextField totalField;
    private JLabel formaPagoLabel;
    private JTextField formaPagoField;
    private JLabel chequeLabel;
    private JTextField chequeField;
    private JLabel efectivoLabel; //dudoso todo
    private JTextField efectivoField;

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

        documentosAsociadosLabel = new JLabel("Documentos Asociados");

        documentosAsociadosField = new JTextField();
        documentosAsociadosField.setColumns(10);

        retencionesLabel = new JLabel("Retenciones");

        retencionesField = new JTextField();
        retencionesField.setColumns(10);

        totalLabel = new JLabel("Total a Cancelar");

        totalField = new JTextField();
        totalField.setColumns(10);

        formaPagoLabel = new JLabel("Formas de Pago");

        formaPagoField = new JTextField();
        formaPagoField.setColumns(10);

        chequeLabel = new JLabel("Cheque");

        chequeField = new JTextField(); //nose si cheque y efect. van. Los inclui en la pantalla
        chequeField.setColumns(10); // pero nose si aca van

        efectivoLabel = new JLabel("Efectivo");

        efectivoField = new JTextField();
        efectivoField.setColumns(10);


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
                        .addComponent(formaPagoField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(chequeField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(efectivoField))
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
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chequeLabel)
                        .addComponent(chequeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(efectivoLabel).addComponent(efectivoLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE);
    }

    @Override
    protected void asignarDatosEntidad() {
        OrdenPago.DTOOrdenPago ordenPago= dto;
        if (ordenPago == null) {
            ordenPago = new OrdenPago.DTOOrdenPago();
        }
        ordenPago.fecha = DateUtil.toDate(fechaField.getText());
//        ordenPago.documentosAsociados = documentosAsociadosField.getText(); // son listas, me pide que lo cambie a string pero rompe el DTO
//        ordenPago.retenciones = Double.parseDouble(retencionesField.getText());
        ordenPago.totalACancelar = Double.parseDouble(totalField.getText());
//        ordenPago.formasDePago = formaPagoField.getText();
        dto = ordenPago;
    }

    @Override
    protected void asignarDatosForm() {
        OrdenPago.DTOOrdenPago ordenPago = dto;
        fechaField.setText(DateUtil.toString(ordenPago.fecha));
        documentosAsociadosField.setText(String.valueOf(ordenPago.documentosAsociados));
        retencionesField.setText(String.valueOf(ordenPago.retenciones));
        totalField.setText(String.valueOf(ordenPago.totalACancelar));
        formaPagoField.setText(String.valueOf(ordenPago.formasDePago));
    }

    @Override
    public OrdenPago.DTOOrdenPago getDTO() {
        return dto;
    }
}
