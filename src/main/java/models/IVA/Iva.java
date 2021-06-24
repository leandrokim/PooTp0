package main.java.models.IVA;

public class Iva {

    private final TipoIVA tipoIVA;
    private double total;

    public Iva(TipoIVA tipoIVA) {
        this.tipoIVA = tipoIVA;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public TipoIVA getTipoIVA() {
        return tipoIVA;
    }

}
