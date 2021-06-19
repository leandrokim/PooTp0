package main.java.controllers;

import main.java.collections.ImpuestoCollection;
import main.java.collections.OrdenPagoCollection;
import main.java.models.Impuesto;
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

    public List<Impuesto> getImpuestos() {
        ImpuestoCollection collection = new ImpuestoCollection();
        return collection.getDatos();
    }

    public List<OrdenPago> getOrdenesDePago() {
        OrdenPagoCollection collection = new OrdenPagoCollection();
        return collection.getDatos();
    }

    public List<OrdenPago.DTOOrdenPago> ordenesDePagoEmitidas() {
        List<OrdenPago.DTOOrdenPago> res = new ArrayList<>();
        for (OrdenPago ordenPago : getOrdenesDePago()) {
            res.add(ordenPago.toDTO());
        }
        return res;
    }

}
