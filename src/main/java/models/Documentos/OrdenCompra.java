package main.java.models.Documentos;

import main.java.models.Productos.PrecioProductoPorProveedor;

import java.util.List;

public class OrdenCompra {

    private int nOrdenCompra;
    private List<PrecioProductoPorProveedor> productos;
    private double totalPrecioAcordado;

    public OrdenCompra(int nOrdenCompra, List<PrecioProductoPorProveedor> productos) {
        this.nOrdenCompra = nOrdenCompra;
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
