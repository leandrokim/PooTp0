package main.java.models.FormaDePago;

import java.time.LocalDate;

public class Cheque extends FormaPago {

    private TipoCheque tipo;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private String firmante;

    public Cheque(double importe,
                  TipoCheque tipo,
                  LocalDate fechaEmision,
                  LocalDate fechaVencimiento,
                  String firmante) {
        super();
        this.importe = importe;
        this.tipo = tipo;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.firmante = firmante;
    }

    public TipoCheque getTipo() {
        return tipo;
    }

    public void setTipo(TipoCheque tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFirmante() {
        return firmante;
    }

    public void setFirmante(String firmante) {
        this.firmante = firmante;
    }

    @Override
    public double getImporte() {
        return importe;
    }

    @Override
    public DTOFormaPago toDTO() {
        DTOFormaPago dto = new DTOFormaPago();
        dto.importe = importe;
        dto.tipo = Cheque.class.getSimpleName();
        dto.type = type;
        return dto;
    }

}
