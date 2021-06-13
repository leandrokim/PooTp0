package main.java.controllers;

import main.java.models.CuentaCorriente;
import main.java.models.Factura;
import main.java.models.PrecioProductoPorProveedor;

import java.time.LocalDate;
import java.util.List;

public class ConsultaGeneralController {

    private static ConsultaGeneralController instancia;

    public static ConsultaGeneralController getInstancia() {
        if (instancia == null) {
            instancia = new ConsultaGeneralController();
        }
        return instancia;
    }

    private ConsultaGeneralController() {
        //TODO generar repo
    }

    public List<CuentaCorriente.CuentaCorrienteDTO> consultaCuentaCorrienteProveedores() {

        return null;
    }

    public List<PrecioProductoPorProveedor> compulsaDePrecios(int idRubro, int idProducto) {
        return null;
    }

    public List<CuentaCorriente> consultaLibroIVACompras() {
        return null;
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
