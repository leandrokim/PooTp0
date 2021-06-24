package main.java.collections;

import main.java.models.Productos.Rubro;

public class RubroCollection extends Collection<Rubro> {

    @Override
    public String nombreArchivo() {
        return RubroCollection.class.getSimpleName();
    }

    @Override
    protected Class<Rubro> clase() {
        return Rubro.class;
    }

}
