package main.java.models.dto;

import main.java.models.Documentos.Documento;
import main.java.models.Documentos.OrdenPago;

import java.util.List;

public class DTODocumentosPagosYDeudas {

    public int cuitProveedor;
    public List<Documento.DTODocumento> documentosImpagos;
    public List<Documento.DTODocumento> documentosRecibidos;
    public List<OrdenPago.DTOOrdenPago> pagosRealizados;
    public double totalDeuda;

    public DTODocumentosPagosYDeudas(int cuitProveedor,
                                     List<Documento.DTODocumento> documentosImpagos,
                                     List<Documento.DTODocumento> documentosRecibidos,
                                     List<OrdenPago.DTOOrdenPago> pagosRealizados,
                                     double totalDeuda) {
        this.cuitProveedor = cuitProveedor;
        this.documentosImpagos = documentosImpagos;
        this.documentosRecibidos = documentosRecibidos;
        this.pagosRealizados = pagosRealizados;
        this.totalDeuda = totalDeuda;
    }
}
