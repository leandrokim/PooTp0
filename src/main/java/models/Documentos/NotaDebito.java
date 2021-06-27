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

    public NotaDebito(int nNotaDeDebito) {
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

    public NotaDebito.DTONotaDebito toDTO() {
        NotaDebito.DTONotaDebito dto = new NotaDebito.DTONotaDebito();
        dto.nNotaDebito = this.getnNotaDeDebito();
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
        return NotaDebito.class.getSimpleName();
    }

    public static class DTONotaDebito extends DTODocumento {
        public int nNotaDebito;
        public boolean vigente;

        public DTONotaDebito() {
        }
    }
}