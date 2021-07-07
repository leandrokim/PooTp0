package main.java.controllers;

import main.java.collections.CuentaCorrienteCollection;
import main.java.collections.UsuarioCollection;
import main.java.models.Documentos.*;
import main.java.models.FormaDePago.Cheque;
import main.java.models.FormaDePago.Efectivo;
import main.java.models.FormaDePago.FormaPago;
import main.java.models.Productos.PrecioProductoPorProveedor;
import main.java.models.Productos.Producto;
import main.java.models.Productos.Rubro;
import main.java.models.Proveedor.*;
import main.java.models.Usuario.Usuario;
import main.java.util.DTOUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ABMController {

    private static ABMController instancia;

    public static ABMController getInstancia() {
        if (instancia == null) {
            instancia = new ABMController();
        }
        return instancia;
    }

    private List<Proveedor> getProveedoresCollection() {
        List<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();
        List<Proveedor> proveedores = new ArrayList<>();
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            proveedores.add(cuentaCorriente.getProveedor());
        }
        return proveedores;
    }

    private ArrayList<CuentaCorriente> getCuentasCorrientesCollection() {
        CuentaCorrienteCollection collection = new CuentaCorrienteCollection();
        return collection.getDatos();
    }

    private void saveCuentaCorrientesCollection(ArrayList<CuentaCorriente> list) {
        CuentaCorrienteCollection collection = new CuentaCorrienteCollection();
        collection.setDatos(list);
        collection.grabar();
    }

    public Proveedor.DTOProveedor getProveedor(int cuitProveedor) {
        List<Proveedor> proveedores = getProveedoresCollection();
        for (Proveedor proveedor : proveedores) {
            if (cuitProveedor == proveedor.getCuitProveedor()) {
                return proveedor.toDTO();
            }
        }
        return null;
    }

    public ArrayList<Proveedor.DTOProveedor> getProveedores() {
        ArrayList<Proveedor.DTOProveedor> dtoProveedores = new ArrayList<>();
        List<Proveedor> proveedores = getProveedoresCollection();
        for (Proveedor proveedor : proveedores) {
            dtoProveedores.add(proveedor.toDTO());
        }
        return dtoProveedores;
    }

    public void guardarProveedores(ArrayList<Proveedor.DTOProveedor> datos) {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();

        ArrayList<CuentaCorriente> toSave = new ArrayList<>();
        for (Proveedor.DTOProveedor dtoProveedor : datos) {
            Proveedor proveedor = DTOUtil.toClass(dtoProveedor, Proveedor.class);

            boolean exists = false;
            for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
                if (cuentaCorriente.getCuitProveedor() == proveedor.getCuitProveedor()) {
                    cuentaCorriente.setProveedor(proveedor);
                    toSave.add(cuentaCorriente);
                    exists = true;
                }
            }
            if (!exists) {
                toSave.add(new CuentaCorriente(proveedor));
            }
        }

        saveCuentaCorrientesCollection(toSave);
    }

    public ArrayList<Factura.DTOFactura> getFacturas() {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();
        ArrayList<Factura.DTOFactura> dtoFacturas = new ArrayList<>();

        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            for (Factura factura : cuentaCorriente.getFacturas()) {
                dtoFacturas.add(factura.toDTO());
            }
        }
        return dtoFacturas;
    }

    // En datos viene TODAS las facturas
    public void guardarFacturas(ArrayList<Factura.DTOFactura> datos) {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();

        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            ArrayList<Factura> facturas = new ArrayList<>();

            for (Factura.DTOFactura dtoFactura : datos) {
                if (dtoFactura.cuitProveedor == cuentaCorriente.getCuitProveedor()) {
                    facturas.add(DTOUtil.toClass(dtoFactura, Factura.class));
                }
            }
            cuentaCorriente.setFacturas(facturas);
        }

        saveCuentaCorrientesCollection(cuentaCorrientes);
    }

    public int nuevoNumeroFactura() {
        ArrayList<Factura.DTOFactura> facturas = getFacturas();
        int nroFactura = 0;
        for (Factura.DTOFactura factura : facturas) {
            if (factura.nFactura > nroFactura) {
                nroFactura = factura.nFactura;
            }
        }
        return nroFactura + 1;
    }

    /**
     * Va a mergear todos los rubros de todos los proveedores, y a su vez va a mergear sus respectivos productos
     * y a su vez va a mergear todos los respectivos PPPP
     */

    public ArrayList<Rubro> getRubros() {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();
        ArrayList<Rubro> rubros = new ArrayList<>();

        //Tengo que mergear todos los rubros
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            for (Rubro rubroProveedor : cuentaCorriente.getProveedor().getRubros()) {
                //Si no tengo rubros inicialmente, agregas uno
                if (rubros.isEmpty()) {
                    rubros.add(rubroProveedor);
                } else {
                    boolean existe = false;
                    //Tengo que mergear todos los productos de los rubros
                    for (Rubro rubro : rubros) {
                        if (rubroProveedor.getIdRubro() == rubro.getIdRubro()) {
                            existe = true;
                            //Es el mismo rubro
                            for (Producto productoProveedor : rubroProveedor.getProductos()) {
                                boolean noTieneProducto = true;
                                //Quiero mergear los PPPP de cada producto igual
                                for (Producto producto : rubro.getProductos()) {
                                    if (productoProveedor.getIdProducto() == producto.getIdProducto()) {
                                        List<PrecioProductoPorProveedor> precios = producto.getPreciosPorProveedor();
                                        precios.addAll(productoProveedor.getPreciosPorProveedor());
                                        producto.setPreciosPorProveedor(precios);
                                        noTieneProducto = false;
                                    }
                                }
                                //Si no encontre el producto, agrego
                                if (noTieneProducto) {
                                    rubro.addProducto(productoProveedor);
                                }
                            }
                        }
                    }

                    if (!existe) {
                        rubros.add(rubroProveedor);
                    }
                }
            }
        }

        return rubros;
    }

    public boolean existeRubro(int rubroId) {
        ArrayList<Rubro> rubros = getRubros();
        for (Rubro rubro : rubros) {
            if (rubro.getIdRubro() == rubroId) {
                return true;
            }
        }
        return false;
    }

    public boolean existeProducto(int rubroId, int productoId) {
        ArrayList<Rubro> rubros = getRubros();
        for (Rubro rubro : rubros) {
            if (rubro.getIdRubro() == rubroId) {
                for (Producto producto : rubro.getProductos()) {
                    if (producto.getIdProducto() == productoId) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean existeProveedor(int cuitPrveedor) {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            if (cuentaCorriente.getCuitProveedor() == cuitPrveedor) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<NotaCredito.DTONotaCredito> getNotasCredito() {
        ArrayList<NotaCredito.DTONotaCredito> dtoNotasCredito = new ArrayList<>();
        ArrayList<NotaCredito> notasCredito = getNotasCreditoCollection();

        for (NotaCredito notaCredito : notasCredito) {
            dtoNotasCredito.add(notaCredito.toDTO());
        }

        return dtoNotasCredito;
    }

    private ArrayList<NotaCredito> getNotasCreditoCollection() {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();
        ArrayList<NotaCredito> notaCreditos = new ArrayList<>();
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            notaCreditos.addAll(cuentaCorriente.getNotasCredito());
        }
        return notaCreditos;
    }

    public void guardarNotasCredito(ArrayList<NotaCredito.DTONotaCredito> datos) {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();

        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            ArrayList<NotaCredito> notaCreditos = new ArrayList<>();

            for (NotaCredito.DTONotaCredito dto : datos) {
                if (dto.cuitProveedor == cuentaCorriente.getCuitProveedor()) {
                    notaCreditos.add(DTOUtil.toClass(dto, NotaCredito.class));
                }
            }
            cuentaCorriente.setNotaCreditos(notaCreditos);
        }

        saveCuentaCorrientesCollection(cuentaCorrientes);
    }

    public ArrayList<NotaDebito.DTONotaDebito> getNotasDebito() {
        ArrayList<NotaDebito.DTONotaDebito> dtoNotasDebito = new ArrayList<>();
        ArrayList<NotaDebito> notasDebito = getNotasDebitoCollection();

        for (NotaDebito notaDebito : notasDebito) {
            dtoNotasDebito.add(notaDebito.toDTO());
        }

        return dtoNotasDebito;
    }

    private ArrayList<NotaDebito> getNotasDebitoCollection() {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();
        ArrayList<NotaDebito> notaDebitos = new ArrayList<>();
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            notaDebitos.addAll(cuentaCorriente.getNotasDebito());
        }
        return notaDebitos;
    }

    public void guardarNotasDebito(ArrayList<NotaDebito.DTONotaDebito> datos) {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();

        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            ArrayList<NotaDebito> notaDebitos = new ArrayList<>();

            for (NotaDebito.DTONotaDebito dto : datos) {
                if (dto.cuitProveedor == cuentaCorriente.getCuitProveedor()) {
                    notaDebitos.add(DTOUtil.toClass(dto, NotaDebito.class));
                }
            }
            cuentaCorriente.setNotaDebitos(notaDebitos);
        }

        saveCuentaCorrientesCollection(cuentaCorrientes);
    }

    public void guardarOrdenesPago(ArrayList<OrdenPago.DTOOrdenPago> datos) {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();

        //Me fijo por cada cuenta corriente y blanqueo las ordenes de pago por si se borro alguna
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            ArrayList<OrdenPago> ordenPagos = new ArrayList<>();

            //Por cada CC, guardo todas las que son de ese proveedor
            for (OrdenPago.DTOOrdenPago dto : datos) {
                if (dto.cuitProveedor == cuentaCorriente.getCuitProveedor()) {
                    ordenPagos.add(DTOUtil.toClass(dto, OrdenPago.class));
                }
            }
            cuentaCorriente.setOrdenesDePago(ordenPagos);
        }

        saveCuentaCorrientesCollection(cuentaCorrientes);
    }

    public CuentaCorriente getCuentaCorrienteCuit(int cuitProveedor) {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();

        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            if (cuentaCorriente.getCuitProveedor() == cuitProveedor) return cuentaCorriente;
        }

        return null;
    }

    public void saveCuentaCorriente(CuentaCorriente cuentaCorriente) {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();

        boolean existe = false;
        for (int i = 0; i < cuentaCorrientes.size(); i++) {
            CuentaCorriente cc = cuentaCorrientes.get(i);
            if (cc.getCuitProveedor() == cuentaCorriente.getCuitProveedor()) {
                cuentaCorrientes.set(i, cuentaCorriente);
                existe = true;
            }
        }
        if (!existe) {
            cuentaCorrientes.add(cuentaCorriente);
        }

        saveCuentaCorrientesCollection(cuentaCorrientes);
    }

    public void guardarOrdenPago(OrdenPago.DTOOrdenPago dato) {
        CuentaCorriente cuentaCorriente = getCuentaCorrienteCuit(dato.cuitProveedor);

        ArrayList<Documento> asociados = new ArrayList<>();
        ArrayList<Factura> facturas = cuentaCorriente.getFacturas();
        ArrayList<NotaDebito> notaDebitos = cuentaCorriente.getNotasDebito();
        ArrayList<NotaCredito> notaCreditos = cuentaCorriente.getNotasCredito();

        for (int i = 0; i < facturas.size(); i++) {
            Factura factura = facturas.get(i);

            for (Documento.DTODocumento documento : dato.documentosAsociados) {
                if (documento instanceof Factura.DTOFactura) {
                    Factura.DTOFactura dto = (Factura.DTOFactura) documento;
                    if (dto.nFactura == factura.getnFactura()) {
                        factura.setFacturaPaga(true);
                        asociados.add(factura);
                        facturas.set(i, factura);
                    }
                }
            }
        }

        for (int i = 0; i < notaDebitos.size(); i++) {
            NotaDebito notaDebito = notaDebitos.get(i);

            for (Documento.DTODocumento documento : dato.documentosAsociados) {
                if (documento instanceof NotaDebito.DTONotaDebito) {
                    NotaDebito.DTONotaDebito dto = (NotaDebito.DTONotaDebito) documento;
                    if (dto.nNotaDeDebito == notaDebito.getnNotaDeDebito()) {
                        notaDebito.setVigente(false);
                        asociados.add(notaDebito);
                        notaDebitos.set(i, notaDebito);
                    }
                }
            }
        }

        for (int i = 0; i < notaCreditos.size(); i++) {
            NotaCredito notaCredito = notaCreditos.get(i);

            for (Documento.DTODocumento documento : dato.documentosAsociados) {
                if (documento instanceof NotaCredito.DTONotaCredito) {
                    NotaCredito.DTONotaCredito dto = (NotaCredito.DTONotaCredito) documento;
                    if (dto.nNotaDeCredito == notaCredito.getnNotaDeCredito()) {
                        notaCredito.setVigente(false);
                        asociados.add(notaCredito);
                        notaCreditos.set(i, notaCredito);
                    }
                }
            }
        }

        ArrayList<FormaPago> formasDePagos = new ArrayList<>();
        for (FormaPago.DTOFormaPago dtoFormaPago : dato.formasDePago) {
            if (dtoFormaPago instanceof Cheque.DTOCheque) {
                formasDePagos.add(DTOUtil.toClass(dtoFormaPago, Cheque.class));
            } else {
                formasDePagos.add(DTOUtil.toClass(dtoFormaPago, Efectivo.class));
            }
        }

        ArrayList<Retencion> retenciones = new ArrayList<>();
        OrdenPago ordenPago = new OrdenPago(dato.nroOrdenPago,
                dato.cuitProveedor,
                asociados,
                new ArrayList<>(),
                formasDePagos,
                LocalDate.now());

        boolean existeCertificado = false;
        for (Certificado certificado : cuentaCorriente.getProveedor().getCertificados()) {
            int idImpuesto = Impuesto.getImpuestoPorMonto(ordenPago.getTotalACancelar());
            if (certificado.getImpuesto().getIdImpuesto() == idImpuesto) {
                LocalDate hoy = LocalDate.now();
                if (hoy.isBefore(certificado.getFechaInicio()) || hoy.isAfter(certificado.getFechaFinal())) {
                    Impuesto impuesto = new Impuesto(idImpuesto);
                    retenciones.add(new Retencion(nuevoNumeroRetencion(),
                            impuesto,
                            impuesto.calcular(ordenPago.getTotalACancelar())));
                }
                existeCertificado = true;
            }
        }
        if (cuentaCorriente.getProveedor().getCertificados() == null ||
                cuentaCorriente.getProveedor().getCertificados().isEmpty() ||
                !existeCertificado) {
            Impuesto impuesto = new Impuesto(Impuesto.getImpuestoPorMonto(ordenPago.getTotalACancelar()));
            retenciones.add(new Retencion(nuevoNumeroRetencion(),
                    impuesto,
                    impuesto.calcular(ordenPago.getTotalACancelar())));
        }

        ordenPago.setRetenciones(retenciones);

        cuentaCorriente.setFacturas(facturas);
        cuentaCorriente.setNotaDebitos(notaDebitos);
        cuentaCorriente.setNotaCreditos(notaCreditos);
        cuentaCorriente.addOrdenPago(ordenPago);
        saveCuentaCorriente(cuentaCorriente);
    }

    private int nuevoNumeroRetencion() {
        OrdenesYDocumentosController controller = OrdenesYDocumentosController.getInstancia();
        ArrayList<OrdenPago.DTOOrdenPago> ordenPagos = controller.getOrdenesDePago();

        ArrayList<Retencion.DTORetencion> retenciones = new ArrayList<>();
        for (OrdenPago.DTOOrdenPago ordenPago : ordenPagos) {
            retenciones.addAll(ordenPago.retenciones);
        }

        int numero = 0;
        for (Retencion.DTORetencion retencion : retenciones) {
            if (retencion.idRetencion > numero) {
                numero = retencion.idRetencion;
            }
        }

        return numero + 1;
    }

    public ArrayList<Usuario.DTOUsuario> getUsuarios() {
        ArrayList<Usuario.DTOUsuario> dtoUsuarios = new ArrayList<>();
        UsuarioCollection collection = new UsuarioCollection();
        ArrayList<Usuario> usuarios = collection.getDatos();
        for (Usuario usuario : usuarios) {
            dtoUsuarios.add(usuario.toDTO());
        }
        return dtoUsuarios;
    }

    public void guardarUsuarios(ArrayList<Usuario.DTOUsuario> datos) {
        UsuarioCollection collection = new UsuarioCollection();
        collection.grabar(datos);
    }

    public ArrayList<Documento.DTODocumento> getDocumentosPorProveedor(int cuitProveedor) {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();
        ArrayList<Documento.DTODocumento> res = new ArrayList<>();

        //Agrega todos los documentos del proveedor seleccionado
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            if (cuentaCorriente.getCuitProveedor() == cuitProveedor) {
                for (Documento documento : cuentaCorriente.getDocumentos()) {
                    res.add(documento.toDTO());
                }
            }
        }

        return res;
    }

    public void borrarOrdenPago(OrdenPago.DTOOrdenPago dto) {
        CuentaCorriente cuentaCorriente = getCuentaCorrienteCuit(dto.cuitProveedor);

        ArrayList<Factura> facturas = cuentaCorriente.getFacturas();
        ArrayList<NotaDebito> notaDebitos = cuentaCorriente.getNotasDebito();
        ArrayList<NotaCredito> notaCreditos = cuentaCorriente.getNotasCredito();

        for (int i = 0; i < facturas.size(); i++) {
            Factura factura = facturas.get(i);

            for (Documento.DTODocumento documento : dto.documentosAsociados) {
                if (documento instanceof Factura.DTOFactura) {
                    Factura.DTOFactura dtoFactura = (Factura.DTOFactura) documento;
                    if (dtoFactura.nFactura == factura.getnFactura()) {
                        factura.setFacturaPaga(false);
                        facturas.set(i, factura);
                    }
                }
            }
        }

        for (int i = 0; i < notaDebitos.size(); i++) {
            NotaDebito notaDebito = notaDebitos.get(i);

            for (Documento.DTODocumento documento : dto.documentosAsociados) {
                if (documento instanceof NotaDebito.DTONotaDebito) {
                    NotaDebito.DTONotaDebito dtoNotaDebito = (NotaDebito.DTONotaDebito) documento;
                    if (dtoNotaDebito.nNotaDeDebito == notaDebito.getnNotaDeDebito()) {
                        notaDebito.setVigente(true);
                        notaDebitos.set(i, notaDebito);
                    }
                }
            }
        }

        for (int i = 0; i < notaCreditos.size(); i++) {
            NotaCredito notaCredito = notaCreditos.get(i);

            for (Documento.DTODocumento documento : dto.documentosAsociados) {
                if (documento instanceof NotaCredito.DTONotaCredito) {
                    NotaCredito.DTONotaCredito dtoNotaCredito = (NotaCredito.DTONotaCredito) documento;
                    if (dtoNotaCredito.nNotaDeCredito == notaCredito.getnNotaDeCredito()) {
                        notaCredito.setVigente(true);
                        notaCreditos.set(i, notaCredito);
                    }
                }
            }
        }

        cuentaCorriente.setFacturas(facturas);
        cuentaCorriente.setNotaDebitos(notaDebitos);
        cuentaCorriente.setNotaCreditos(notaCreditos);
        cuentaCorriente.removeOrdenPago(DTOUtil.toClass(dto, OrdenPago.class));
        saveCuentaCorriente(cuentaCorriente);
    }

    public Producto.DTOProducto getProducto(int productoId) {
        ArrayList<Rubro> rubros = getRubros();
        for (Rubro rubro : rubros) {
            for (Producto producto : rubro.getProductos()) {
                if (producto.getIdProducto() == productoId) {
                    return producto.toDTO();
                }
            }
        }
        return null;
    }

    public void guardarFactura(Factura.DTOFactura dto) {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();

        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            if (cuentaCorriente.getCuitProveedor() == dto.cuitProveedor) {
                for (OrdenCompra ordenCompra : cuentaCorriente.getProveedor().getOrdenesDeCompra()) {
                    for (int i = 0; i < dto.ordenesDeCompras.size(); i++) {
                        OrdenCompra.DTOOrdenCompra dtoOrdenCompra = dto.ordenesDeCompras.get(i);
                        if (ordenCompra.getNOrdenCompra() == dtoOrdenCompra.nOrdenCompra) {
                            dto.ordenesDeCompras.set(i, ordenCompra.toDTO());
                        }
                    }
                }
            }
        }

        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            if (cuentaCorriente.getCuitProveedor() == dto.cuitProveedor) {
                boolean existe = false;

                for (int i = 0; i < cuentaCorriente.getFacturas().size(); i++) {
                    Factura factura = cuentaCorriente.getFacturas().get(i);
                    if (factura.getnFactura() == dto.nFactura) {
                        Factura factura1 = DTOUtil.toClass(dto, Factura.class);
                        factura1.setOrdenesDeCompras(factura1.getOrdenesDeCompras());
                        cuentaCorriente.getFacturas().set(i, factura1);
                        existe = true;
                    }
                }

                if (!existe) {
                    Factura factura1 = DTOUtil.toClass(dto, Factura.class);
                    factura1.setOrdenesDeCompras(factura1.getOrdenesDeCompras());
                    cuentaCorriente.getFacturas().add(factura1);
                }
            }
        }

        saveCuentaCorrientesCollection(cuentaCorrientes);
    }

    public ArrayList<OrdenCompra.DTOOrdenCompra> getOrdenCompraPorProveedor(int cuitProveedor) {
        ArrayList<CuentaCorriente> cuentaCorrientes = getCuentasCorrientesCollection();
        ArrayList<OrdenCompra.DTOOrdenCompra> res = new ArrayList<>();

        //Agrega todos los documentos del proveedor seleccionado
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            if (cuentaCorriente.getCuitProveedor() == cuitProveedor) {
                for (OrdenCompra ordenCompra : cuentaCorriente.getProveedor().getOrdenesDeCompra()) {
                    res.add(ordenCompra.toDTO());
                }
            }
        }

        return res;
    }

}
