package main.view.ConsultaIVA;

import main.java.controllers.ConsultaGeneralController;
import main.java.models.dto.DTOConsultasDeLibroIVA;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ConsultaIVA extends AbstractABMWindow {

    public ConsultaIVA() {
        super();
    }

    @Override
    protected String getTitle() {
        return "Consulta IVA";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ConsultaGeneralController controller = ConsultaGeneralController.getInstancia();
        ArrayList<DTOConsultasDeLibroIVA> datos = controller.consultaLibroIVACompras();
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Cuit Proveedor", int.class));
        tableColumns.add(new TableColumn("Nombre Proveedor", String.class));
        tableColumns.add(new TableColumn("Fecha", String.class));
        tableColumns.add(new TableColumn("Tipo Documento", String.class));
        tableColumns.add(new TableColumn("IVAs", String.class));
        tableColumns.add(new TableColumn("Total", double.class));
        return new ConsultaIVATable(datos, tableColumns);
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
