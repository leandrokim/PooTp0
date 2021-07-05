package main.java.models.Documentos;

import main.java.controllers.ABMController;
import main.java.models.IVA.Iva;
import main.java.models.Productos.PrecioProductoPorProveedor;
import main.java.models.IVA.TipoIVA;
import main.java.models.Proveedor.Proveedor;
import main.java.util.DTOUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Documento {

    private LocalDate fecha;
    private int cuitProveedor;
    private List<PrecioProductoPorProveedor> productos;
    private double total;

    //Es para parsearlo en persistencia
    protected String type;

    public Documento(LocalDate fecha, int cuitProveedor) {
        this.fecha = fecha;
        this.cuitProveedor = cuitProveedor;

        type = getClass().getName();
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

    public Proveedor getProveedor() {
        ABMController controller = ABMController.getInstancia();
        return DTOUtil.toClass(controller.getProveedor(cuitProveedor), Proveedor.class);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<Iva> getDocumentIva() {
        ArrayList<Iva> res = new ArrayList<>();
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
        public LocalDate fecha;
        public int cuitProveedor;
        public List<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> productos;
        public double total;

        public String type;
    }

}
