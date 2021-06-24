package main.java.collections;

import main.java.models.Proveedor.Proveedor;

public class ProveedorCollection extends Collection<Proveedor> {

    @Override
    public String nombreArchivo() {
        return ProveedorCollection.class.getSimpleName();
    }

    @Override
    protected Class<Proveedor> clase() {
        return Proveedor.class;
    }

}
