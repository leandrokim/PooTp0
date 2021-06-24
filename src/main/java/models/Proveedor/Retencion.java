package main.java.models.Proveedor;

public class Retencion {

    private int idRetencion;
    private Impuesto impuesto;
    private double total;

    public Retencion(int idRetencion, Impuesto impuesto, double total) {
        this.idRetencion = idRetencion;
        this.impuesto = impuesto;
        this.total = total;
    }

    public int getIdRetencion() {
        return idRetencion;
    }

    public void setIdRetencion(int idRetencion) {
        this.idRetencion = idRetencion;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public DTORetencion toDTO() {
        DTORetencion dto = new DTORetencion();
        dto.idRetencion = idRetencion;
        dto.total = total;
        dto.impuesto = impuesto.toDTO();
        return dto;
    }

    public static class DTORetencion {
        public int idRetencion;
        public Impuesto.DTOImpuesto impuesto;
        public double total;
    }

}
