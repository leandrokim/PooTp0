package main.view.productos;

import main.java.models.Productos.PrecioProductoPorProveedor;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Productos extends AbstractABMWindow {

    private final ArrayList<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> productos;

    public Productos(ArrayList<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> productos) {
        this.productos = productos;
        initialize();
    }

    @Override
    protected String getTitle() {
        return "Productos";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Id Producto", int.class));
        tableColumns.add(new TableColumn("Nombre Producto", String.class));
        tableColumns.add(new TableColumn("Precio", double.class));
        tableColumns.add(new TableColumn("Cuit Proveedor", int.class));
        return new ProductosTable(productos, tableColumns);
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
