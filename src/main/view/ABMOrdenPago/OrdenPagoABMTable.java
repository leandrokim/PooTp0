package src.main.view.ABMOrdenPago;

import src.main.java.models.Documentos.OrdenPago;
import src.main.view.abm.TableColumn;

import java.util.ArrayList;

public class OrdenPagoABMTable {
    public OrdenPagoABMTable(ArrayList<OrdenPago.DTOOrdenPago> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            OrdenPago.DTOOrdenPago dto = lista.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return dto.fecha;
                case 1:
                    return dto.documentosAsociados;
                case 2:
                    return dto.totalACancelar;
                case 3:
                    return dto.retenciones;
                case 4:
                    return dto.formasDePago;
                default:
                    return "";
            }
        }

}
