package main.java.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Documento {

    private String nombreEmpresa;
    private int cuitEmpresa;
    private LocalDate fecha;
    private int cuitProveedor;
    private List<PrecioProductoPorProveedor> productos;
    private double total;

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public int getCuitEmpresa() {
        return cuitEmpresa;
    }

    public void setCuitEmpresa(int cuitEmpresa) {
        this.cuitEmpresa = cuitEmpresa;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCuitProveedor() {
        return cuitProveedor;
    }

    public void setCuitProveedor(int cuitProveedor) {
        this.cuitProveedor = cuitProveedor;
    }

    public List<PrecioProductoPorProveedor> getProductos() {
        return productos;
    }

    public void setProductos(List<PrecioProductoPorProveedor> productos) {
        this.productos = productos;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public List<Iva> getDocumentIva() {
        List<Iva> res = new ArrayList<>();
        for (PrecioProductoPorProveedor precioProductoPorProveedor : productos) {
            TipoIVA tipoIVA = precioProductoPorProveedor.getProducto().getTipoDeIva();

            boolean exist = false;
            for (Iva iva : res) {
                if (iva.getTipoIVA().equals(tipoIVA)) {
                    exist = true;
                    iva.setTotal(iva.getTotal() + precioProductoPorProveedor.getProductoIva());
                    break;
                }
            }
            if (!exist) {
                Iva iva = new Iva(tipoIVA);
                iva.setTotal(precioProductoPorProveedor.getProductoIva());
                res.add(iva);
            }
        }

        return res;
    }

    public abstract DTODocumento toDTO();

    public static class DTODocumento {
        public String nombreEmpresa;
    }

}
