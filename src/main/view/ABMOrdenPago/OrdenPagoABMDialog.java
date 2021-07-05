package main.view.ABMOrdenPago;

import main.java.controllers.ABMController;
import main.java.models.Documentos.Factura;
import main.java.models.Documentos.NotaCredito;
import main.java.models.Documentos.NotaDebito;
import main.java.models.Documentos.OrdenPago;
import main.java.models.FormaDePago.Cheque;
import main.java.models.FormaDePago.Efectivo;
import main.java.models.FormaDePago.TipoCheque;
import main.view.abm.AbstractABMDialog;
import main.view.documentos.Documentos;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class OrdenPagoABMDialog extends AbstractABMDialog<OrdenPago.DTOOrdenPago> {

    private JLabel cuitProveedorLabel;
    private JTextField cuitProveedorField;
    private JLabel facturasLabel;
    private JTextField facturasField;
    private JLabel notaDebitoLabel;
    private JTextField notaDebitoField;
    private JLabel notaCreditoLabel;
    private JTextField notaCreditoField;
    private JLabel documentosButtonLabel;
    private JButton documentosButtonField;
    private JLabel efectivoLabel;
    private JTextField efectivoField;
    private JLabel chequeLabel;
    private JTextField chequeField;
    private JLabel chequeTipoLabel;
    private JComboBox chequeTipoField;

    public OrdenPagoABMDialog(JFrame frame) {
        super(frame, "Orden de Pago", true);
    }

    @Override
    protected void inicializarCampos() {
        cuitProveedorLabel = new JLabel("Cuit Proveedor");

        cuitProveedorField = new JTextField();
        cuitProveedorField.setColumns(10);

        facturasLabel = new JLabel("Facturas a asociar");

        facturasField = new JTextField();
        facturasField.setColumns(10);

        notaDebitoLabel = new JLabel("Notas de Debito a asociar");

        notaDebitoField = new JTextField();
        notaDebitoField.setColumns(10);

        notaCreditoLabel = new JLabel("Notas de Credito a asociar");

        notaCreditoField = new JTextField();
        notaCreditoField.setColumns(10);

        documentosButtonLabel = new JLabel("Documentos del Proveedor");

        documentosButtonField = new JButton("Visualizar");
        documentosButtonField.addActionListener(e -> {
            ABMController controller = ABMController.getInstancia();

            if (cuitProveedorField.getText().isEmpty() || !controller.existeProveedor(Integer.parseInt(cuitProveedorField.getText()))) {
                JOptionPane.showMessageDialog(null, "Ingrese Proveedor Valido");
                return;
            }

            Documentos documentos = new Documentos(controller.getDocumentosPorProveedor(Integer.parseInt(cuitProveedorField.getText())));
            documentos.frame.setVisible(true);
        });

        efectivoLabel = new JLabel("Efectivo a pagar");

        efectivoField = new JTextField();
        efectivoField.setColumns(10);

        chequeLabel = new JLabel("Monto cheque a pagar");

        chequeField = new JTextField();
        chequeField.setColumns(10);

        chequeTipoLabel = new JLabel("Tipo cheque");

        String[] tipoStrings = {TipoCheque.COMUN.name(),
                TipoCheque.DE_PAGO_DIFERIDO.name(),
                TipoCheque.CANCELATORIO.name()};
        chequeTipoField = new JComboBox(tipoStrings);
        chequeTipoField.setSelectedIndex(0);
    }

    @Override
    protected GroupLayout.SequentialGroup getHorizontalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cuitProveedorLabel)
                        .addComponent(facturasLabel)
                        .addComponent(notaDebitoLabel)
                        .addComponent(notaCreditoLabel)
                        .addComponent(documentosButtonLabel)
                        .addComponent(efectivoLabel)
                        .addComponent(chequeLabel)
                        .addComponent(chequeTipoLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cuitProveedorField)
                        .addComponent(facturasField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(notaDebitoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(notaCreditoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(documentosButtonField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(efectivoField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(chequeField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                        .addComponent(chequeTipoField, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE);
    }

    @Override
    protected GroupLayout.SequentialGroup getVerticalGroup(GroupLayout group) {
        return group.createSequentialGroup()
                .addContainerGap()
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(cuitProveedorLabel)
                        .addComponent(cuitProveedorField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(4)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(facturasLabel)
                        .addComponent(facturasField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(notaDebitoLabel)
                        .addComponent(notaDebitoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(notaCreditoLabel)
                        .addComponent(notaCreditoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(documentosButtonLabel)
                        .addComponent(documentosButtonField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(efectivoLabel)
                        .addComponent(efectivoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chequeLabel)
                        .addComponent(chequeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chequeTipoLabel)
                        .addComponent(chequeTipoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(group.createParallelGroup(GroupLayout.Alignment.BASELINE))
                .addContainerGap(60, Short.MAX_VALUE);
    }

    @Override
    protected void asignarDatosEntidad() {
        OrdenPago.DTOOrdenPago ordenPago = dto;
        if (ordenPago == null) {
            ordenPago = new OrdenPago.DTOOrdenPago();
        }
        ordenPago.cuitProveedor = Integer.parseInt(cuitProveedorField.getText());
        ordenPago.documentosAsociados = new ArrayList<>();
        for (String i : facturasField.getText().split(",")) {
            Factura.DTOFactura factura = new Factura.DTOFactura();
            factura.nFactura = Integer.parseInt(i);
            ordenPago.documentosAsociados.add(factura);
        }
        for (String i : notaDebitoField.getText().split(",")) {
            NotaDebito.DTONotaDebito notaDebito = new NotaDebito.DTONotaDebito();
            notaDebito.nNotaDeDebito = Integer.parseInt(i);
            ordenPago.documentosAsociados.add(notaDebito);
        }
        for (String i : notaCreditoField.getText().split(",")) {
            NotaCredito.DTONotaCredito notaCredito = new NotaCredito.DTONotaCredito();
            notaCredito.nNotaDeCredito = Integer.parseInt(i);
            ordenPago.documentosAsociados.add(notaCredito);
        }
        ordenPago.formasDePago = new ArrayList<>();
        if (!efectivoField.getText().isEmpty()) {
            Efectivo efectivo = new Efectivo(Double.parseDouble(efectivoField.getText()));
            ordenPago.formasDePago.add(efectivo.toDTO());
        }
        if (!chequeField.getText().isEmpty()) {
            Cheque cheque = new Cheque(Double.parseDouble(chequeField.getText()),
                    chequeTipoField.getSelectedIndex() == 0 ? TipoCheque.COMUN :
                            chequeTipoField.getSelectedIndex() == 1 ? TipoCheque.DE_PAGO_DIFERIDO :
                                    TipoCheque.CANCELATORIO,
                    LocalDate.now(),
                    LocalDate.now().plus(30, ChronoUnit.DAYS),
                    "Firmante");
            ordenPago.formasDePago.add(cheque.toDTO());
        }

        dto = ordenPago;
    }

    @Override
    protected void asignarDatosForm() {
        //no se puede modificar
    }

    @Override
    public OrdenPago.DTOOrdenPago getDTO() {
        return dto;
    }

}
