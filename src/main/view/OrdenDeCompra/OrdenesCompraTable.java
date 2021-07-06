package main.view.OrdenDeCompra;

import main.java.controllers.ABMController;
import main.java.models.Documentos.OrdenCompra;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;
import main.view.productos.Productos;

import javax.swing.*;
import java.util.ArrayList;

public class OrdenesCompraTable extends AbstractModelTable<OrdenCompra.DTOOrdenCompra> {

    public OrdenesCompraTable(ArrayList<OrdenCompra.DTOOrdenCompra> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrdenCompra.DTOOrdenCompra dto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dto.nOrdenCompra;
            case 1:
                JButton productos = new JButton("Visualizar");
                productos.addActionListener(e -> {
                    Productos productosView = new Productos((ArrayList) dto.productos);
                    productosView.frame.setVisible(true);
                });
            case 2:
                return dto.totalPrecioAcordado;
            default:
                return "";
        }
    }

}
