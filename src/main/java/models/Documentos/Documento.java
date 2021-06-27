package main.java.models.Documentos;

import main.java.models.IVA.Iva;
import main.java.models.Productos.PrecioProductoPorProveedor;
import main.java.models.IVA.TipoIVA;

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

    //Es para parsearlo en persistencia
    protected String type;

    public Documento(String nombreEmpresa, int cuitEmpresa, LocalDate fecha, int cuitProveedor) {
        this.nombreEmpresa = nombreEmpresa;
        this.cuitEmpresa = cuitEmpresa;
        this.fecha = fecha;
        this.cuitProveedor = cuitProveedor;

        type = getClass().getName();
    }

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
        total = 0d;
        for (PrecioProductoPorProveedor producto : productos) {
            total += producto.getPrecio();
        }
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

    public abstract String getTipoDocumento();

    public static class DTODocumento {
        public String nombreEmpresa;
        public int cuitEmpresa;
        public LocalDate fecha;
        public int cuitProveedor;
        public List<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> productos;
        public double total;

        public String type;
    }

}
