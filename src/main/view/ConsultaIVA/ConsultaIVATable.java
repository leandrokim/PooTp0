package main.view.ConsultaIVA;

import main.java.models.IVA.Iva;
import main.java.models.dto.DTOConsultasDeLibroIVA;
import main.java.util.DateUtil;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

import java.util.ArrayList;

public class ConsultaIVATable extends AbstractModelTable<DTOConsultasDeLibroIVA> {

    public ConsultaIVATable(ArrayList<DTOConsultasDeLibroIVA> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DTOConsultasDeLibroIVA dto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dto.cuitProveedor;
            case 1:
                return dto.nombreProveedor;
            case 2:
                return DateUtil.toString(dto.fecha);
            case 3:
                return dto.tipoDocumento;
            case 4:
                StringBuilder builder = new StringBuilder();
                for (Iva iva : dto.iva) {
                    builder.append(iva.getTipoIVA().name())
                            .append(": ")
                            .append(iva.getTotal())
                            .append(", ");
                }
                return builder.toString();
            case 5:
                return dto.total;
            default:
                return "";
        }
    }

}
