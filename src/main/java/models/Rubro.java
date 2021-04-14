package main.java.models;

import java.util.List;

public class Rubro {

    private String nombreRubro;
    private List<Producto> productos;

    public Rubro(String nombreRubro) {
        this.nombreRubro = nombreRubro;
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

}
