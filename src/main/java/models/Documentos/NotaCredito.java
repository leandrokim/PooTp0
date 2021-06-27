package main.java.models.Documentos;

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

    public NotaCredito(int nNotaDeCredito) {
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

    public NotaCredito.DTONotaCredito toDTO() {
        NotaCredito.DTONotaCredito dto = new NotaCredito.DTONotaCredito();
        dto.nNotaDeCredito = this.getnNotaDeCredito();
        dto.vigente = this.isVigente();
        dto.nombreEmpresa = this.getNombreEmpresa();
        dto.cuitEmpresa = this.getCuitEmpresa();
        dto.fecha = this.getFecha();
        dto.cuitProveedor = this.getCuitProveedor();
        dto.total = this.getTotal();
        dto.productos = new ArrayList();
        Iterator var2 = this.getProductos().iterator();

        while(var2.hasNext()) {
            PrecioProductoPorProveedor producto = (PrecioProductoPorProveedor)var2.next();
            dto.productos.add(producto.toDTO());
        }

        return dto;
    }

    public String getTipoDocumento() {
        return NotaCredito.class.getSimpleName();
    }

    public static class DTONotaCredito extends DTODocumento {
        public int nNotaDeCredito;
        public boolean vigente;

        public DTONotaCredito() {
        }
    }
}
