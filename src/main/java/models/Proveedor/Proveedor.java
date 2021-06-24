package main.java.models.Proveedor;

import main.java.models.Documentos.OrdenCompra;
import main.java.models.Productos.Rubro;

import java.util.ArrayList;
import java.util.List;

public class Proveedor {

    private int cuitProveedor;
    private String nombreProveedor;
    private double topeDeudaEmpresa;
    private boolean responsableIva;
    private List<Certificado> certificados;
    private List<Rubro> rubros;
    private String emailProvedor;
    private String telProvedor;
    private String dirProvedor;
    private List<Retencion> retenciones;
    private List<OrdenCompra> ordenesDeCompra;

    public Proveedor(int cuitProveedor,
                     String nombreProveedor,
                     double topeDeudaEmpresa,
                     boolean responsableIva,
                     List<Certificado> certificados,
                     String emailProvedor,
                     String telProvedor,
                     String dirProvedor) {
        this.cuitProveedor = cuitProveedor;
        this.nombreProveedor = nombreProveedor;
        this.topeDeudaEmpresa = topeDeudaEmpresa;
        this.responsableIva = responsableIva;
        this.certificados = certificados;
        this.rubros = new ArrayList<>();
        this.emailProvedor = emailProvedor;
        this.telProvedor = telProvedor;
        this.dirProvedor = dirProvedor;
        this.retenciones = new ArrayList<>();
        this.ordenesDeCompra = new ArrayList<>();
    }

    public int getCuitProveedor() {
        return cuitProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public double getTopeDeudaEmpresa() {
        return topeDeudaEmpresa;
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

    public List<OrdenCompra> getOrdenesDeCompra() {
        return ordenesDeCompra;
    }

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

    public void setOrdenesDeCompra(List<OrdenCompra> ordenesDeCompra) {
        this.ordenesDeCompra = ordenesDeCompra;
    }

    public DTOProveedor toDTO() {
        DTOProveedor dto = new DTOProveedor();
        dto.cuitProveedor = getCuitProveedor();
        dto.nombreProveedor = getNombreProveedor();
        dto.topeDeudaEmpresa = getTopeDeudaEmpresa();
        dto.responsableIva = isResponsableIva();
        dto.emailProvedor = getEmailProvedor();
        dto.telProvedor = getTelProvedor();
        dto.dirProvedor = getDirProvedor();
        dto.certificados = new ArrayList<>();
        for (Certificado certificado : certificados) {
            dto.certificados.add(certificado.toDTO());
        }
        dto.rubros = new ArrayList<>();
        for (Rubro rubro : rubros) {
            dto.rubros.add(rubro.toDTO());
        }
        dto.retenciones = new ArrayList<>();
        for (Retencion retencion : retenciones) {
            dto.retenciones.add(retencion.toDTO());
        }
        return dto;
    }

    public static class DTOProveedor {
        public int cuitProveedor;
        public String nombreProveedor;
        public double topeDeudaEmpresa;
        public boolean responsableIva;
        public String emailProvedor;
        public String telProvedor;
        public String dirProvedor;
        public List<Certificado.DTOCertificado> certificados;
        public List<Rubro.DTORubro> rubros;
        public List<Retencion.DTORetencion> retenciones;
    }

}
