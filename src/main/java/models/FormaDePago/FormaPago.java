package main.java.models.FormaDePago;

public abstract class FormaPago {

    protected double importe;
    protected String type;

    public FormaPago() {
        type = getClass().getName();
    }

    public abstract double getImporte();

    public abstract DTOFormaPago toDTO();

    public static class DTOFormaPago {
        public double importe;
        public String type;
    }

}
