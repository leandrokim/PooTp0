
package main.view.ABMNotaCredito;

import java.time.LocalDate;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import main.java.models.Documentos.NotaCredito.DTONotaCredito;
import main.java.util.DateUtil;
import main.view.abm.AbstractABMDialog;

public class NotaCreditoABMDialog extends AbstractABMDialog<DTONotaCredito> {
    private JLabel nNotaCreditoLabel;
    private JTextField nNotaCreditoField;
    private JLabel vigenteLabel;
    private JCheckBox vigenteField;
    private JLabel fechaLabel;
    private JFormattedTextField fechaField;
    private JLabel cuitProveedorLabel;
    private JTextField cuitProveedorField;
    private JLabel totalLabel;
    private JTextField totalField;

    public NotaCreditoABMDialog(JFrame frame) {
        super(frame, "NotaCredito", true);
    }

    protected void inicializarCampos() {
        this.nNotaCreditoLabel = new JLabel("N NotaCredito");
        this.nNotaCreditoField = new JTextField();
        this.nNotaCreditoField.setColumns(10);
        this.vigenteLabel = new JLabel("Vigente");
        this.vigenteField = new JCheckBox();
        this.fechaLabel = new JLabel("fecha");
        this.fechaField = new JFormattedTextField();
        try {
            fechaField.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.fechaField.setColumns(8);
        this.cuitProveedorLabel = new JLabel("Cuit Proveedor");
        this.cuitProveedorField = new JTextField();
        this.cuitProveedorField.setColumns(10);
        this.totalLabel = new JLabel("TOTAL ($)");
        this.totalField = new JTextField();
        this.totalField.setColumns(10);
    }

    protected SequentialGroup getHorizontalGroup(GroupLayout group) {
        return group.createSequentialGroup().addContainerGap()
                .addGroup(group.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.nNotaCreditoLabel)
                        .addComponent(this.vigenteLabel)
                        .addComponent(this.fechaLabel, -2, 37, -2)
                        .addComponent(this.cuitProveedorLabel)
                        .addComponent(this.totalLabel))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.nNotaCreditoField, -2, 250, -2)
                        .addComponent(this.vigenteField, -2, 250, -2)
                        .addComponent(this.fechaField, -2, 250, -2)
                        .addComponent(this.cuitProveedorField, -2, 250, -2)
                        .addComponent(this.totalField, -2, 250, -2))
                .addContainerGap(108, 32767);
    }

    protected SequentialGroup getVerticalGroup(GroupLayout group) {
        return group.createSequentialGroup().addContainerGap()
                .addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.nNotaCreditoLabel)
                        .addComponent(this.nNotaCreditoField, -2, -1, -2))
                .addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.vigenteLabel)
                        .addComponent(this.vigenteField, -2, -1, -2))
                .addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.fechaLabel).addComponent(this.fechaField, -2, -1, -2))
                .addPreferredGap(ComponentPlacement.RELATED).addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.cuitProveedorLabel).addComponent(this.cuitProveedorField, -2, -1, -2))
                .addPreferredGap(ComponentPlacement.RELATED).addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.totalLabel).addComponent(this.totalField, -2, -1, -2))
                .addPreferredGap(ComponentPlacement.RELATED).addContainerGap(60, 32767);
    }

    protected void asignarDatosEntidad() {
        DTONotaCredito notaCredito = this.dto;
        if (notaCredito == null) {
            notaCredito = new DTONotaCredito();
        }

        notaCredito.nNotaDeCredito = Integer.parseInt(this.nNotaCreditoField.getText());
        notaCredito.vigente = this.vigenteField.isSelected();
        notaCredito.fecha = DateUtil.toDate(fechaField.getText());
        notaCredito.cuitProveedor = Integer.parseInt(this.cuitProveedorField.getText());
        notaCredito.total = Double.parseDouble(this.totalField.getText());
        this.dto = notaCredito;
    }

    protected void asignarDatosForm() {
        DTONotaCredito notaCredito = this.dto;
        this.vigenteField.setSelected(notaCredito.vigente);
        this.fechaField.setText(DateUtil.toString(notaCredito.fecha));
        this.cuitProveedorField.setText(String.valueOf(notaCredito.cuitProveedor));
        this.totalField.setText(String.valueOf(notaCredito.total));
    }

    public DTONotaCredito getDTO() {
        return this.dto;
    }
}