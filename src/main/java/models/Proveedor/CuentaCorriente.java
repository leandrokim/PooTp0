package main.java.models.Proveedor;

import main.java.collections.OrdenPagoCollection;
import main.java.models.Documentos.*;
import main.java.models.IVA.Iva;
import main.java.models.dto.DTOConsultasDeLibroIVA;
import main.java.models.dto.DTODocumentosPagosYDeudas;
import main.java.models.dto.DTOListadoDeImpuestosConNombreYTotalRetenido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CuentaCorriente {

    private Proveedor proveedor;
    private List<Documento> documentos;
    private List<OrdenPago> ordenesDePago;

    public CuentaCorriente(Proveedor proveedor) {
        this.proveedor = proveedor;
        this.documentos = new ArrayList<>();
        this.ordenesDePago = new ArrayList<>();
    }

    private List<OrdenPago> getOrdenesPago() {
        OrdenPagoCollection collection = new OrdenPagoCollection();
        return collection.getDatos();
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<OrdenPago> getOrdenesDePago() {
        return ordenesDePago;
    }

    public void setOrdenesDePago(List<OrdenPago> ordenesDePago) {
        this.ordenesDePago = ordenesDePago;
    }

    public DTOCuentaCorriente toDTO() {
        return null;
    }

    public static class DTOCuentaCorriente {

    }

    public int getCuitProveedor() {
        return proveedor.getCuitProveedor();
    }

    public List<NotaCredito> getNotasCredito() {
        return documentos.stream().filter(documento -> documento instanceof NotaCredito)
                .map(documento -> (NotaCredito) documento)
                .collect(Collectors.toList());
    }

    public List<NotaDebito> getNotasDebito() {
        return documentos.stream().filter(documento -> documento instanceof NotaDebito)
                .map(documento -> (NotaDebito) documento)
                .collect(Collectors.toList());
    }

    public List<Factura> getFacturas() {
        return documentos.stream().filter(documento -> documento instanceof Factura)
                .map(documento -> (Factura) documento)
                .collect(Collectors.toList());
    }

    public double deudaProveedor(int cuitProveedor) {
        return 0;
    }

    public List<Factura> getFacturasPorDia(LocalDate dia) {
        return documentos.stream().filter(documento -> documento instanceof Factura)
                .filter(documento -> documento.getFecha().isEqual(dia))
                .map(documento -> (Factura) documento)
                .collect(Collectors.toList());
    }

    public DTODocumentosPagosYDeudas consultaDocumentosProveedor() {
        int cuitProveedor = proveedor.getCuitProveedor();
        double totalDeuda = 0d;

        ArrayList<Documento.DTODocumento> documentosRecibidos = new ArrayList<>();
        ArrayList<Documento.DTODocumento> facturasImpagas = new ArrayList<>();
        for (Documento documento : documentos) {
            documentosRecibidos.add(documento.toDTO());
            if (documento instanceof Factura) {
                Factura factura = (Factura) documento;
                if (!factura.isFacturaPaga()) {
                    facturasImpagas.add(factura.toDTO());

                    totalDeuda += factura.getTotal();
                }
            }

            if (documento instanceof NotaDebito) {
                totalDeuda -= documento.getTotal();
            }

            if (documento instanceof NotaCredito) {
                totalDeuda += documento.getTotal();
            }
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

        for (Documento documento : documentos) {
            List<Iva> ivaDocumentos = documento.getDocumentIva();
            dto.add(new DTOConsultasDeLibroIVA(getCuitProveedor(),
                    getNombreProveedor(),
                    documento.getFecha(),
                    documento.getTipoDocumento(),
                    ivaDocumentos,
                    documento.getTotal()));
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
