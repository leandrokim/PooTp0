package main.java.models;

public abstract class FormaPago {

    protected double importe;

    public abstract double getImporte();

    public abstract DTOFormaPago toDTO();

    public static class DTOFormaPago {
        public double importe;
        public String tipo;
    }

}
