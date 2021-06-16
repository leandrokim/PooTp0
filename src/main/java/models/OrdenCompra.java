package main.java.models;

import java.util.List;

public class OrdenDeCompra {
    private int nOrdenCompra;
    private int nFactura;
    private List <PrecioProductoPorProveedor> productos;
    private double totalPrecioAcordado;

    public Integer getnOrdenCompra() {
        return nOrdenCompra;
    }

    public void setnOrdenCompra(Integer nOrdenCompra) {
        this.nOrdenCompra = nOrdenCompra;
    }

    public Integer getnFactura() {
        return nFactura;
    }

    public void setnFactura(Integer nFactura) {
        this.nFactura = nFactura;
    }

    public List<PrecioProductoPorProveedor> getProductos() {
        return productos;
    }

    public void setProductos(List<PrecioProductoPorProveedor> productos) {
        this.productos = productos;
    }

    public Double getTotalPrecioAcordado() {
        return totalPrecioAcordado;
    }

    public void setTotalPrecioAcordado(Double totalPrecioAcordado) {
        this.totalPrecioAcordado = totalPrecioAcordado;
    }
}
