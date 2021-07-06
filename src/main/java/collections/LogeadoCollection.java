package main.java.collections;

import main.java.models.Usuario.Usuario;

public class LogeadoCollection extends Collection<Usuario, Usuario.DTOUsuario> {

    @Override
    public String nombreArchivo() {
        return LogeadoCollection.class.getSimpleName();
    }

    @Override
    protected Class<Usuario> clase() {
        return Usuario.class;
    }

}
