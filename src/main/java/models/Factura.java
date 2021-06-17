package main.java.models;

import java.time.LocalDate;
import java.util.List;

public class Factura extends Documento {

    private int nFactura;
    private ResponsableIVA responsabilidadIVA;
    private List<OrdenCompra> ordenesDeCompras;
    private boolean facturaPaga;

    public Factura(int nFactura, ResponsableIVA responsabilidadIVA, List<OrdenCompra> ordenesDeCompras) {
        this.nFactura = nFactura;
        this.responsabilidadIVA = responsabilidadIVA;
        this.ordenesDeCompras = ordenesDeCompras;
        facturaPaga = false;
    }

    public int getnFactura() {
        return nFactura;
    }

    public void setnFactura(int nFactura) {
        this.nFactura = nFactura;
    }

    public ResponsableIVA getResponsabilidadIVA() {
        return responsabilidadIVA;
    }

    public void setResponsabilidadIVA(ResponsableIVA responsabilidadIVA) {
        this.responsabilidadIVA = responsabilidadIVA;
    }

    public List<OrdenCompra> getOrdenesDeCompras() {
        return ordenesDeCompras;
    }

    public void setOrdenesDeCompras(List<OrdenCompra> ordenesDeCompras) {
        this.ordenesDeCompras = ordenesDeCompras;
    }

    public boolean isFacturaPaga() {
        return facturaPaga;
    }

    public void setFacturaPaga(boolean facturaPaga) {
        this.facturaPaga = facturaPaga;
    }

    @Override
    public List<Iva> getDocumentIva() {
        return super.getDocumentIva();
    }

    @Override
    public double getTotal() {
        return super.getTotal();
    }

    @Override
    public LocalDate getFecha() {
        return super.getFecha();
    }

    @Override
    public DTODocumento toDTO() {
        DTODocumento documento = new DTODocumento();
        documento.nombreEmpresa = getNombreEmpresa();
        //TODO
        return documento;
    }

}
