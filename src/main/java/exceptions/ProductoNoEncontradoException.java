package main.java.exceptions;

public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
