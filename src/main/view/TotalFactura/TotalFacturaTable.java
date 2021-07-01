package main.view.TotalFactura;

import main.java.models.dto.DTOFacturas;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

import java.util.ArrayList;

public class TotalFacturaTable extends AbstractModelTable<DTOFacturas> {

    public TotalFacturaTable(ArrayList<DTOFacturas> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DTOFacturas dto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dto.cuitProveedor;
            case 1:
                return dto.fecha;
            case 2:
                return dto.cantFacturas;
            case 3:
                return dto.monto;
            default:
                return "";
        }
    }
}
