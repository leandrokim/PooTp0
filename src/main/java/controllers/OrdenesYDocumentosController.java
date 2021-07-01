package main.java.controllers;

import main.java.models.Proveedor.Impuesto;
import main.java.models.Documentos.OrdenPago;

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
