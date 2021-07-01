package main.view.TotalFactura;

import main.java.controllers.ConsultaGeneralController;
import main.java.models.dto.DTOFacturas;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public class TotalFactura extends AbstractABMWindow {
    private final int cuitProveedor;
    private final LocalDate fecha;

    public TotalFactura(int cuitProveedor, LocalDate fecha) {
        this.cuitProveedor = cuitProveedor;
        this.fecha = fecha;

        initialize();
    }

    @Override
    protected String getTitle() {
        return "Total Factura";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ConsultaGeneralController controller = ConsultaGeneralController.getInstancia();
        ArrayList<DTOFacturas> factura = controller.facturasPorDiaOProveedor(fecha,cuitProveedor);
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Cuit del proveedor", int.class));
        tableColumns.add(new TableColumn("Fecha", LocalDate.class));
        tableColumns.add(new TableColumn("Cantidad de facturas", int.class));
        tableColumns.add(new TableColumn("Monto", double.class));
        return new TotalFacturaTable(factura, tableColumns);
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
