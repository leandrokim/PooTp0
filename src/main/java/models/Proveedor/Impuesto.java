package main.java.models.Proveedor;

public class Impuesto {

    private int idImpuesto;
    private String nombreImpuesto;
    private double porcentaje;
    private double valor;

    public Impuesto(int idImpuesto) {
        this.idImpuesto = idImpuesto;
        nombreImpuesto = "Ganancia " + idImpuesto;
        switch (idImpuesto) {
            case 0:
                porcentaje = 0.05;
                valor = 0;
            case 1:
                porcentaje = 0.09;
                valor = 400;
            case 2:
                porcentaje = 0.12;
                valor = 1120;
            case 3:
                porcentaje = 0.15;
                valor = 2080;
            case 4:
                porcentaje = 0.19;
                valor = 3280;
            case 5:
                porcentaje = 0.23;
                valor = 6320;
            case 6:
                porcentaje = 0.27;
                valor = 10000;
            case 7:
                porcentaje = 0.31;
                valor = 18640;
        }
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double calcular(double monto) {
        return monto * porcentaje + valor;
    }

    public static class DTOImpuesto {
        public int idImpuesto;
        public String nombreImpuesto;
        public double porcentaje;
    }

    public static int getImpuestoPorMonto(double monto) {
        if (monto > 96000) {
            return 7;
        } else if (monto > 64000) {
            return 6;
        } else if (monto > 48000) {
            return 5;
        } else if (monto > 32000) {
            return 4;
        } else if (monto > 24000) {
            return 3;
        } else if (monto > 16000) {
            return 2;
        } else if (monto > 8000) {
            return 1;
        } else {
            return 0;
        }
    }

}
