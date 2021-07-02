package main.java.models.Productos;

import main.java.exceptions.ProductoNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Rubro {

    private int idRubro;
    private String nombreRubro;
    //Solo guarda productos de este proveedor (xq viene de proveedor el rubro)
    private List<Producto> productos;

    public Rubro(int idRubro, String nombreRubro) {
        this.idRubro = idRubro;
        this.nombreRubro = nombreRubro;
    }

    public int getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(int idRubro) {
        this.idRubro = idRubro;
    }

    public String getNombreRubro() {
        return nombreRubro;
    }

    public void setNombreRubro(String nombreRubro) {
        this.nombreRubro = nombreRubro;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void addProducto(Producto producto) {
        if (productos == null) {
            productos = new ArrayList<>();
        }
        productos.add(producto);
    }

    public List<PrecioProductoPorProveedor> getPrecioProductoPorProveedor(int idProducto) {
        List<Producto> productos = getProductos();
        for (Producto producto : productos) {
            if (idProducto == producto.getIdProducto()) {
                return producto.getPreciosPorProveedor();
            }
        }
        throw new ProductoNoEncontradoException("El producto con ID " + idProducto + " no existe");
    }

    public DTORubro toDTO() {
        DTORubro dto = new DTORubro();
        dto.idRubro = getIdRubro();
        dto.nombreRubro = getNombreRubro();
        return dto;
    }

    public static class DTORubro {
        public Integer idRubro;
        public String nombreRubro;
    }

}
