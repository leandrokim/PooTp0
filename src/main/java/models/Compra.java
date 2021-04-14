package main.java.models;

public class Compra {

    private int numCompra;
    private Factura factura;

    public Compra(Factura factura) {
        this.factura = factura;
    }

    public int getNumCompra() {
        return numCompra;
    }

    public void setNumCompra(int numCompra) {
        this.numCompra = numCompra;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

}
