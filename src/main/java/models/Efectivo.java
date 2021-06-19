package main.java.models;

public class Efectivo extends FormaPago {

    public Efectivo(double importe) {
        this.importe = importe;
    }

    @Override
    public double getImporte() {
        return importe;
    }

    @Override
    public DTOFormaPago toDTO() {
        DTOFormaPago dto = new DTOFormaPago();
        dto.importe = importe;
        dto.tipo = Efectivo.class.getSimpleName();
        return dto;
    }

}
