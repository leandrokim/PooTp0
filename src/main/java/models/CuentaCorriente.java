package main.java.models;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CuentaCorriente {

    private Proveedor proveedor;
    private List<Documento> documentos;
    private List<OrdenPago> ordenesDePago;

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<OrdenPago> getOrdenesDePago() {
        return ordenesDePago;
    }

    public void setOrdenesDePago(List<OrdenPago> ordenesDePago) {
        this.ordenesDePago = ordenesDePago;
    }

    public CuentaCorrienteDTO toDTO() {
        return null;
    }

    public static class CuentaCorrienteDTO {

    }

    public int getCuitProveedor() {
        return proveedor.getCuitProveedor();
    }

    public List<NotaCredito> getNotasCredito() {
        return documentos.stream().filter(documento -> documento instanceof NotaCredito)
                .map(documento -> (NotaCredito) documento)
                .collect(Collectors.toList());
    }

    public List<NotaDebito> getNotasDebito() {
        return documentos.stream().filter(documento -> documento instanceof NotaDebito)
                .map(documento -> (NotaDebito) documento)
                .collect(Collectors.toList());
    }

    public List<Factura> getFacturas() {
        return documentos.stream().filter(documento -> documento instanceof Factura)
                .map(documento -> (Factura) documento)
                .collect(Collectors.toList());
    }

    public double deudaProveedor(int cuitProveedor) {
        return 0;
    }

    public List<Factura> getFacturasPorDia(LocalDate dia) {
        return documentos.stream().filter(documento -> documento instanceof Factura)
                //TODO filter by date
                .map(documento -> (Factura) documento)
                .collect(Collectors.toList());
    }

    public List<Documento> consultaDocumentosProveedor() {
        return null;
    }

    public String getNombreProveedor() {
        return proveedor.getNombreProveedor();
    }

}
