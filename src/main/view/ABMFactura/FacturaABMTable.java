package main.view.ABMFactura;

import main.java.models.Documentos.Factura;
import main.java.util.DateUtil;
import main.view.FormaDePago.FormasDePago;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;
import main.view.productos.Productos;

import javax.swing.*;
import java.util.ArrayList;

public class FacturaABMTable extends AbstractModelTable<Factura.DTOFactura> {

    public FacturaABMTable(ArrayList<Factura.DTOFactura> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Factura.DTOFactura dto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dto.nFactura;
            case 1:
                return dto.cuitProveedor;
            case 2:
                return DateUtil.toString(dto.fecha);
            case 3:
                return dto.total;
            case 4:
                return dto.facturaPaga;
            case 5:
                return dto.responsabilidadIVA;
            case 6:
                JButton productos = new JButton("Visualizar");
                productos.addActionListener(e -> {
                    Productos productosView = new Productos(dto.productos);
                    productosView.frame.setVisible(true);
                });
                return productos;
            default:
                return "";
        }
    }

}
