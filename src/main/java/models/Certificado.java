package main.java.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Certificado {

    private int cuit;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private boolean retencion;
    private Retencion idRetencion;


    //region get
    public Certificado getCertificado() {
        return this.Certificado;
    }
    public LocalDate getFechaInicio(){
        return (fechaInicio);
    }
    public Retencion getIdRetencion() {
        return Retencion;
    }
    public LocalDate getFechaFinal(){
        return (fechaFinal);
    }
    public Retencion getIdRetencion() {
        return idRetencion;
    }
    //endregion

    //region set
    public void setFechaInicio(LocalDate fechaSalida) {
        this.fechaInicio = fechaSalida;
    }
    public void setFechaFinal(LocalDate fechaSalida) {
        this.fechaFinal = fechaSalida;
    }
    public void setCuit(int cuit) {
        this.cuit = cuit;
    }
    public void setRetencion(boolean state) {
        this.retencion = retencion;
    }
    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }
//endregion
}
