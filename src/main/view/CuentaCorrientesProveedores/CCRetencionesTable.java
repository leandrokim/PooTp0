package main.view.CuentaCorrientesProveedores;

import main.java.models.Proveedor.Retencion;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

import java.util.ArrayList;

public class CCRetencionesTable extends AbstractModelTable<Retencion.DTORetencion> {

    public CCRetencionesTable(ArrayList<Retencion.DTORetencion> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Retencion.DTORetencion dto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dto.idRetencion;
            case 1:
                return dto.impuesto.idImpuesto;
            case 2:
                return dto.impuesto.nombreImpuesto;
            case 3:
                return dto.impuesto.porcentaje;
            case 4:
                return dto.total;
            default:
                return "";
        }
    }

}
