package main.java.controllers;

import main.java.models.OrdenPago;

import java.util.ArrayList;
import java.util.List;

public class OrdenesYDocumentosController {

    private static OrdenesYDocumentosController instancia;
    private List<OrdenPago> ordenesDePago;

    public static OrdenesYDocumentosController getInstancia() {
        if (instancia == null) {
            instancia = new OrdenesYDocumentosController();
        }
        return instancia;
    }

    public List<OrdenPago> getOrdenesDePago() {
        ordenesDePago = new ArrayList<>();
        //TODO persistencia
        return ordenesDePago;
    }

    public List<OrdenPago.DTOOrdenPago> ordenesDePagoEmitidas() {
        List<OrdenPago.DTOOrdenPago> res = new ArrayList<>();
        for (OrdenPago ordenPago : getOrdenesDePago()) {
            res.add(ordenPago.toDTO());
        }
        return res;
    }

}
