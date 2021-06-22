package main.java.collections;

import main.java.models.Proveedor;
import main.java.models.Rubro;

public class ProveedorCollection extends Collection<Proveedor> {
    @Override
    public String nombreArchivo() {
        return ProveedorCollection.class.getSimpleName();
    }

}
