package main.java.collections;

import main.java.models.OrdenPago;

public class OrdenPagoCollection extends Collection<OrdenPago> {

    @Override
    public String nombreArchivo() {
        return OrdenPagoCollection.class.getSimpleName();
    }

}
