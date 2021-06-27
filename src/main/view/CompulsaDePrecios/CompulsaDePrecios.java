package main.view.CompulsaDePrecios;

import main.java.controllers.ConsultaGeneralController;
import main.java.models.Productos.PrecioProductoPorProveedor;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CompulsaDePrecios extends AbstractABMWindow {

    private final int productoId;
    private final int rubroId;

    public CompulsaDePrecios(int productoId, int rubroId) {
        this.productoId = productoId;
        this.rubroId = rubroId;

        initialize();
    }

    @Override
    protected String getTitle() {
        return "Compulsa de Precios";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ConsultaGeneralController controller = ConsultaGeneralController.getInstancia();
        ArrayList<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> precioProductoPorProveedores = controller.compulsaDePrecios(rubroId, productoId);
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Cuit Proveedor", int.class));
        tableColumns.add(new TableColumn("Nombre Proveedor", String.class));
        tableColumns.add(new TableColumn("Email", String.class));
        tableColumns.add(new TableColumn("Direccion", String.class));
        tableColumns.add(new TableColumn("Telefono", String.class));
        tableColumns.add(new TableColumn("ResponsableIva", boolean.class));
        tableColumns.add(new TableColumn("Precio Acordado", double.class));
        tableColumns.add(new TableColumn("Nombre Producto", String.class));
        return new CompulsaDePreciosTable(precioProductoPorProveedores, tableColumns);
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
