package main.view.TotalDeImpuestosRetenidos;

import java.util.ArrayList;
import main.java.models.dto.DTOListadoDeImpuestosConNombreYTotalRetenido;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

public class TotalImpuestosRetenidosTable extends AbstractModelTable<DTOListadoDeImpuestosConNombreYTotalRetenido> {
    public TotalImpuestosRetenidosTable(ArrayList<DTOListadoDeImpuestosConNombreYTotalRetenido> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        DTOListadoDeImpuestosConNombreYTotalRetenido dto = (DTOListadoDeImpuestosConNombreYTotalRetenido)this.lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return dto.nombreDelImpuesto;
            case 1:
                return dto.totalRetenido;
            default:
                return "";
        }
    }
}