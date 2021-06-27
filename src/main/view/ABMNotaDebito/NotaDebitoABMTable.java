
package main.view.ABMNotaDebito;

import java.util.ArrayList;
import main.java.models.Documentos.NotaDebito.DTONotaDebito;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

public class NotaDebitoABMTable extends AbstractModelTable<DTONotaDebito> {
    public NotaDebitoABMTable(ArrayList<DTONotaDebito> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        DTONotaDebito dto = (DTONotaDebito)this.lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return dto.nNotaDebito;
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