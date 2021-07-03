package main.view.CompulsaDePrecios;

import main.java.models.Productos.PrecioProductoPorProveedor;
import main.java.models.Productos.Producto;
import main.java.models.Proveedor.Proveedor;
import main.java.util.DTOUtil;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

import java.util.ArrayList;

public class CompulsaDePreciosTable extends AbstractModelTable<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> {

    public CompulsaDePreciosTable(ArrayList<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor dto = lista.get(rowIndex);
        Proveedor.DTOProveedor proveedor = DTOUtil.toClass(dto, PrecioProductoPorProveedor.class).getProveedor();
        Producto producto = DTOUtil.toClass(dto, PrecioProductoPorProveedor.class).getProducto();
        switch (columnIndex) {
            case 0:
                return proveedor.cuitProveedor;
            case 1:
                return proveedor.nombreProveedor;
            case 2:
                return proveedor.emailProvedor;
            case 3:
                return proveedor.dirProvedor;
            case 4:
                return proveedor.telProvedor;
            case 5:
                return proveedor.responsableIva;
            case 6:
                return dto.precio;
            case 7:
                return producto.getNombreProducto();
            default:
                return "";
        }
    }

}
