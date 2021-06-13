package main.java.models;

public class Efectivo extends FormaPago {

    public Efectivo(double importe) {
        this.importe = importe;
    }

    @Override
    public double getImporte() {
        return importe;
    }

}
