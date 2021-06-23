package main.java.controllers;

import main.java.collections.CuentaCorrienteCollection;
import main.java.collections.OrdenPagoCollection;
import main.java.collections.ProveedorCollection;
import main.java.collections.RubroCollection;
import main.java.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultaGeneralController {

    private static ConsultaGeneralController instancia;

    public static ConsultaGeneralController getInstancia() {
        if (instancia == null) {
            instancia = new ConsultaGeneralController();
        }
        return instancia;
    }

    public List<CuentaCorriente> getCuentaCorrientes() {
        CuentaCorrienteCollection collection = new CuentaCorrienteCollection();
        return collection.getDatos();
    }

    public List<Rubro> getRubros() {
        RubroCollection collection = new RubroCollection();
        return collection.getDatos();
    }

    public List<Proveedor> getProveedores() {
        ProveedorCollection collection = new ProveedorCollection();
        return collection.getDatos();

    }

    public List<OrdenPago> getOrdenPago() {
        OrdenPagoCollection collection = new OrdenPagoCollection();
        return collection.getDatos();
    }

    public List<DTODocumentosPagosYDeudas> consultaCuentaCorrienteProveedores() {
        List<DTODocumentosPagosYDeudas> dto = new ArrayList<>();
        List<CuentaCorriente> cuentaCorrientes = getCuentaCorrientes();
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            dto.add(cuentaCorriente.consultaDocumentosProveedor());
        }
        return dto;
    }

    public List<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> compulsaDePrecios(int idRubro, int idProducto) {
        List<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> dto = new ArrayList<>();
        List<Rubro> rubros = getRubros();
        for (Rubro rubro : rubros) {
            if (idRubro == rubro.getIdRubro()) {
                List<PrecioProductoPorProveedor> precioProductoPorProveedores = rubro.getPrecioProductoPorProveedor(idProducto);
                for (PrecioProductoPorProveedor precioProductoPorProveedor : precioProductoPorProveedores) {
                    dto.add(precioProductoPorProveedor.toDTO());
                }
                break;
            }
        }
        return dto;
    }

    public List<DTOConsultasDeLibroIVA> consultaLibroIVACompras() {
        List<DTOConsultasDeLibroIVA> dto = new ArrayList<>();
        List<CuentaCorriente> cuentaCorrientes = getCuentaCorrientes();
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {

            List<DTOConsultasDeLibroIVA> listadoIVAPorDocumento = cuentaCorriente.getIvaPorDocumento();
            dto.addAll(listadoIVAPorDocumento);
        }
        return dto;
    }

    public double totalDeudaPorProveedor(int cuitProveedor) {
        double totalDeudaPorProveedor = 0;
        List<CuentaCorriente> cuentaCorrientes = getCuentaCorrientes();
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            if (cuitProveedor == cuentaCorriente.getCuitProveedor()) {
                List<OrdenPago> ordenesDePago = getOrdenPago();
                for (OrdenPago ordenPago : ordenesDePago) {
                    totalDeudaPorProveedor += ordenPago.getTotalACancelar();
                }
            }
        }
        return totalDeudaPorProveedor;
    }

    public List<Object> totalDeImpuestosRetsenidos() {
        return null;
    }

    public List<DTOListadoDeImpuestosConNombreYTotalRetenido> totalDeImpuestosRetenidos() {
        List<DTOListadoDeImpuestosConNombreYTotalRetenido> dto = new ArrayList<>();
        List<CuentaCorriente> cuentaCorrientes = getCuentaCorrientes();

        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {

            List<DTOListadoDeImpuestosConNombreYTotalRetenido> retencionesEImpuestos = cuentaCorriente.getImpuestosRetenidos();

            //recorro esa lista para generar la lista unica de retenciones e impuestos
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

    public List<DTOFacturas> facturasPorDiaOProveedor(LocalDate dia, int cuitProveedor) {
        List<DTOFacturas> dto = new ArrayList<>();
        List<CuentaCorriente> cuentaCorrientes = getCuentaCorrientes();
        if (cuitProveedor != 0 && dia != null) {
            //tiene proveedor y fecha
            for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
                int cuitDelProveedor = cuentaCorriente.getCuitProveedor();
                if (cuitDelProveedor == cuitProveedor) {
                    List<Factura> facturas = cuentaCorriente.getFacturasPorDia(dia);
                    dto.add(toDTOFacturas(cuitDelProveedor, dia, facturas));
                }
            }
        } else if (cuitProveedor == 0 && dia != null) {
            //solo fecha
            for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
                dto.add(toDTOFacturas(cuentaCorriente.getCuitProveedor(),
                        dia,
                        cuentaCorriente.getFacturasPorDia(dia)));
            }
        } else {
            //solo proveedor
            for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
                int cuitDelProveedor = cuentaCorriente.getCuitProveedor();
                if (cuitDelProveedor == cuitProveedor) {
                    dto.add(toDTOFacturas(cuitDelProveedor,
                            null,
                            cuentaCorriente.getFacturas()));
                }
            }
        }
        return dto;
    }

    private DTOFacturas toDTOFacturas(int cuitProveedor, LocalDate fecha, List<Factura> facturas) {
        double monto = 0d;
        for (Factura factura : facturas) {
            monto += factura.getTotal();
        }
        return new DTOFacturas(cuitProveedor, fecha, facturas.size(), monto);
    }

}
