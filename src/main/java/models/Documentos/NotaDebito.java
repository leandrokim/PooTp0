package main.java.models.Documentos;

import main.java.models.IVA.Iva;
import main.java.models.Productos.PrecioProductoPorProveedor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotaDebito extends Documento {

    private int nNotaDeDebito;
    private boolean vigente;

    public NotaDebito(int nNotaDeDebito) {
        this.nNotaDeDebito = nNotaDeDebito;
        vigente = true;
    }

    public int getnNotaDeDebito() {
        return nNotaDeDebito;
    }

    public void setnNotaDeDebito(int nNotaDeDebito) {
        this.nNotaDeDebito = nNotaDeDebito;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }

    @Override
    public List<Iva> getDocumentIva() {
        return super.getDocumentIva();
    }

    @Override
    public LocalDate getFecha() {
        return super.getFecha();
    }

    @Override
    public double getTotal() {
        return super.getTotal();
    }

    @Override
    public DTODocumento toDTO() {
        DTODocumento dto = new DTODocumento();
        dto.nombreEmpresa = getNombreEmpresa();
        dto.cuitEmpresa = getCuitEmpresa();
        dto.fecha = getFecha();
        dto.cuitProveedor = getCuitProveedor();
        dto.total = getTotal();
        dto.productos = new ArrayList<>();
        for (PrecioProductoPorProveedor producto : getProductos()) {
            dto.productos.add(producto.toDTO());
        }
        return dto;
    }

    @Override
    public String getTipoDocumento() {
        return NotaDebito.class.getSimpleName();
    }

}
