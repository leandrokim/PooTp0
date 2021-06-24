package main.java.collections;

import main.java.models.Proveedor.CuentaCorriente;

public class CuentaCorrienteCollection extends Collection<CuentaCorriente> {

    @Override
    public String nombreArchivo() {
        return CuentaCorrienteCollection.class.getSimpleName();
    }

    @Override
    protected Class<CuentaCorriente> clase() {
        return CuentaCorriente.class;
    }

}
