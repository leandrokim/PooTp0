package main.java.models;

import java.time.LocalDate;
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
        DTODocumento documento = new DTODocumento();
        documento.nombreEmpresa = getNombreEmpresa();
        //TODO
        return documento;
    }

}
