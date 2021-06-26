package main.java.models.Documentos;

import main.java.models.Productos.PrecioProductoPorProveedor;
import main.java.models.Productos.Producto;
import main.java.models.Proveedor.Proveedor;

import java.util.ArrayList;
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

    public DTOOrdenCompra toDTO() {
        DTOOrdenCompra dto = new DTOOrdenCompra();
        dto.nOrdenCompra = getNOrdenCompra();
        dto.productos = new ArrayList<>();
        for (PrecioProductoPorProveedor precioProductoPorProveedor : getProductos()) {
            dto.productos.add(precioProductoPorProveedor.toDTO());
        }
        dto.totalPrecioAcordado = getTotalPrecioAcordado();
        return dto;
    }

    public static class DTOOrdenCompra {
        public int nOrdenCompra;
        public List<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> productos;
        public double totalPrecioAcordado;
    }

}
