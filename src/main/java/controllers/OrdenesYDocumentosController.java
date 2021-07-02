package main.java.controllers;

import main.java.collections.CuentaCorrienteCollection;
import main.java.models.Documentos.OrdenPago;
import main.java.models.Proveedor.CuentaCorriente;

import java.util.ArrayList;

public class OrdenesYDocumentosController {

    private static OrdenesYDocumentosController instancia;

    public static OrdenesYDocumentosController getInstancia() {
        if (instancia == null) {
            instancia = new OrdenesYDocumentosController();
        }
        return instancia;
    }

    public ArrayList<OrdenPago> getOrdenPagoCollection() {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();
        ArrayList<OrdenPago> ordenPagos = new ArrayList<>();

        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            ordenPagos.addAll(cuentaCorriente.getOrdenesDePago());
        }

        return ordenPagos;
    }

    public ArrayList<OrdenPago.DTOOrdenPago> getOrdenesDePago() {
        ArrayList<OrdenPago.DTOOrdenPago> dtoOrdenPago = new ArrayList<>();
        ArrayList<OrdenPago> ordenesDePago = getOrdenPagoCollection();
        for (OrdenPago ordenDePago : ordenesDePago) {
            dtoOrdenPago.add(ordenDePago.toDTO());
        }
        return dtoOrdenPago;
    }

    private ArrayList<CuentaCorriente> getCuentasCorrientesCollection() {
        CuentaCorrienteCollection collection = new CuentaCorrienteCollection();
        return collection.getDatos();
    }

}
