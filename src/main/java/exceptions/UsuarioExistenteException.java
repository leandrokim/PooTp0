package main.java.exceptions;

public class UsuarioExistenteException extends RuntimeException {

    public UsuarioExistenteException(String mensaje) {
        super(mensaje);
    }

}
