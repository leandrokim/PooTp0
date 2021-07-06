package main.java.models.Productos;

import main.java.controllers.ABMController;
import main.java.models.Proveedor.Proveedor;

import java.util.ArrayList;

public class PrecioProductoPorProveedor {

    private double precio;
    private int cuitProveedor;
    private int producto;

    public PrecioProductoPorProveedor(double precio, int cuitProveedor, int producto) {
        this.precio = precio;
        this.cuitProveedor = cuitProveedor;
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCuitProveedor() {
        return cuitProveedor;
    }

    public void setProveedor(int cuitProveedor) {
        this.cuitProveedor = cuitProveedor;
    }

    public Producto getProducto() {
        ABMController controller = ABMController.getInstancia();
        ArrayList<Rubro> rubros = controller.getRubros();
        for (Rubro rubro : rubros) {
            for (Producto producto : rubro.getProductos()) {
                if (producto.getIdProducto() == this.producto) {
                    return producto;
                }
            }
        }
        return null;
    }

    public int getProductoId() {
        return producto;
    }

    public void setProductoId(int producto) {
        this.producto = producto;
    }

    public double getProductoIva() {
        return getProducto().getTipoDeIva().getValue() * precio / 100;
    }

    public Proveedor.DTOProveedor getProveedor() {
        ABMController controller = ABMController.getInstancia();
        return controller.getProveedor(cuitProveedor);
    }

    public DTOPrecioProductoPorProveedor toDTO() {
        DTOPrecioProductoPorProveedor dto = new DTOPrecioProductoPorProveedor();
        dto.precio = getPrecio();
        dto.producto = getProductoId();
        dto.cuitProveedor = getCuitProveedor();
        return dto;
    }

    public static class DTOPrecioProductoPorProveedor {
        public double precio;
        public int cuitProveedor;
        public int producto;
    }

}
