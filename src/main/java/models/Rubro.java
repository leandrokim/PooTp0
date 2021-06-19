package main.java.models;

import main.java.exceptions.ProductoNoEncontradoException;

import java.util.List;

public class Rubro {
    private Integer idRubro;
    private String nombreRubro;
    private List<Producto> productos;

    public Rubro(String nombreRubro) {
        this.nombreRubro = nombreRubro;
    }

    public Integer getIdRubro() {
        return idRubro;
    }

    public void setIdRubro(Integer idRubro) {
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
