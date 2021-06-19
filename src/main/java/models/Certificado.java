package main.java.models;

import java.time.LocalDate;

public class Certificado {

    private int cuit;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private boolean retencion;
    private Impuesto impuesto;

    public Certificado(int cuit, LocalDate fechaInicio, LocalDate fechaFinal, boolean retencion, Impuesto impuesto) {
        this.cuit = cuit;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.retencion = retencion;
        this.impuesto = impuesto;
    }

    public Certificado getCertificado() {
        return this;
    }

    public LocalDate getFechaInicio() {
        return (fechaInicio);
    }

    public LocalDate getFechaFinal() {
        return (fechaFinal);
    }

    public void setFechaInicio(LocalDate fechaSalida) {
        this.fechaInicio = fechaSalida;
    }

    public void setFechaFinal(LocalDate fechaSalida) {
        this.fechaFinal = fechaSalida;
    }

    public void setCuitProveedor(int cuit) {
        this.cuit = cuit;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public boolean isRetencion() {
        return retencion;
    }

    public void setRetencion(boolean retencion) {
        this.retencion = retencion;
    }

    public int getCuitProveedor() {
        return cuit;
    }

    public DTOCertificado toDTO() {
        DTOCertificado dto = new DTOCertificado();
        dto.cuit = getCuitProveedor();
        dto.fechaInicio = getFechaInicio();
        dto.fechaFinal = getFechaFinal();
        dto.retencion = getCertificado().isRetencion();
        dto.impuesto = getImpuesto().toDTO();
        return dto;
    }

    public static class DTOCertificado {
        public int cuit;
        public LocalDate fechaInicio;
        public LocalDate fechaFinal;
        public boolean retencion;
        public Impuesto.DTOImpuesto impuesto;
    }

}
