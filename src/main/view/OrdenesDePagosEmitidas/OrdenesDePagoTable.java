package main.view.OrdenesDePagosEmitidas;

import main.java.models.Documentos.OrdenPago;
import main.java.util.DateUtil;
import main.view.FormaDePago.FormasDePago;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;
import main.view.documentos.Documentos;
import main.view.retenciones.Retenciones;

import javax.swing.*;
import java.util.ArrayList;

public class OrdenesDePagoTable extends AbstractModelTable<OrdenPago.DTOOrdenPago> {
    public OrdenesDePagoTable(ArrayList<OrdenPago.DTOOrdenPago> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        OrdenPago.DTOOrdenPago dtoOP = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dtoOP.nroOrdenPago;
            case 1:
                return dtoOP.cuitProveedor;
            case 2:
                return DateUtil.toString(dtoOP.fecha);
            case 3:
                return dtoOP.totalACancelar;
            case 4:
                JButton asociados = new JButton("Visualizar");
                asociados.addActionListener(e -> {
                    Documentos documentosAsociados = new Documentos(dtoOP.documentosAsociados);
                    documentosAsociados.frame.setVisible(true);
                });
                return asociados;
            case 5:
                JButton pagos = new JButton("Visualizar");
                pagos.addActionListener(e -> {
                    FormasDePago formasDePago = new FormasDePago(dtoOP.formasDePago);
                    formasDePago.frame.setVisible(true);
                });
                return pagos;
            case 6:
                JButton retenciones = new JButton("Visualizar");
                retenciones.addActionListener(e -> {
                    Retenciones rets = new Retenciones(dtoOP.retenciones);
                    rets.frame.setVisible(true);
                });
                return retenciones;
            default:
                return "";
        }
    }
}
