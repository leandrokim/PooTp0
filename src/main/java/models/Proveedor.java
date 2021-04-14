package main.java.models;

import java.util.List;

public class Proveedor {

    private int cuitProveedor;
    private String nombreProveedor;
    private double cantDeudaEmpresa;
    private double topeDeudaEmpresa;
    private boolean responsableIva;
    private CuentaCorriente cuentaCorriente;
    private List<Certificado> certificados;
    private List<Rubro> rubros;

    public Proveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public int getCuitProveedor() {
        return cuitProveedor;
    }

    public void setCuitProveedor(int cuitProveedor) {
        this.cuitProveedor = cuitProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public double getCantDeudaEmpresa() {
        return cantDeudaEmpresa;
    }

    public void setCantDeudaEmpresa(double cantDeudaEmpresa) {
        this.cantDeudaEmpresa = cantDeudaEmpresa;
    }

    public double getTopeDeudaEmpresa() {
        return topeDeudaEmpresa;
    }

    public void setTopeDeudaEmpresa(double topeDeudaEmpresa) {
        this.topeDeudaEmpresa = topeDeudaEmpresa;
    }

    public boolean isResponsableIva() {
        return responsableIva;
    }

    public void setResponsableIva(boolean responsableIva) {
        this.responsableIva = responsableIva;
    }

    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public List<Certificado> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<Certificado> certificados) {
        this.certificados = certificados;
    }

    public List<Rubro> getRubros() {
        return rubros;
    }

    public void setRubros(List<Rubro> rubros) {
        this.rubros = rubros;
    }

}
