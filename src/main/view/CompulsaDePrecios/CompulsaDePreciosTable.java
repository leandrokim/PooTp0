package main.view.CompulsaDePrecios;

import main.java.models.Productos.PrecioProductoPorProveedor;
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
        switch (columnIndex) {
            case 0:
                return dto.proveedor.cuitProveedor;
            case 1:
                return dto.proveedor.nombreProveedor;
            case 2:
                return dto.proveedor.emailProvedor;
            case 3:
                return dto.proveedor.dirProvedor;
            case 4:
                return dto.proveedor.telProvedor;
            case 5:
                return dto.proveedor.responsableIva;
            case 6:
                return dto.precio;
            case 7:
                return dto.producto.nombreProducto;
            default:
                return "";
        }
    }

}
