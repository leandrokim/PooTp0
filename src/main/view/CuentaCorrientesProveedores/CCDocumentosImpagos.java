package main.view.CuentaCorrientesProveedores;

import main.java.models.Documentos.Documento;
import main.view.abm.AbstractABMWindow;
import main.view.abm.TableColumn;
import main.view.documentos.DocumentosTable;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class CCDocumentosImpagos extends AbstractABMWindow {

    private final ArrayList<Documento.DTODocumento> documentosImpagos;

    public CCDocumentosImpagos(ArrayList<Documento.DTODocumento> documentosRecibidos) {
        this.documentosImpagos = documentosRecibidos;
        initialize();
    }

    @Override
    protected String getTitle() {
        return "Documentos Impagos";
    }

    @Override
    protected AbstractTableModel getTableModel() {
        ArrayList<TableColumn> tableColumns = new ArrayList<>();
        tableColumns.add(new TableColumn("Tipo Documentos", String.class));
        tableColumns.add(new TableColumn("Numero", int.class));
        tableColumns.add(new TableColumn("Cuit Proveedor", int.class));
        tableColumns.add(new TableColumn("Fecha", String.class));
        tableColumns.add(new TableColumn("Total", double.class));
        return new DocumentosTable(documentosImpagos, tableColumns);
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
