package main.java.models;

public class Impuesto {

    private int idImpuesto;
    private String nombreImpuesto;
    private double porcentaje;

    public Impuesto(int idImpuesto, String nombreImpuesto, double porcentaje) {
        this.idImpuesto = idImpuesto;
        this.nombreImpuesto = nombreImpuesto;
        this.porcentaje = porcentaje;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getNombreImpuesto() {
        return nombreImpuesto;
    }

    public void setNombreImpuesto(String nombreImpuesto) {
        this.nombreImpuesto = nombreImpuesto;
    }

    public int getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(int idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    public DTOImpuesto toDTO() {
        DTOImpuesto dto = new DTOImpuesto();
        dto.idImpuesto = idImpuesto;
        dto.nombreImpuesto = nombreImpuesto;
        dto.porcentaje = porcentaje;
        return dto;
    }

    public static class DTOImpuesto {
        public int idImpuesto;
        public String nombreImpuesto;
        public double porcentaje;
    }

}
