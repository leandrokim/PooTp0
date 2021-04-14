package main.java.models;

import java.util.List;

public class Producto {

    private String nombreProducto;
    private Unidad unidad;
    private List<PrecioPorProveedor> preciosPorProveedor;

    public Producto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

}
