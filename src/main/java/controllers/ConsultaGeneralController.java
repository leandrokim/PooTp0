package main.java.controllers;

import main.java.collections.CuentaCorrienteCollection;
import main.java.models.Documentos.Factura;
import main.java.models.Documentos.NotaCredito;
import main.java.models.Documentos.NotaDebito;
import main.java.models.Documentos.OrdenPago;
import main.java.models.Productos.PrecioProductoPorProveedor;
import main.java.models.Productos.Rubro;
import main.java.models.Proveedor.CuentaCorriente;
import main.java.models.Proveedor.Proveedor;
import main.java.models.dto.DTOConsultasDeLibroIVA;
import main.java.models.dto.DTODocumentosPagosYDeudas;
import main.java.models.dto.DTOFacturas;
import main.java.models.dto.DTOListadoDeImpuestosConNombreYTotalRetenido;

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

    public ArrayList<CuentaCorriente> getCuentaCorrientes() {
        CuentaCorrienteCollection collection = new CuentaCorrienteCollection();
        return collection.getDatos();
    }

    public List<Rubro> getRubros() {
        ABMController controller = ABMController.getInstancia();
        return controller.getRubros();
    }

    public List<OrdenPago> getOrdenPago() {
        OrdenesYDocumentosController controller = OrdenesYDocumentosController.getInstancia();
        return controller.getOrdenPagoCollection();
    }

    public ArrayList<DTODocumentosPagosYDeudas> consultaCuentaCorrienteProveedores() {
        ArrayList<DTODocumentosPagosYDeudas> dto = new ArrayList<>();
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentaCorrientes();
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            dto.add(cuentaCorriente.consultaDocumentosProveedor());
        }
        return dto;
    }

    public ArrayList<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> compulsaDePrecios(int idRubro, int idProducto) {
        ArrayList<PrecioProductoPorProveedor.DTOPrecioProductoPorProveedor> dto = new ArrayList<>();
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

    public ArrayList<DTOConsultasDeLibroIVA> consultaLibroIVACompras() {
        ArrayList<DTOConsultasDeLibroIVA> dto = new ArrayList<>();
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentaCorrientes();
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {

            List<DTOConsultasDeLibroIVA> listadoIVAPorDocumento = cuentaCorriente.getIvaPorDocumento();
            dto.addAll(listadoIVAPorDocumento);
        }
        return dto;
    }

    public double totalDeudaPorProveedor(int cuitProveedor) {
        double totalDeudaPorProveedor = 0;
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentaCorrientes();
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            if (cuitProveedor == cuentaCorriente.getCuitProveedor()) {
                ArrayList<Factura> facturas = cuentaCorriente.getFacturas();
                for (Factura factura : facturas) {
                    if (!factura.isFacturaPaga()) {
                        totalDeudaPorProveedor += factura.getTotal();
                    }
                }

                ArrayList<NotaDebito> notaDebitos = cuentaCorriente.getNotasDebito();
                for (NotaDebito notaDebito : notaDebitos) {
                    if (notaDebito.isVigente()) {
                        totalDeudaPorProveedor -= notaDebito.getTotal();
                    }
                }

                ArrayList<NotaCredito> notaCreditos = cuentaCorriente.getNotasCredito();
                for (NotaCredito notaCredito : notaCreditos) {
                    if (notaCredito.isVigente()) {
                        totalDeudaPorProveedor += notaCredito.getTotal();
                    }
                }
            }
        }
        return totalDeudaPorProveedor;
    }

    public ArrayList<DTOListadoDeImpuestosConNombreYTotalRetenido> totalDeImpuestosRetenidos() {
        ArrayList<DTOListadoDeImpuestosConNombreYTotalRetenido> dto = new ArrayList<>();
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

    public ArrayList<DTOFacturas> facturasPorDiaOProveedor(LocalDate dia, int cuitProveedor) {
        ArrayList<DTOFacturas> dto = new ArrayList<>();
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
