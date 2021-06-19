package main.java.collections;

import main.java.models.Rubro;

public class RubroCollection extends Collection<Rubro> {

    @Override
    public String nombreArchivo() {
        return RubroCollection.class.getSimpleName();
    }

}
