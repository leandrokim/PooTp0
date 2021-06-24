package main.java.collections;

import main.java.models.Documentos.OrdenPago;

public class OrdenPagoCollection extends Collection<OrdenPago> {

    @Override
    public String nombreArchivo() {
        return OrdenPagoCollection.class.getSimpleName();
    }

    @Override
    protected Class<OrdenPago> clase() {
        return OrdenPago.class;
    }

}
