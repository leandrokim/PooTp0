package main.java.models.Documentos;

import main.java.models.IVA.Iva;
import main.java.models.IVA.ResponsableIVA;
import main.java.models.Productos.PrecioProductoPorProveedor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import main.java.models.Documentos.Documento.DTODocumento;
import main.java.models.IVA.Iva;
import main.java.models.Productos.PrecioProductoPorProveedor;

public class NotaCredito extends Documento {
    private int nNotaDeCredito;
    private boolean vigente;

    public NotaCredito(int nNotaDeCredito,
                       String nombreEmpresa,
                       int cuitEmpresa,
                       LocalDate fecha,
                       int cuitProveedor) {
        super(nombreEmpresa,
                cuitEmpresa,
                fecha,
                cuitProveedor);
        this.nNotaDeCredito = nNotaDeCredito;
        this.vigente = true;
    }

    public int getnNotaDeCredito() {
        return this.nNotaDeCredito;
    }

    public void setnNotaDeCredito(int nNotaDeCredito) {
        this.nNotaDeCredito = nNotaDeCredito;
    }

    public boolean isVigente() {
        return this.vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    public List<Iva> getDocumentIva() {
        return super.getDocumentIva();
    }

    public LocalDate getFecha() {
        return super.getFecha();
    }

    public double getTotal() {
        return super.getTotal();
    }

    @Override
    public DTONotaCredito toDTO() {
        DTONotaCredito dto = new DTONotaCredito();
        dto.nombreEmpresa = getNombreEmpresa();
        dto.cuitEmpresa = getCuitEmpresa();
        dto.fecha = getFecha();
        dto.cuitProveedor = getCuitProveedor();
        dto.total = getTotal();
        dto.productos = new ArrayList<>();
        if (getProductos() != null)
            for (PrecioProductoPorProveedor producto : getProductos()) {
                dto.productos.add(producto.toDTO());
            }
        dto.nNotaDeCredito = getnNotaDeCredito();
        dto.vigente = isVigente();

        dto.type = type;
        return dto;
    }

    public static class DTONotaCredito extends DTODocumento {
        public int nNotaDeCredito;
        public boolean vigente;
    }

    @Override
    public String getTipoDocumento() {
        return NotaCredito.class.getSimpleName();
    }

}
