package main.view.TotalDeImpuestosRetenidos;

import main.java.controllers.ConsultaGeneralController;
import main.java.models.dto.DTOListadoDeImpuestosConNombreYTotalRetenido;
import main.view.CompulsaDePrecios.CompulsaDePreciosTable;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TotalImpuestosRetenidos extends AbstractABMWindow {
    private JButton btnBuscar;

    @Override
    protected String getTitle() {
        return "Impuestos Retenidos";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ConsultaGeneralController controller = ConsultaGeneralController.getInstancia();
        ArrayList<DTOListadoDeImpuestosConNombreYTotalRetenido> listadoDeImpuestosConNombreYTotalRetenidos = controller.totalDeImpuestosRetenidos();
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Nombre Del Impuesto", int.class));
        tableColumns.add(new TableColumn("total", String.class));

        return new TotalImpuestosRetenidosTable(listadoDeImpuestosConNombreYTotalRetenidos, tableColumns);
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
