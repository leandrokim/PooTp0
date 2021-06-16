package main.java.models;

import java.util.List;

public class Producto {
    private int idProducto;
    private String nombreProducto;
    private Unidad unidad;
    private List<PrecioProductoPorProveedor> preciosPorProveedor;
    private TipoIVA tipoDeIva;

    public Producto(int idProducto) {
        this.idProducto = idProducto;
    }

    //region get
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public List<PrecioProductoPorProveedor> getPreciosPorProveedor() {
        return preciosPorProveedor;
    }

    public TipoIVA getTipoDeIva() {
        return tipoDeIva;
    }

    //endregion

    //region set

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public void setPreciosPorProveedor(List<PrecioProductoPorProveedor> preciosPorProveedor) {
        this.preciosPorProveedor = preciosPorProveedor;
    }

    public void setTipoDeIva(TipoIVA tipoDeIva) {
        this.tipoDeIva = tipoDeIva;
    }




}
