package main.java.models.dto;

import java.time.LocalDate;

public class DTOFacturas {

    public int cuitProveedor;
    public LocalDate fecha;
    public int cantFacturas;
    public double monto;

    public DTOFacturas(int cuitProveedor, LocalDate fecha, int cantFacturas, double monto) {
        this.cuitProveedor = cuitProveedor;
        this.fecha = fecha;
        this.cantFacturas = cantFacturas;
        this.monto = monto;
    }

}
