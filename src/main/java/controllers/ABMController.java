package main.java.controllers;

import main.java.collections.CuentaCorrienteCollection;
import main.java.collections.UsuarioCollection;
import main.java.models.Documentos.Factura;
import main.java.models.Documentos.NotaCredito;
import main.java.models.Documentos.NotaDebito;
import main.java.models.Documentos.OrdenPago;
import main.java.models.Productos.PrecioProductoPorProveedor;
import main.java.models.Productos.Producto;
import main.java.models.Productos.Rubro;
import main.java.models.Proveedor.CuentaCorriente;
import main.java.models.Proveedor.Proveedor;
import main.java.models.Usuario.Usuario;
import main.java.util.DTOUtil;

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

            for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
                if (cuentaCorriente.getCuitProveedor() == proveedor.getCuitProveedor()) {
                    cuentaCorriente.setProveedor(proveedor);
                    toSave.add(cuentaCorriente);
                }
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
                    //Tengo que mergear todos los productos de los rubros
                    for (Rubro rubro : rubros) {
                        if (rubroProveedor.getIdRubro() == rubro.getIdRubro()) {
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

        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            ArrayList<OrdenPago> ordenPagos = new ArrayList<>();

            for (OrdenPago.DTOOrdenPago dto : datos) {
                if (dto.cuitProveedor == cuentaCorriente.getCuitProveedor()) {
                    ordenPagos.add(DTOUtil.toClass(dto, OrdenPago.class));
                }
            }
            cuentaCorriente.setOrdenesDePago(ordenPagos);
        }

        saveCuentaCorrientesCollection(cuentaCorrientes);
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

}
