package main.java.models.dto;

import main.java.models.Documentos.Documento;
import main.java.models.Documentos.OrdenPago;

import java.util.ArrayList;

public class DTODocumentosPagosYDeudas {

    public int cuitProveedor;
    public ArrayList<Documento.DTODocumento> documentosImpagos;
    public ArrayList<Documento.DTODocumento> documentosRecibidos;
    public ArrayList<OrdenPago.DTOOrdenPago> pagosRealizados;
    public double totalDeuda;

    public DTODocumentosPagosYDeudas(int cuitProveedor,
                                     ArrayList<Documento.DTODocumento> documentosImpagos,
                                     ArrayList<Documento.DTODocumento> documentosRecibidos,
                                     ArrayList<OrdenPago.DTOOrdenPago> pagosRealizados,
                                     double totalDeuda) {
        this.cuitProveedor = cuitProveedor;
        this.documentosImpagos = documentosImpagos;
        this.documentosRecibidos = documentosRecibidos;
        this.pagosRealizados = pagosRealizados;
        this.totalDeuda = totalDeuda;
    }

}
