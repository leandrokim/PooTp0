package main.java.controllers;

import main.java.collections.UsuarioCollection;
import main.java.exceptions.UsuarioExistenteException;
import main.java.models.Usuario.TipoUsuario;
import main.java.models.Usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private static UserController instancia;

    public static UserController getInstancia() {
        if (instancia == null) {
            instancia = new UserController();
        }
        return instancia;
    }

    public void altaUsuario(String nombreUsuario, String password, TipoUsuario tipoUsuario) {
        UsuarioCollection collection = new UsuarioCollection();
        ArrayList<Usuario> usuarios = collection.getDatos();
        int idMasAlto = -1;
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario)) {
                throw new UsuarioExistenteException("El usuario con nombre " + nombreUsuario + " ya existe");
            }
            if (idMasAlto < usuario.getId()) {
                idMasAlto = usuario.getId();
            }
        }

        if (tipoUsuario == null) {
            tipoUsuario = TipoUsuario.USUARIO;
        }

        usuarios.add(new Usuario(idMasAlto + 1, nombreUsuario, password, tipoUsuario));

        collection.setDatos(usuarios);
    }

    public boolean login(String nombreUsuario, String password) {
        UsuarioCollection collection = new UsuarioCollection();
        List<Usuario> usuarios = collection.getDatos();

        for (Usuario usuario : usuarios) {
            if (usuario.validar(nombreUsuario, password)) {
                return true;
            }
        }
        return false;
    }

}
