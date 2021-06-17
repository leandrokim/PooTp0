package main.java.models;

import java.time.LocalDate;
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
        DTODocumento documento = new DTODocumento();
        documento.nombreEmpresa = getNombreEmpresa();
        //TODO
        return documento;
    }

}
