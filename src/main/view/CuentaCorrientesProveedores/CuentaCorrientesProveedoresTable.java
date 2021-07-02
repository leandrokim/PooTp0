package main.view.CuentaCorrientesProveedores;

import main.java.models.dto.DTODocumentosPagosYDeudas;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

import javax.swing.*;
import java.util.ArrayList;

public class CuentaCorrientesProveedoresTable extends AbstractModelTable<DTODocumentosPagosYDeudas> {

    public CuentaCorrientesProveedoresTable(ArrayList<DTODocumentosPagosYDeudas> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DTODocumentosPagosYDeudas dto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dto.cuitProveedor;
            case 1:
                return dto.totalDeuda;
            case 2:
                JButton recibidos = new JButton("Visualizar");
                recibidos.addActionListener(e -> {
                    CCDocumentosRecibidos documentosRecibidos = new CCDocumentosRecibidos(dto.documentosRecibidos);
                    documentosRecibidos.frame.setVisible(true);
                });
                return recibidos;
            case 3:
                JButton impagos = new JButton("Visualizar");
                impagos.addActionListener(e -> {
                    CCDocumentosImpagos documentosImpagos = new CCDocumentosImpagos(dto.documentosImpagos);
                    documentosImpagos.frame.setVisible(true);
                });
                return impagos;
            case 4:
                JButton pagos = new JButton("Visualizar");
                pagos.addActionListener(e -> {
                    CCPagosRealizados pagosRealizados = new CCPagosRealizados(dto.pagosRealizados);
                    pagosRealizados.frame.setVisible(true);
                });
                return pagos;
            default:
                return "";
        }
    }

}
