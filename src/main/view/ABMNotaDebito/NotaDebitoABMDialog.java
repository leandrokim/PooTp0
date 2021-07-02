
package main.view.ABMNotaDebito;

import main.java.models.Documentos.NotaDebito.DTONotaDebito;
import main.java.util.DateUtil;
import main.view.abm.AbstractABMDialog;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class NotaDebitoABMDialog extends AbstractABMDialog<DTONotaDebito> {
    private JLabel nNotaDebitoLabel;
    private JTextField nNotaDebitoField;
    private JLabel vigenteLabel;
    private JCheckBox vigenteField;
    private JLabel fechaLabel;
    private JFormattedTextField fechaField;
    private JLabel cuitProveedorLabel;
    private JTextField cuitProveedorField;
    private JLabel totalLabel;
    private JTextField totalField;

    public NotaDebitoABMDialog(JFrame frame) {
        super(frame, "NotaCredito", true);
    }

    protected void inicializarCampos() {
        this.nNotaDebitoLabel = new JLabel("N NotaDebito");
        this.nNotaDebitoField = new JTextField();
        this.nNotaDebitoField.setColumns(10);
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
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.nNotaDebitoLabel)
                        .addComponent(this.vigenteLabel)
                        .addComponent(this.fechaLabel, -2, 37, -2)
                        .addComponent(this.cuitProveedorLabel)
                        .addComponent(this.totalLabel))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(Alignment.LEADING)
                        .addComponent(this.nNotaDebitoField, -2, 250, -2)
                        .addComponent(this.vigenteField, -2, 250, -2)
                        .addComponent(this.fechaField, -2, 250, -2)
                        .addComponent(this.cuitProveedorField, -2, 250, -2)
                        .addComponent(this.totalField, -2, 250, -2))
                .addContainerGap(108, 32767);
    }

    protected SequentialGroup getVerticalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.nNotaDebitoLabel)
                        .addComponent(this.nNotaDebitoField, -2, -1, -2))
                .addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.vigenteLabel)
                        .addComponent(this.vigenteField, -2, -1, -2))
                .addGap(4)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.fechaLabel)
                        .addComponent(this.fechaField, -2, -1, -2))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.cuitProveedorLabel)
                        .addComponent(this.cuitProveedorField, -2, -1, -2))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(Alignment.BASELINE)
                        .addComponent(this.totalLabel)
                        .addComponent(this.totalField, -2, -1, -2))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addContainerGap(60, 32767);
    }

    protected void asignarDatosEntidad() {
        DTONotaDebito notaDebito = this.dto;
        if (notaDebito == null) {
            notaDebito = new DTONotaDebito();
        }

        notaDebito.nNotaDeDebito = Integer.parseInt(this.nNotaDebitoField.getText());
        notaDebito.vigente = this.vigenteField.isSelected();
        notaDebito.fecha = DateUtil.toDate(fechaField.getText());
        notaDebito.cuitProveedor = Integer.parseInt(this.cuitProveedorField.getText());
        notaDebito.total = Double.parseDouble(this.totalField.getText());
        this.dto = notaDebito;
    }

    protected void asignarDatosForm() {
        DTONotaDebito notaDebito = this.dto;
        this.vigenteField.setSelected(notaDebito.vigente);
        this.fechaField.setText(DateUtil.toString(notaDebito.fecha));
        this.cuitProveedorField.setText(String.valueOf(notaDebito.cuitProveedor));
        this.totalField.setText(String.valueOf(notaDebito.total));
    }

    public DTONotaDebito getDTO() {
        return this.dto;
    }
}
