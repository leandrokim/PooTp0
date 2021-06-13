package main.java.models;

import java.util.List;

public class Proveedor {

    private int cuitProveedor;
    private String nombreProveedor;
    private double topeDeudaEmpresa;
    private boolean responsableIva;
    private CuentaCorriente cuentaCorriente;
    private List<Certificado> certificados;
    private List<Rubro> rubros;
    private String emailProvedor;
    private String telProvedor;
    private String dirProvedor;
    private List<Retencion> retenciones;
    private List<OrdenDeCompra> ordenesDeCompra;

    public Proveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }//lo cambiaria por cuitproveedor



    //region get
    public int getCuitProveedor() {
        return cuitProveedor;
    }
    public String getNombreProveedor() {
        return nombreProveedor;
    }
    public double getTopeDeudaEmpresa() {
        return topeDeudaEmpresa;
    }
    public CuentaCorriente getCuentaCorriente() {
        return cuentaCorriente;
    }
    public List<Certificado> getCertificados() {
        return certificados;
    }
    public List<Rubro> getRubros() {
        return rubros;
    }
    public String getEmailProvedor() {
        return emailProvedor;
    }
    public String getTelProvedor() {
        return telProvedor;
    }
    public List<Retencion> getRetenciones() {
        return retenciones;
    }
    public String getDirProvedor() {
        return dirProvedor;
    }
    public List<OrdenDeCompra> getOrdenesDeCompra() {
        return ordenesDeCompra;
    }
    //endregion

    //region set
    public void setCuitProveedor(int cuitProveedor) {
        this.cuitProveedor = cuitProveedor;
    }
    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
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
    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }
    public void setCertificados(List<Certificado> certificados) {
        this.certificados = certificados;
    }



    public void setRubros(List<Rubro> rubros) {
        this.rubros = rubros;
    }



    public void setEmailProvedor(String emailProvedor) {
        this.emailProvedor = emailProvedor;
    }



    public void setTelProvedor(String telProvedor) {
        this.telProvedor = telProvedor;
    }



    public void setDirProvedor(String dirProvedor) {
        this.dirProvedor = dirProvedor;
    }



    public void setRetenciones(List<Retencion> retenciones) {
        this.retenciones = retenciones;
    }



    public void setOrdenesDeCompra(List<OrdenDeCompra> ordenesDeCompra) {
        this.ordenesDeCompra = ordenesDeCompra;
    }
    //endregion

}
