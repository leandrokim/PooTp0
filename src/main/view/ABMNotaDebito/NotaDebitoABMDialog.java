
package main.view.ABMNotaDebito;

import java.time.LocalDate;
import javax.swing.GroupLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;
import main.java.models.Documentos.NotaDebito.DTONotaDebito;
import main.view.abm.AbstractABMDialog;

public class NotaDebitoABMDialog extends AbstractABMDialog<DTONotaDebito> {
    private JLabel nNotaDebitoLabel;
    private JTextField nNotaDebitoField;
    private JLabel vigenteLabel;
    private JCheckBox vigenteField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel cuitLabel;
    private JTextField cuitField;
    private JLabel fechaLabel;
    private JTextField fechaField;
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
        this.nameLabel = new JLabel("Nombre");
        this.nameField = new JTextField();
        this.nameField.setColumns(10);
        this.cuitLabel = new JLabel("Cuit");
        this.cuitField = new JTextField();
        this.cuitField.setColumns(10);
        this.fechaLabel = new JLabel("fecha");
        this.fechaField = new JTextField();
        this.fechaField.setColumns(8);
        this.cuitProveedorLabel = new JLabel("Cuit Proveedor");
        this.cuitProveedorField = new JTextField();
        this.cuitProveedorField.setColumns(10);
        this.totalLabel = new JLabel("TOTAL ($)");
        this.totalField = new JTextField();
        this.totalField.setColumns(10);
    }

    protected SequentialGroup getHorizontalGroup(GroupLayout group) {
        return group.createSequentialGroup().addContainerGap().addGroup(group.createParallelGroup(Alignment.LEADING).addComponent(this.nNotaDebitoLabel).addComponent(this.vigenteLabel).addComponent(this.nameLabel).addComponent(this.cuitLabel).addComponent(this.fechaLabel, -2, 37, -2).addComponent(this.cuitProveedorLabel).addComponent(this.totalLabel)).addPreferredGap(ComponentPlacement.RELATED).addGroup(group.createParallelGroup(Alignment.LEADING).addComponent(this.nNotaDebitoField, -2, 250, -2).addComponent(this.vigenteField, -2, 250, -2).addComponent(this.nameField, -2, -1, -2).addComponent(this.cuitField, -2, 250, -2).addComponent(this.fechaField, -2, 250, -2).addComponent(this.cuitProveedorField, -2, 250, -2).addComponent(this.totalField, -2, 250, -2)).addContainerGap(108, 32767);
    }

    protected SequentialGroup getVerticalGroup(GroupLayout group) {
        return group.createSequentialGroup().addContainerGap().addGroup(group.createParallelGroup(Alignment.BASELINE).addComponent(this.nNotaDebitoLabel).addComponent(this.nNotaDebitoField, -2, -1, -2)).addGroup(group.createParallelGroup(Alignment.BASELINE).addComponent(this.vigenteLabel).addComponent(this.vigenteField, -2, -1, -2)).addGroup(group.createParallelGroup(Alignment.BASELINE).addComponent(this.nameLabel).addComponent(this.nameField, -2, -1, -2)).addGroup(group.createParallelGroup(Alignment.BASELINE).addComponent(this.cuitLabel).addComponent(this.cuitField, -2, -1, -2)).addGap(4).addPreferredGap(ComponentPlacement.RELATED).addGroup(group.createParallelGroup(Alignment.BASELINE).addComponent(this.fechaLabel).addComponent(this.fechaField, -2, -1, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(group.createParallelGroup(Alignment.BASELINE).addComponent(this.cuitProveedorLabel).addComponent(this.cuitProveedorField, -2, -1, -2)).addPreferredGap(ComponentPlacement.RELATED).addGroup(group.createParallelGroup(Alignment.BASELINE).addComponent(this.totalLabel).addComponent(this.totalField, -2, -1, -2)).addPreferredGap(ComponentPlacement.RELATED).addContainerGap(60, 32767);
    }

    protected void asignarDatosEntidad() {
        DTONotaDebito notaDebito = (DTONotaDebito)this.dto;
        if (notaDebito == null) {
            notaDebito = new DTONotaDebito();
        }

        notaDebito.nNotaDeDebito = Integer.parseInt(this.nNotaDebitoField.getText());
        notaDebito.vigente = this.vigenteField.isSelected();
        notaDebito.nombreEmpresa = this.nameField.getText();
        notaDebito.cuitEmpresa = Integer.parseInt(this.cuitField.getText());
        notaDebito.fecha = LocalDate.parse(this.fechaField.getText());
        notaDebito.cuitProveedor = Integer.parseInt(this.cuitProveedorField.getText());
        notaDebito.total = Double.parseDouble(this.totalField.getText());
        this.dto = notaDebito;
    }

    protected void asignarDatosForm() {
        DTONotaDebito notaDebito = (DTONotaDebito)this.dto;
        this.vigenteField.setSelected(notaDebito.vigente);
        this.nameField.setText(notaDebito.nombreEmpresa);
        this.cuitField.setText(String.valueOf(notaDebito.cuitEmpresa));
        this.fechaField.setText(notaDebito.fecha.toString());
        this.cuitProveedorField.setText(String.valueOf(notaDebito.cuitProveedor));
        this.totalField.setText(String.valueOf(notaDebito.total));
    }

    public DTONotaDebito getDTO() {
        return (DTONotaDebito)this.dto;
    }
}
