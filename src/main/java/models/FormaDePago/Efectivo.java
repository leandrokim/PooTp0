package main.java.models.FormaDePago;

public class Efectivo extends FormaPago {

    public Efectivo(double importe) {
        super();
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
        dto.type = type;
        return dto;
    }

}
