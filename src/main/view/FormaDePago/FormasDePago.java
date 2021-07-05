package main.view.FormaDePago;

import main.java.models.FormaDePago.FormaPago;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class FormasDePago extends AbstractABMWindow {

    private final ArrayList<FormaPago.DTOFormaPago> formaPagos;

    public FormasDePago(ArrayList<FormaPago.DTOFormaPago> formaPagos) {
        this.formaPagos = formaPagos;
        initialize();
    }

    @Override
    protected String getTitle() {
        return "Formas de Pago";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Tipo de Pago", String.class));
        tableColumns.add(new TableColumn("Importe", double.class));
        tableColumns.add(new TableColumn("Tipo Cheque", String.class));
        tableColumns.add(new TableColumn("Fecha de Emision", String.class));
        tableColumns.add(new TableColumn("Fecha de Vencimiento", String.class));
        tableColumns.add(new TableColumn("Firmante", String.class));
        return new FormaPagoTable(formaPagos, tableColumns);
    }

    //Is Not ABM
    @Override
    protected void agregar() {
    }

    @Override
    protected void borrar() {
    }

    @Override
    protected void modificar() {
    }

    @Override
    protected boolean isABM() {
        return false;
    }

}
