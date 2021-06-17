package main.java.models;

import java.util.List;

public class OrdenCompra {

    private int nOrdenCompra;
    private int nFactura;
    private List<PrecioProductoPorProveedor> productos;
    private double totalPrecioAcordado;

    public OrdenCompra(int nOrdenCompra, int nFactura, List<PrecioProductoPorProveedor> productos) {
        this.nOrdenCompra = nOrdenCompra;
        this.nFactura = nFactura;
        this.productos = productos;
        totalPrecioAcordado = 0;
        for (PrecioProductoPorProveedor precioProductoPorProveedor : productos) {
            totalPrecioAcordado += precioProductoPorProveedor.getPrecio();
        }
    }

    public Integer getNOrdenCompra() {
        return nOrdenCompra;
    }

    public void setNOrdenCompra(Integer nOrdenCompra) {
        this.nOrdenCompra = nOrdenCompra;
    }

    public Integer getNFactura() {
        return nFactura;
    }

    public void setNFactura(Integer nFactura) {
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
