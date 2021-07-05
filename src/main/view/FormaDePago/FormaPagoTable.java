package main.view.FormaDePago;

import main.java.models.FormaDePago.Cheque;
import main.java.models.FormaDePago.FormaPago;
import main.java.util.DateUtil;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

import java.util.ArrayList;

public class FormaPagoTable extends AbstractModelTable<FormaPago.DTOFormaPago> {

    public FormaPagoTable(ArrayList<FormaPago.DTOFormaPago> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FormaPago.DTOFormaPago dto = lista.get(rowIndex);
        String tipoPago = "Efectivo";
        if (dto instanceof Cheque.DTOCheque) {
            tipoPago = "Cheque";
        }
        switch (columnIndex) {
            case 0:
                return tipoPago;
            case 1:
                return dto.importe;
            case 2:
                if (dto instanceof Cheque.DTOCheque) {
                    return ((Cheque.DTOCheque) dto).tipo.name();
                }
                return "";
            case 3:
                if (dto instanceof Cheque.DTOCheque) {
                    return DateUtil.toString(((Cheque.DTOCheque) dto).fechaEmision);
                }
                return "";
            case 4:
                if (dto instanceof Cheque.DTOCheque) {
                    return DateUtil.toString(((Cheque.DTOCheque) dto).fechaVencimiento);
                }
                return "";
            case 5:
                if (dto instanceof Cheque.DTOCheque) {
                    return ((Cheque.DTOCheque) dto).firmante;
                }
                return "";
            default:
                return "";
        }
    }

}
