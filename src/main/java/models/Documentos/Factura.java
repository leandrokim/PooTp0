package main.java.models.Documentos;

import main.java.models.IVA.Iva;
import main.java.models.Productos.PrecioProductoPorProveedor;
import main.java.models.IVA.ResponsableIVA;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura extends Documento {

    private int nFactura;
    private ResponsableIVA responsabilidadIVA;
    private List<OrdenCompra> ordenesDeCompras;
    private boolean facturaPaga;

    public Factura(int nFactura,
                   ResponsableIVA responsabilidadIVA,
                   List<OrdenCompra> ordenesDeCompras,
                   String nombreEmpresa,
                   int cuitEmpresa,
                   LocalDate fecha,
                   int cuitProveedor) {
        super(nombreEmpresa,
        cuitEmpresa,
        fecha,
        cuitProveedor);
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
    public DTOFactura toDTO() {
        DTOFactura dto = new DTOFactura();
        dto.nombreEmpresa = getNombreEmpresa();
        dto.cuitEmpresa = getCuitEmpresa();
        dto.fecha = getFecha();
        dto.cuitProveedor = getCuitProveedor();
        dto.total = getTotal();
        dto.productos = new ArrayList<>();
        if (getProductos() != null)
            for (PrecioProductoPorProveedor producto : getProductos()) {
                dto.productos.add(producto.toDTO());
            }
        dto.nFactura = getnFactura();
        dto.facturaPaga = isFacturaPaga();
        dto.responsabilidadIVA = getResponsabilidadIVA();
        dto.ordenesDeCompras = new ArrayList<>();
        if (getOrdenesDeCompras() != null)
            for (OrdenCompra ordenCompra : getOrdenesDeCompras()) {
                dto.ordenesDeCompras.add(ordenCompra.toDTO());
            }

        dto.type = type;
        return dto;
    }

    public static class DTOFactura extends DTODocumento {
        public int nFactura;
        public boolean facturaPaga;
        public ResponsableIVA responsabilidadIVA;
        public List<OrdenCompra.DTOOrdenCompra> ordenesDeCompras;
    }

    @Override
    public String getTipoDocumento() {
        return Factura.class.getSimpleName();
    }

}