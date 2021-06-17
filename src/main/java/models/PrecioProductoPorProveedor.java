package main.java.models;

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

}
