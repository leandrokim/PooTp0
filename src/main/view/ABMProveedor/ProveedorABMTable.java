package main.view.ABMProveedor;

import main.java.models.Proveedor.Proveedor;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

import java.util.ArrayList;

public class ProveedorABMTable extends AbstractModelTable<Proveedor.DTOProveedor> {

    public ProveedorABMTable(ArrayList<Proveedor.DTOProveedor> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proveedor.DTOProveedor dto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dto.cuitProveedor;
            case 1:
                return dto.nombreProveedor;
            case 2:
                return dto.emailProvedor;
            case 3:
                return dto.dirProvedor;
            case 4:
                return dto.telProvedor;
            default:
                return "";
        }
    }

}
