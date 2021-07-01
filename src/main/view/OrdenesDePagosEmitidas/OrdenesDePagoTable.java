package main.view.OrdenesDePagosEmitidas;

import main.java.models.Documentos.Documento;
import main.java.models.Documentos.OrdenPago;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

import javax.swing.*;
import java.util.ArrayList;

public class OrdenesDePagoTable extends AbstractModelTable<OrdenPago.DTOOrdenPago> {
    public OrdenesDePagoTable(ArrayList<OrdenPago.DTOOrdenPago> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        OrdenPago.DTOOrdenPago dtoOP = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dtoOP.totalACancelar;
            case 1:
                return dtoOP.retenciones;
            case 2:
                JButton asociados = new JButton("Visualizar");
                asociados.addActionListener(e -> {
                    OPDocumentosAsociados documentosAsociados = new OPDocumentosAsociados((ArrayList<Documento.DTODocumento>) dtoOP.documentosAsociados);
                    documentosAsociados.frame.setVisible(true);
                });
                return asociados;
            case 3:
                //Button impagos = new JButton("Visualizar");
//                impagos.addActionListener(e -> {
//
//                });
                return dtoOP.formasDePago; //TODO

            default:
                return "";
        }
    }
}
