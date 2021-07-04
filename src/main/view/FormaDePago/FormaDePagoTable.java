package main.view.FormaDePago;

import java.util.ArrayList;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;
import main.java.models.FormaDePago.FormaPago;

public class FormaDePagoTable extends AbstractModelTable<FormaPago.DTOFormaPago> {
    public FormaDePagoTable (ArrayList<FormaPago.DTOFormaPago> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        FormaPago.DTOFormaPago dto = (DTOFormaPago) this.lista.get(rowIndex);
        switch(columnIndex) {
            case 0:
                return dto.cheque;
            case 1:
                return dto.efectivo;
            default:
                return "";
        }
    }
}