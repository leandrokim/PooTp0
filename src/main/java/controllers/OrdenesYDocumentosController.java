package main.java.controllers;

import main.java.models.OrdenPago;
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
        return ordenesDePago;
    }

}
