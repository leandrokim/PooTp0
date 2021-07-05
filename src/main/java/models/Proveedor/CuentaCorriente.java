package main.java.models.Proveedor;

import main.java.models.Documentos.*;
import main.java.models.IVA.Iva;
import main.java.models.dto.DTOConsultasDeLibroIVA;
import main.java.models.dto.DTODocumentosPagosYDeudas;
import main.java.models.dto.DTOListadoDeImpuestosConNombreYTotalRetenido;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CuentaCorriente {

    private Proveedor proveedor;
    private ArrayList<Factura> facturas;
    private ArrayList<NotaCredito> notaCreditos;
    private ArrayList<NotaDebito> notaDebitos;
    private ArrayList<OrdenPago> ordenesDePago;

    public CuentaCorriente(Proveedor proveedor) {
        this.proveedor = proveedor;
        this.facturas = new ArrayList<>();
        this.notaCreditos = new ArrayList<>();
        this.notaDebitos = new ArrayList<>();
        this.ordenesDePago = new ArrayList<>();
    }

    private List<OrdenPago> getOrdenesPago() {
        return ordenesDePago;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public ArrayList<Documento> getDocumentos() {
        ArrayList<Documento> documentos = new ArrayList<>();
        documentos.addAll(facturas);
        documentos.addAll(notaDebitos);
        documentos.addAll(notaCreditos);
        return documentos;
    }

    public void setDocumentos(ArrayList<Documento> documentos) {
        facturas = new ArrayList<>();
        notaCreditos = new ArrayList<>();
        notaDebitos = new ArrayList<>();

        for (Documento documento : documentos) {
            if (documento.getTipoDocumento().equals(Factura.class.getName())) {
                facturas.add((Factura) documento);
            } else if (documento.getTipoDocumento().equals(NotaDebito.class.getName())) {
                notaDebitos.add((NotaDebito) documento);
            } else {
                notaCreditos.add((NotaCredito) documento);
            }
        }
    }

    public List<OrdenPago> getOrdenesDePago() {
        return ordenesDePago;
    }

    public void setOrdenesDePago(ArrayList<OrdenPago> ordenesDePago) {
        this.ordenesDePago = ordenesDePago;
    }

    public DTOCuentaCorriente toDTO() {
        return null;
    }

    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }

    public void setNotaCreditos(ArrayList<NotaCredito> notaCreditos) {
        this.notaCreditos = notaCreditos;
    }

    public void setNotaDebitos(ArrayList<NotaDebito> notaDebitos) {
        this.notaDebitos = notaDebitos;
    }

    public void addOrdenPago(OrdenPago ordenPago) {
        if (ordenesDePago == null) {
            ordenesDePago = new ArrayList<>();
        }
        ordenesDePago.add(ordenPago);
    }

    public void removeOrdenPago(OrdenPago ordenPago) {
        int index = 0;
        for (int i = 0; i < ordenesDePago.size(); i++) {
            if (ordenesDePago.get(i).getNroOrdenPago() == ordenPago.getNroOrdenPago()) {
                index = i;
            }
        }
        ordenesDePago.remove(index);
    }

    public static class DTOCuentaCorriente {

    }

    public int getCuitProveedor() {
        return proveedor.getCuitProveedor();
    }

    public ArrayList<NotaCredito> getNotasCredito() {
        return notaCreditos;
    }

    public ArrayList<NotaDebito> getNotasDebito() {
        return notaDebitos;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public List<Factura> getFacturasPorDia(LocalDate dia) {
        return facturas.stream()
                .filter(factura -> factura.getFecha().isEqual(dia))
                .collect(Collectors.toList());
    }

    public DTODocumentosPagosYDeudas consultaDocumentosProveedor() {
        int cuitProveedor = proveedor.getCuitProveedor();
        double totalDeuda = 0d;

        ArrayList<Documento.DTODocumento> documentosRecibidos = new ArrayList<>();
        ArrayList<Documento.DTODocumento> facturasImpagas = new ArrayList<>();
        for (Factura factura : facturas) {
            documentosRecibidos.add(factura.toDTO());
            if (!factura.isFacturaPaga()) {
                facturasImpagas.add(factura.toDTO());

                totalDeuda += factura.getTotal();
            }
        }
        for (NotaDebito notaDebito : notaDebitos) {
            documentosRecibidos.add(notaDebito.toDTO());
            totalDeuda -= notaDebito.getTotal();
        }
        for (NotaCredito notaCredito : notaCreditos) {
            documentosRecibidos.add(notaCredito.toDTO());
            totalDeuda += notaCredito.getTotal();
        }

        ArrayList<OrdenPago.DTOOrdenPago> pagosRealizados = new ArrayList<>();
        for (OrdenPago ordenPago : ordenesDePago) {
            pagosRealizados.add(ordenPago.toDTO());
        }

        return new DTODocumentosPagosYDeudas(cuitProveedor,
                facturasImpagas,
                documentosRecibidos,
                pagosRealizados,
                totalDeuda);
    }

    public String getNombreProveedor() {
        return proveedor.getNombreProveedor();
    }

    public List<DTOConsultasDeLibroIVA> getIvaPorDocumento() {
        List<DTOConsultasDeLibroIVA> dto = new ArrayList<>();

        for (Documento documento : getDocumentos()) {
            if (documento.getProductos() != null && !documento.getProductos().isEmpty()){
                List<Iva> ivaDocumentos = documento.getDocumentIva();
                dto.add(new DTOConsultasDeLibroIVA(getCuitProveedor(),
                        getNombreProveedor(),
                        documento.getFecha(),
                        documento.getTipoDocumento(),
                        ivaDocumentos,
                        documento.getTotal()));
            }
        }
        return dto;
    }

    public List<DTOListadoDeImpuestosConNombreYTotalRetenido> getImpuestosRetenidos() {
        List<DTOListadoDeImpuestosConNombreYTotalRetenido> dto = new ArrayList<>();
        List<OrdenPago> ordenesDePago = getOrdenesDePago();
        for (OrdenPago ordenPago : ordenesDePago) {
            List<DTOListadoDeImpuestosConNombreYTotalRetenido> retencionesEImpuestos = ordenPago.getTotalRetenciones();
            for (DTOListadoDeImpuestosConNombreYTotalRetenido lista : retencionesEImpuestos) {

                DTOListadoDeImpuestosConNombreYTotalRetenido busq = verificarImpuesto(dto, lista.nombreDelImpuesto);

                if (busq != null) {
                    busq.totalRetenido = busq.totalRetenido + lista.totalRetenido;
                    dto.replaceAll(p -> p.nombreDelImpuesto == busq.nombreDelImpuesto ? busq : p);
                } else {
                    dto.add(new DTOListadoDeImpuestosConNombreYTotalRetenido(lista.totalRetenido, lista.nombreDelImpuesto));
                }

            }

        }
        return dto;
    }

    private DTOListadoDeImpuestosConNombreYTotalRetenido verificarImpuesto(List<DTOListadoDeImpuestosConNombreYTotalRetenido> dto, String nombreImpuesto) {
        return (dto.stream()
                .filter(v -> v.nombreDelImpuesto.equals(nombreImpuesto))
                .findFirst().orElse(null));
    }

}
