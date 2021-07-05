
package main.view.ABMNotaDebito;

import java.util.ArrayList;

import main.java.models.Documentos.NotaDebito.DTONotaDebito;
import main.java.util.DateUtil;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;
import main.view.productos.Productos;

import javax.swing.*;

public class NotaDebitoABMTable extends AbstractModelTable<DTONotaDebito> {
    public NotaDebitoABMTable(ArrayList<DTONotaDebito> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        DTONotaDebito dto = this.lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dto.nNotaDeDebito;
            case 1:
                return dto.vigente;
            case 2:
                return DateUtil.toString(dto.fecha);
            case 3:
                return dto.cuitProveedor;
            case 4:
                return dto.total;
            case 5:
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