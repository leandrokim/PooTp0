package main.java.collections;

import main.java.models.Usuario.Usuario;

public class UsuarioCollection extends Collection<Usuario> {

    @Override
    public String nombreArchivo() {
        return UsuarioCollection.class.getSimpleName();
    }

    @Override
    protected Class<Usuario> clase() {
        return Usuario.class;
    }

}
