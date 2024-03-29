package main.java.models.Documentos;

import main.java.models.FormaDePago.FormaPago;
import main.java.models.Proveedor.Retencion;
import main.java.models.dto.DTOListadoDeImpuestosConNombreYTotalRetenido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdenPago {

    private int nroOrdenPago;
    private int cuitProveedor;
    private List<Documento> documentosAsociados;
    private List<Retencion> retenciones;
    private List<FormaPago> formasDePagos;
    private LocalDate fecha;

    public OrdenPago(
            int nroOrdenPago,
            int cuitProveedor,
            List<Documento> documentosAsociados,
            List<Retencion> retenciones,
            List<FormaPago> formasDePagos,
            LocalDate fecha) {
        this.nroOrdenPago = nroOrdenPago;
        this.cuitProveedor = cuitProveedor;
        this.documentosAsociados = documentosAsociados;
        this.formasDePagos = formasDePagos;
        this.fecha = fecha;
        this.retenciones = retenciones;
    }

    private List<Retencion> getRetencionesArchivos() {
        return retenciones;
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

    public List<DTOListadoDeImpuestosConNombreYTotalRetenido> getTotalRetenciones() {
        List<DTOListadoDeImpuestosConNombreYTotalRetenido> dto = new ArrayList<>();
        List<Retencion> retenciones = getRetencionesArchivos();
        for (Retencion retencion : retenciones) {

            double total = retencion.getTotal();
            String nombreImpuesto = retencion.getImpuesto().getNombreImpuesto();
            DTOListadoDeImpuestosConNombreYTotalRetenido busq = verificarImpuesto(dto, nombreImpuesto);
            if (busq != null) {
                busq.totalRetenido = busq.totalRetenido + total;
                dto.replaceAll(p -> p.nombreDelImpuesto.equals(busq.nombreDelImpuesto) ? busq : p);//dto.replaceAll(DTOListadoDeImpuestosConNombreYTotalRetenido ->DTOListadoDeImpuestosConNombreYTotalRetenido.nombreDelImpuesto==busq.nombreDelImpuesto?busq:DTOListadoDeImpuestosConNombreYTotalRetenido);
            } else {
                dto.add(new DTOListadoDeImpuestosConNombreYTotalRetenido(total, nombreImpuesto));
            }
        }

        return dto;
    }

    private DTOListadoDeImpuestosConNombreYTotalRetenido verificarImpuesto(List<DTOListadoDeImpuestosConNombreYTotalRetenido> dto, String nombreImpuesto) {
        return (dto.stream()
                .filter(v -> v.nombreDelImpuesto.equals(nombreImpuesto))
                .findFirst().orElse(null));
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

    public double getTotalRetencion() {
        double totalRetenciones = 0d;

        for (Retencion retencion : getRetenciones()) {
            totalRetenciones += retencion.getTotal();
        }

        return totalRetenciones;
    }

    public int getCuitProveedor() {
        return cuitProveedor;
    }

    public int getNroOrdenPago() {
        return nroOrdenPago;
    }

    public DTOOrdenPago toDTO() {
        DTOOrdenPago dto = new DTOOrdenPago();
        dto.nroOrdenPago = nroOrdenPago;
        dto.cuitProveedor = cuitProveedor;
        dto.fecha = getFecha();
        dto.totalACancelar = getTotalACancelar();
        dto.documentosAsociados = new ArrayList<>();
        if (documentosAsociados != null)
            for (Documento documento : documentosAsociados) {
                dto.documentosAsociados.add(documento.toDTO());
            }
        dto.formasDePago = new ArrayList<>();
        if (formasDePagos != null)
            for (FormaPago formaPago : formasDePagos) {
                dto.formasDePago.add(formaPago.toDTO());
            }
        dto.retenciones = new ArrayList<>();
        if (retenciones != null)
            for (Retencion retencion : retenciones) {
                dto.retenciones.add(retencion.toDTO());
            }
        return dto;
    }

    public static class DTOOrdenPago {
        public int nroOrdenPago;
        public int cuitProveedor;
        public LocalDate fecha;
        public double totalACancelar;
        public ArrayList<Documento.DTODocumento> documentosAsociados;
        public ArrayList<FormaPago.DTOFormaPago> formasDePago;
        public ArrayList<Retencion.DTORetencion> retenciones;
    }

}
