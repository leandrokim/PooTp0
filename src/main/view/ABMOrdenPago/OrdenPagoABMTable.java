package main.view.ABMOrdenPago;

import main.java.models.Documentos.OrdenPago;
import main.java.util.DateUtil;
import main.view.FormaDePago.FormasDePago;
import main.view.documentos.Documentos;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;
import main.view.retenciones.Retenciones;

import javax.swing.*;
import java.util.ArrayList;

public class OrdenPagoABMTable extends AbstractModelTable<OrdenPago.DTOOrdenPago> {

    public OrdenPagoABMTable(ArrayList<OrdenPago.DTOOrdenPago> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

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
                JButton asociados = new JButton("Visualizar");
                asociados.addActionListener(e -> {
                    Documentos documentosRecibidos = new Documentos(dto.documentosAsociados);
                    documentosRecibidos.frame.setVisible(true);
                });
                return asociados;
            case 5:
                JButton retenciones = new JButton("Visualizar");
                retenciones.addActionListener(e -> {
                    Retenciones retencionView = new Retenciones(dto.retenciones);
                    retencionView.frame.setVisible(true);
                });
                return retenciones;
            case 6:
                JButton pagos = new JButton("Visualizar");
                pagos.addActionListener(e -> {
                    FormasDePago documentosRecibidos = new FormasDePago(dto.formasDePago);
                    documentosRecibidos.frame.setVisible(true);
                });
                return pagos;
            default:
                return "";
        }
    }

}
