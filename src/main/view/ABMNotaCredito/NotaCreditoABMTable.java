
package main.view.ABMNotaCredito;

import java.util.ArrayList;
import main.java.models.Documentos.NotaCredito.DTONotaCredito;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

public class NotaCreditoABMTable extends AbstractModelTable<DTONotaCredito> {
    public NotaCreditoABMTable(ArrayList<DTONotaCredito> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        DTONotaCredito dto = (DTONotaCredito)this.lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return dto.nNotaDeCredito;
            case 1:
                return dto.vigente;
            case 2:
                return dto.nombreEmpresa;
            case 3:
                return dto.cuitEmpresa;
            case 4:
                return dto.fecha;
            case 5:
                return dto.cuitProveedor;
            case 6:
                return dto.total;
            default:
                return "";
        }
    }
}