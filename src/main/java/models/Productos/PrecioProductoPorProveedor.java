package main.java.models.Productos;

import main.java.collections.RubroCollection;
import main.java.controllers.ABMController;
import main.java.models.Proveedor.Proveedor;

import java.util.ArrayList;

public class PrecioProductoPorProveedor {

    private double precio;
    private Proveedor proveedor;
    private int producto;

    public PrecioProductoPorProveedor(double precio, Proveedor proveedor, int producto) {
        this.precio = precio;
        this.proveedor = proveedor;
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Producto getProducto() {
        RubroCollection collection = new RubroCollection();
        ArrayList<Rubro> rubros = collection.getDatos();
        for (Rubro rubro : rubros) {
            for (Producto producto : rubro.getProductos()) {
                if (producto.getIdProducto() == this.producto) {
                    return producto;
                }
            }
        }
        return null;
    }

    public void setProductoId(int producto) {
        this.producto = producto;
    }

    public double getProductoIva() {
        return getProducto().getTipoDeIva().getValue() * precio / 100;
    }

    public DTOPrecioProductoPorProveedor toDTO() {
        DTOPrecioProductoPorProveedor dto = new DTOPrecioProductoPorProveedor();
        dto.precio = getPrecio();
        dto.producto = getProducto().toDTO();
        dto.proveedor = getProveedor().toDTO();
        return dto;
    }

    public static class DTOPrecioProductoPorProveedor {
        public double precio;
        public Producto.DTOProducto producto;
        public Proveedor.DTOProveedor proveedor;
    }

}
