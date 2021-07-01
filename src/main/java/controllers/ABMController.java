package main.java.controllers;

import main.java.collections.CuentaCorrienteCollection;
import main.java.collections.UsuarioCollection;
import main.java.models.Documentos.Factura;
import main.java.models.Documentos.NotaCredito;
import main.java.models.Documentos.NotaDebito;
import main.java.models.Documentos.OrdenPago;
import main.java.models.Productos.Producto;
import main.java.models.Productos.Rubro;
import main.java.models.Proveedor.CuentaCorriente;
import main.java.models.Proveedor.Proveedor;
import main.java.models.Usuario.Usuario;
import main.java.util.DTOUtil;

import java.util.ArrayList;
import java.util.Iterator;
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
        CuentaCorrienteCollection collection = new CuentaCorrienteCollection();
        List<CuentaCorriente> cuentaCorrientes = collection.getDatos();
        List<Proveedor> proveedores = new ArrayList<>();
        for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
            proveedores.add(cuentaCorriente.getProveedor());
        }
        return proveedores;
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
        CuentaCorrienteCollection collection = new CuentaCorrienteCollection();
        ArrayList<CuentaCorriente> cuentaCorrientes = collection.getDatos();

        ArrayList<CuentaCorriente> toSave = new ArrayList<>();
        for (Proveedor.DTOProveedor dtoProveedor : datos) {
            Proveedor proveedor = (Proveedor) DTOUtil.toClass(dtoProveedor, Proveedor.class);

            for (CuentaCorriente cuentaCorriente : cuentaCorrientes) {
                if (cuentaCorriente.getCuitProveedor() == proveedor.getCuitProveedor()) {
                    cuentaCorriente.setProveedor(proveedor);
                    toSave.add(cuentaCorriente);
                }
            }
        }

        collection.setDatos(toSave);
        collection.grabar();
    }

    public ArrayList<Factura.DTOFactura> getFacturas() {
        ArrayList<Factura.DTOFactura> dtoFacturas = new ArrayList<>();
        FacturaCollection collection = new FacturaCollection();
        ArrayList<Factura> facturas = collection.getDatos();
        for (Factura factura : facturas) {
            dtoFacturas.add(factura.toDTO());
        }
        return dtoFacturas;
    }

    public void guardarFacturas(ArrayList<Factura.DTOFactura> datos) {
        FacturaCollection collection = new FacturaCollection();
        collection.grabar(datos);
    }

    public int nuevoNumeroFactura() {
        FacturaCollection collection = new FacturaCollection();
        ArrayList<Factura> facturas = collection.getDatos();
        if (facturas == null || facturas.isEmpty()) {
            return 1;
        }
        return facturas.get(facturas.size() - 1).getnFactura() + 1;
    }

    public boolean existeRubro(int rubroId) {
        RubroCollection collection = new RubroCollection();
        ArrayList<Rubro> rubros = collection.getDatos();
        for (Rubro rubro : rubros) {
            if (rubro.getIdRubro() == rubroId) {
                return true;
            }
        }
        return false;
    }

    public boolean existeProducto(int rubroId, int productoId) {
        RubroCollection collection = new RubroCollection();
        ArrayList<Rubro> rubros = collection.getDatos();
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
        ArrayList<NotaCredito.DTONotaCredito> dtoNotasCredito = new ArrayList();
        NotaCreditoCollection collection = new NotaCreditoCollection();
        ArrayList<NotaCredito> notasCredito = collection.getDatos();
        Iterator var4 = notasCredito.iterator();

        while (var4.hasNext()) {
            NotaCredito notaCredito = (NotaCredito) var4.next();
            dtoNotasCredito.add(notaCredito.toDTO());
        }

        return dtoNotasCredito;
    }

    public void guardarNotasCredito(ArrayList<NotaCredito.DTONotaCredito> datos) {
        NotaCreditoCollection collection = new NotaCreditoCollection();
        collection.grabar(datos);
    }

    public ArrayList<NotaDebito.DTONotaDebito> getNotasDebito() {
        ArrayList<NotaDebito.DTONotaDebito> dtoNotasDebito = new ArrayList();
        NotaDebitoCollection collection = new NotaDebitoCollection();
        ArrayList<NotaDebito> notasDebito = collection.getDatos();
        Iterator var4 = notasDebito.iterator();

        while (var4.hasNext()) {
            NotaDebito notaDebito = (NotaDebito) var4.next();
            dtoNotasDebito.add(notaDebito.toDTO());
        }

        return dtoNotasDebito;
    }

    public void guardarNotasDebito(ArrayList<NotaDebito.DTONotaDebito> datos) {
        NotaDebitoCollection collection = new NotaDebitoCollection();
        collection.grabar(datos);
    }

    public ArrayList<OrdenPago.DTOOrdenPago> getOrdenPago() {
        ArrayList<OrdenPago.DTOOrdenPago> dtoOrdenPago = new ArrayList<>();
        OrdenPagoCollection collection = new OrdenPagoCollection();
        ArrayList<OrdenPago> ordenesDePago = collection.getDatos();
        for (OrdenPago ordenDePago : ordenesDePago) {
            dtoOrdenPago.add(ordenDePago.toDTO());
        }
        return dtoOrdenPago;
    }

    public void guardarOrdenesPago(ArrayList<OrdenPago.DTOOrdenPago> datos) {
        OrdenPagoCollection collection = new OrdenPagoCollection();
        collection.grabar(datos);
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
