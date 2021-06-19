package main.java.models;

import java.time.LocalDate;
import java.util.List;

public class DTOConsultasDeLibroIVA {

    public int cuitProveedor;
    public String nombreProveedor;
    public LocalDate fecha;
    public String tipoDocumento;
    public List<Iva> iva;
    public double total;

    public DTOConsultasDeLibroIVA(int cuitProveedor,
                                  String nombreProveedor,
                                  LocalDate fecha,
                                  String tipoDocumento,
                                  List<Iva> iva,
                                  double total) {
        this.cuitProveedor = cuitProveedor;
        this.nombreProveedor = nombreProveedor;
        this.fecha = fecha;
        this.tipoDocumento = tipoDocumento;
        this.iva = iva;
        this.total = total;
    }

}
