package main.view.CuentaCorrientesProveedores;

import main.java.models.Documentos.*;
import main.java.util.DateUtil;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

import javax.swing.*;
import java.util.ArrayList;

public class CCOrdenPagoTable extends AbstractModelTable<OrdenPago.DTOOrdenPago> {

    public CCOrdenPagoTable(ArrayList<OrdenPago.DTOOrdenPago> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    /**
     * public static class DTOOrdenPago {
     * public int nroOrdenPago;
     * public int cuitProveedor;
     * public LocalDate fecha;
     * public double totalACancelar;
     * public List<Documento.DTODocumento> documentosAsociados;
     * public List<FormaPago.DTOFormaPago> formasDePago;
     * public List<Retencion.DTORetencion> retenciones;
     * }
     *
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrdenPago.DTOOrdenPago dto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dto.nroOrdenPago;
            case 1:
                return dto.cuitProveedor;
            case 2:
                return DateUtil.toString(dto.fecha);
            case 3:
                return dto.totalACancelar;
            case 4:
                JButton recibidos = new JButton("Visualizar");
                recibidos.addActionListener(e -> {
                    CCDocumentosAsociados documentosAsociados = new CCDocumentosAsociados(dto.documentosAsociados);
                    documentosAsociados.frame.setVisible(true);
                });
                return recibidos;
            case 5:
                JButton impagos = new JButton("Visualizar");
                impagos.addActionListener(e -> {
                    CCFormasDePago formasDePago = new CCFormasDePago(dto.formasDePago);
                    formasDePago.frame.setVisible(true);
                });
                return impagos;
            case 6:
                JButton pagos = new JButton("Visualizar");
                pagos.addActionListener(e -> {
                    CCRetenciones retenciones = new CCRetenciones(dto.retenciones);
                    retenciones.frame.setVisible(true);
                });
                return pagos;
            default:
                return "";
        }
    }

}
