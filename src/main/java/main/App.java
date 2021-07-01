package main.java.main;

import main.java.collections.CuentaCorrienteCollection;
import main.java.controllers.ABMController;
import main.java.models.Documentos.Documento;
import main.java.models.Documentos.Factura;
import main.java.models.Documentos.NotaDebito;
import main.java.models.Documentos.OrdenPago;
import main.java.models.FormaDePago.Efectivo;
import main.java.models.FormaDePago.FormaPago;
import main.java.models.IVA.ResponsableIVA;
import main.java.models.IVA.TipoIVA;
import main.java.models.Productos.PrecioProductoPorProveedor;
import main.java.models.Productos.Producto;
import main.java.models.Productos.Rubro;
import main.java.models.Productos.Unidad;
import main.java.models.Proveedor.CuentaCorriente;
import main.java.models.Proveedor.Proveedor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        ProveedorCollection proveedorCollection = new ProveedorCollection();
        RubroCollection rubroCollection = new RubroCollection();

        ArrayList<Proveedor> proveedores = proveedorCollection.getDatos();
        ArrayList<Rubro> rubros = rubroCollection.getDatos();

        Proveedor ricki = new Proveedor(666,
                "Ricardo Fort",
                10d,
                true,
                new ArrayList<>(),
                "rickiF@elComandante.com",
                "1122223333",
                "Miameeeeee");

        Rubro rubro = new Rubro(1, "Limpieza");

        Producto producto = new Producto(
                1,
                "Magistral",
                Unidad.UNIDAD,
                new ArrayList<>(),
                TipoIVA.VEINTIUNO
        );

        PrecioProductoPorProveedor precioProductoPorProveedor = new PrecioProductoPorProveedor(
                10d,
                ricki,
                producto.getIdProducto()
        );

        ABMController abmController = ABMController.getInstancia();

        Factura factura = new Factura(abmController.nuevoNumeroFactura(),
                ResponsableIVA.MONOTRIBUTO,
                new ArrayList<>(),
                "asfsaf",
                80085,
                LocalDate.of(2021, 6, 27),
                ricki.getCuitProveedor());

        ArrayList<PrecioProductoPorProveedor> productos = new ArrayList<>();
        productos.add(precioProductoPorProveedor);

        factura.setProductos(productos);

        NotaDebito notaDebito = new NotaDebito(11,
                "asfsaf",
                80085,
                LocalDate.of(2021, 6, 27),
                ricki.getCuitProveedor());
        notaDebito.setProductos(productos);

        ArrayList<Documento> documentos = new ArrayList<>();
        documentos.add(factura);
        documentos.add(notaDebito);

        List<FormaPago> formaPagos = new ArrayList<>();
        formaPagos.add(new Efectivo(10d));

        OrdenPago ordenPago = new OrdenPago(documentos,
                new ArrayList<>(),
                formaPagos,
                LocalDate.of(2021, 6, 27));

        ArrayList<OrdenPago> ordenPagos = new ArrayList<>();
        ordenPagos.add(ordenPago);
        CuentaCorriente cuentaCorriente = new CuentaCorriente(ricki);
        cuentaCorriente.setDocumentos(documentos);
        cuentaCorriente.setOrdenesDePago(ordenPagos);

        producto.addPreciosPorProveedor(precioProductoPorProveedor);
        rubro.addProducto(producto);

        if (!proveedores.contains(ricki)) {
            proveedores.add(ricki);
        }
        proveedorCollection.setDatos(proveedores);

        if (!rubros.contains(rubro)) {
            rubros.add(rubro);
        }
        rubroCollection.setDatos(rubros);

        CuentaCorrienteCollection cuentaCorrienteCollection = new CuentaCorrienteCollection();
        ArrayList<CuentaCorriente> cuentaCorrientes = cuentaCorrienteCollection.getDatos();

        if (!cuentaCorrientes.contains(cuentaCorriente)) {
            cuentaCorrientes.add(cuentaCorriente);
        }

        cuentaCorrienteCollection.setDatos(cuentaCorrientes);
    }

}

