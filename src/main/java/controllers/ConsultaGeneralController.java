package main.java.controllers;

import main.java.collections.CuentaCorrienteCollection;
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
            //TODO diagrama de secuencia cambiar cuentaCorriente.getIvaPorDocumento() y la respuesta
            List<DTOConsultasDeLibroIVA> listadoIVAPorDocumento = cuentaCorriente.getIvaPorDocumento();
            dto.addAll(listadoIVAPorDocumento);
        }
        return dto;
    }

    public double totalDeudaPorProveedor(int cuitProveedor) {
        return 0;
    }

    public List<Object> totalDeImpuestosRetenidos() {
        return null;
    }

    public List<Factura> facturasPorDiaOProveedor(LocalDate dia, int cuitProveedor) {
        return null;
    }

}
