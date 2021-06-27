package main.java.models.Documentos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import main.java.models.Documentos.Documento.DTODocumento;
import main.java.models.IVA.Iva;
import main.java.models.Productos.PrecioProductoPorProveedor;

public class NotaDebito extends Documento {
    private int nNotaDeDebito;
    private boolean vigente;

    public NotaDebito(int nNotaDeDebito,
                      String nombreEmpresa,
                      int cuitEmpresa,
                      LocalDate fecha,
                      int cuitProveedor) {
        super(nombreEmpresa,
                cuitEmpresa,
                fecha,
                cuitProveedor);
        this.nNotaDeDebito = nNotaDeDebito;
        this.vigente = true;
    }

    public int getnNotaDeDebito() {
        return this.nNotaDeDebito;
    }

    public void setnNotaDeDebito(int nNotaDeDebito) {
        this.nNotaDeDebito = nNotaDeDebito;
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
    public DTONotaDebito toDTO() {
        DTONotaDebito dto = new DTONotaDebito();
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
        dto.nNotaDeDebito = getnNotaDeDebito();
        dto.vigente = isVigente();

        dto.type = type;
        return dto;
    }

    public static class DTONotaDebito extends DTODocumento {
        public int nNotaDeDebito;
        public boolean vigente;
    }

    @Override
    public String getTipoDocumento() {
        return NotaDebito.class.getSimpleName();
    }

}