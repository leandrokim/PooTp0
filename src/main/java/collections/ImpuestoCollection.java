package main.java.collections;

import main.java.models.Proveedor.Impuesto;

public class ImpuestoCollection extends Collection<Impuesto, Impuesto.DTOImpuesto> {

    @Override
    public String nombreArchivo() {
        return ImpuestoCollection.class.getSimpleName();
    }

    @Override
    protected Class<Impuesto> clase() {
        return Impuesto.class;
    }

}
