package main.java.collections;

import main.java.models.Documentos.Factura;

public class FacturaCollection extends Collection<Factura, Factura.DTOFactura> {

    @Override
    public String nombreArchivo() {
        return FacturaCollection.class.getSimpleName();
    }

    @Override
    protected Class<Factura> clase() {
        return Factura.class;
    }

}
