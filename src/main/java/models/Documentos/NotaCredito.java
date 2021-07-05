package main.java.models.Documentos;

import main.java.models.IVA.Iva;
import main.java.models.Productos.PrecioProductoPorProveedor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotaCredito extends Documento {
    private int nNotaDeCredito;
    private boolean vigente;

    public NotaCredito(int nNotaDeCredito,
                       LocalDate fecha,
                       int cuitProveedor) {
        super(fecha, cuitProveedor);
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

    public ArrayList<Iva> getDocumentIva() {
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
