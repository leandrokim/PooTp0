package main.view.OrdenDeCompra;


import main.java.models.Documentos.OrdenCompra;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.util.ArrayList;

public class OrdenesDeCompra extends AbstractABMWindow {

    private  ArrayList<OrdenCompra.DTOOrdenCompra> ordenCompras;

    public OrdenesDeCompra(ArrayList<OrdenCompra.DTOOrdenCompra> ordenCompras) {
        this.ordenCompras = ordenCompras;
        initialize();
    }

    @Override
    protected String getTitle() {
        return "Ordenes de Compra";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("id OrdenCompra", int.class));
        tableColumns.add(new TableColumn("Productos", JButton.class));
        tableColumns.add(new TableColumn("totalPrecioAcordado", double.class));
        return new OrdenesCompraTable(ordenCompras, tableColumns);
    }

    //Is Not ABM
    @Override
    protected void agregar() {
    }

    @Override
    protected void borrar() {
    }

    @Override
    protected void modificar() {
    }

    @Override
    protected boolean isABM() {
        return false;
    }

}
