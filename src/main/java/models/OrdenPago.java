package main.java.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenPago {

    private List<Documento> documentosAsociados;
    private List<Retencion> retenciones;
    private List<FormaPago> formasDePagos;
    private LocalDate fecha;

    public OrdenPago(List<Documento> documentosAsociados, List<Retencion> retenciones, List<FormaPago> formasDePagos, LocalDate fecha) {
        this.documentosAsociados = documentosAsociados;
        this.formasDePagos = formasDePagos;
        this.fecha = fecha;
        this.retenciones = retenciones; //TODO
    }

    public List<Documento> getDocumentosAsociados() {
        return documentosAsociados;
    }

    public void setDocumentosAsociados(List<Documento> documentosAsociados) {
        this.documentosAsociados = documentosAsociados;
    }

    public List<Retencion> getRetenciones() {
        return retenciones;
    }

    public void setRetenciones(List<Retencion> retenciones) {
        this.retenciones = retenciones;
    }

    public List<FormaPago> getFormasDePagos() {
        return formasDePagos;
    }

    public void setFormasDePagos(List<FormaPago> formasDePagos) {
        this.formasDePagos = formasDePagos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotalACancelar() {
        double totalACancelar = 0;

        for (Documento documento : documentosAsociados) {
            if (documento instanceof NotaDebito) {
                totalACancelar -= documento.getTotal();
            } else {
                totalACancelar += documento.getTotal();
            }
        }

        return totalACancelar;
    }

    public DTOOrdenPago toDTO() {
        DTOOrdenPago dto = new DTOOrdenPago();
        dto.fecha = getFecha();
        dto.totalACancelar = getTotalACancelar();
        dto.documentosAsociados = new ArrayList<>();
        for (Documento documento : documentosAsociados) {
            dto.documentosAsociados.add(documento.toDTO());
        }
        dto.formasDePago = new ArrayList<>();
        for (FormaPago formaPago : formasDePagos) {
            dto.formasDePago.add(formaPago.toDTO());
        }
        dto.retenciones = new ArrayList<>();
        for (Retencion retencion : retenciones) {
            dto.retenciones.add(retencion.toDTO());
        }
        return dto;
    }

    public static class DTOOrdenPago {
        public LocalDate fecha;
        public double totalACancelar;
        public List<Documento.DTODocumento> documentosAsociados;
        public List<FormaPago.DTOFormaPago> formasDePago;
        public List<Retencion.DTORetencion> retenciones;
    }

}
