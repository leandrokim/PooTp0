package main.view.productos;

import main.java.controllers.ABMController;
import main.java.models.Productos.PrecioProductoPorProveedor;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

import java.util.ArrayList;

public class ProductosTable extends AbstractModelTable<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> {

    public ProductosTable(ArrayList<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor dto = lista.get(rowIndex);
        ABMController abmController = ABMController.getInstancia();
        switch (columnIndex) {
            case 0:
                return dto.producto;
            case 1:
                return abmController.getProducto(dto.producto).nombreProducto;
            case 2:
                return dto.precio;
            case 3:
                return dto.cuitProveedor;
            default:
                return "";
        }
    }

}
