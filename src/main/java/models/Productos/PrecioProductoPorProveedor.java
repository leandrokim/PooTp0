package main.java.models.Productos;

import main.java.models.Proveedor.Proveedor;

public class PrecioProductoPorProveedor {

    private double precio;
    private Proveedor proveedor;
    private Producto producto;

    public PrecioProductoPorProveedor(double precio, Proveedor proveedor, Producto producto) {
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
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getProductoIva() {
        return producto.getTipoDeIva().getValue() * precio / 100;
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
