package main.java.models.Productos;

import main.java.models.IVA.TipoIVA;

import java.util.ArrayList;
import java.util.List;

public class Producto {

    private int idProducto;
    private String nombreProducto;
    private Unidad unidad;
    private List<PrecioProductoPorProveedor> preciosPorProveedor;
    private TipoIVA tipoDeIva;

    public Producto(int idProducto, String nombreProducto, Unidad unidad, List<PrecioProductoPorProveedor> preciosPorProveedor, TipoIVA tipoDeIva) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.unidad = unidad;
        this.preciosPorProveedor = preciosPorProveedor;
        this.tipoDeIva = tipoDeIva;
    }

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

    public void addPreciosPorProveedor(PrecioProductoPorProveedor precioProductoPorProveedor) {
        if (preciosPorProveedor == null) {
            preciosPorProveedor = new ArrayList<>();
        }
        preciosPorProveedor.add(precioProductoPorProveedor);
    }

    public void setTipoDeIva(TipoIVA tipoDeIva) {
        this.tipoDeIva = tipoDeIva;
    }

    public DTOProducto toDTO() {
        DTOProducto dto = new DTOProducto();
        dto.idProducto = getIdProducto();
        dto.nombreProducto = getNombreProducto();
        dto.tipoDeIva = getTipoDeIva();
        dto.unidad = getUnidad();
        return dto;
    }

    public static class DTOProducto {
        public int idProducto;
        public String nombreProducto;
        public Unidad unidad;
        public TipoIVA tipoDeIva;
    }

}
