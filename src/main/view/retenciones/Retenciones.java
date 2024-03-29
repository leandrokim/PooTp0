package main.view.retenciones;

import main.java.models.Proveedor.Retencion;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Retenciones extends AbstractABMWindow {

    private final ArrayList<Retencion.DTORetencion> retencions;

    public Retenciones(ArrayList<Retencion.DTORetencion> retencions) {
        this.retencions = retencions;
        initialize();
    }

    @Override
    protected String getTitle() {
        return "Retenciones";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Id", int.class));
        tableColumns.add(new TableColumn("Id Impuesto", int.class));
        tableColumns.add(new TableColumn("Nombre Impuesto", String.class));
        tableColumns.add(new TableColumn("Porcentaje", double.class));
        tableColumns.add(new TableColumn("Total", double.class));
        return new RetencionesTable(retencions, tableColumns);
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
