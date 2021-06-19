package main.java.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotaCredito extends Documento {

    private int nNotaDeCredito;
    private boolean vigente;

    public NotaCredito(int nNotaDeCredito) {
        this.nNotaDeCredito = nNotaDeCredito;
        vigente = true;
    }

    public int getnNotaDeCredito() {
        return nNotaDeCredito;
    }

    public void setnNotaDeCredito(int nNotaDeCredito) {
        this.nNotaDeCredito = nNotaDeCredito;
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
        for (PrecioProductoPorProveedor producto : getProductos()){
            dto.productos.add(producto.toDTO());
        }
        return dto;
    }

    @Override
    public String getTipoDocumento() {
        return NotaCredito.class.getSimpleName();
    }

}
