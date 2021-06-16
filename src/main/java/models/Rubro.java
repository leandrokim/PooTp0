package main.java.models;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    public int getPrecioProductoPorProveedor(int idProducto){

    }
}
