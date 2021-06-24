package main.java.collections;


import main.java.models.Proveedor.Retencion;

public class RetencionCollection extends Collection<Retencion>{

    @Override
    public String nombreArchivo() {
        return RetencionCollection.class.getSimpleName();
    }

    @Override
    protected Class<Retencion> clase() {
        return Retencion.class;
    }

}