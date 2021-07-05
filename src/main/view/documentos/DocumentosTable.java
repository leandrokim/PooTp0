package main.view.documentos;

import main.java.models.Documentos.Documento;
import main.java.models.Documentos.Factura;
import main.java.models.Documentos.NotaCredito;
import main.java.models.Documentos.NotaDebito;
import main.java.util.DateUtil;
import main.view.abm.AbstractModelTable;
import main.view.abm.TableColumn;

import java.util.ArrayList;

public class DocumentosTable extends AbstractModelTable<Documento.DTODocumento> {

    public DocumentosTable(ArrayList<Documento.DTODocumento> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Documento.DTODocumento dto = lista.get(rowIndex);
        String tipoDocumento = "Factura";
        if (dto instanceof NotaCredito.DTONotaCredito) {
            tipoDocumento = "Nota de Credito";
        } else if (dto instanceof NotaDebito.DTONotaDebito) {
            tipoDocumento = "Nota de Debito";
        }
        switch (columnIndex) {
            case 0:
                return tipoDocumento;
            case 1:
                if (dto instanceof NotaCredito.DTONotaCredito) {
                    return ((NotaCredito.DTONotaCredito) dto).nNotaDeCredito;
                } else if (dto instanceof NotaDebito.DTONotaDebito) {
                    return ((NotaDebito.DTONotaDebito) dto).nNotaDeDebito;
                }
                return ((Factura.DTOFactura) dto).nFactura;
            case 2:
                return dto.cuitProveedor;
            case 3:
                return DateUtil.toString(dto.fecha);
            case 4:
                return dto.total;
            default:
                return "";
        }
    }

}
