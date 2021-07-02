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
    public DTOEfectivo toDTO() {
        DTOEfectivo dto = new DTOEfectivo();
        dto.importe = importe;
        dto.type = type;
        return dto;
    }

    public static class DTOEfectivo extends FormaPago.DTOFormaPago {

    }

}
