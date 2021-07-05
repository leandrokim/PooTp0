package main.java.main;

import main.java.controllers.ABMController;
import main.java.models.Documentos.*;
import main.java.models.FormaDePago.Efectivo;
import main.java.models.FormaDePago.FormaPago;
import main.java.models.IVA.ResponsableIVA;
import main.java.models.IVA.TipoIVA;
import main.java.models.Productos.PrecioProductoPorProveedor;
import main.java.models.Productos.Producto;
import main.java.models.Productos.Rubro;
import main.java.models.Productos.Unidad;
import main.java.models.Proveedor.Certificado;
import main.java.models.Proveedor.Impuesto;
import main.java.models.Proveedor.Proveedor;
import main.java.models.Proveedor.Retencion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        ABMController abmController = ABMController.getInstancia();

        List<Certificado> certificados = new ArrayList<>();
        certificados.add(new Certificado(666,
                LocalDate.of(2021, 6, 27),
                LocalDate.of(2030, 6, 27),
                false,
                new Impuesto(1)));

        Proveedor ricki = new Proveedor(666,
                "Ricardo Fort",
                10d,
                true,
                certificados,
                "rickiF@elComandante.com",
                "1122223333",
                "Miameeeeee");

        Rubro rubro = new Rubro(1, "Golosinas");

        Producto producto = new Producto(
                1,
                "Chocolate Felfort",
                Unidad.UNIDAD,
                new ArrayList<>(),
                TipoIVA.VEINTIUNO
        );
        List<Producto> productos = new ArrayList<>();
        productos.add(producto);

        PrecioProductoPorProveedor precioProductoPorProveedor = new PrecioProductoPorProveedor(
                10d,
                ricki.getCuitProveedor(),
                producto.getIdProducto()
        );
        List<PrecioProductoPorProveedor> precios = new ArrayList<>();
        precios.add(precioProductoPorProveedor);

        producto.setPreciosPorProveedor(precios);
        rubro.setProductos(productos);
        List<Rubro> rubros = new ArrayList<>();
        rubros.add(rubro);
        ricki.setRubros(rubros);

        List<OrdenCompra> ordenCompras = new ArrayList<>();
        ordenCompras.add(new OrdenCompra(1, precios));

        Factura factura = new Factura(abmController.nuevoNumeroFactura(),
                ResponsableIVA.MONOTRIBUTO,
                ordenCompras,
                LocalDate.of(2021, 6, 27),
                ricki.getCuitProveedor());

        factura.setProductos(precios);

        NotaDebito notaDebito = new NotaDebito(11,
                LocalDate.of(2021, 6, 27),
                ricki.getCuitProveedor());
        notaDebito.setProductos(precios);

        ArrayList<Documento> documentos = new ArrayList<>();
        documentos.add(factura);
        documentos.add(notaDebito);

        List<FormaPago> formaPagos = new ArrayList<>();
        formaPagos.add(new Efectivo(10d));

        List<Retencion> retenciones = new ArrayList<>();
        retenciones.add(new Retencion(1,
                new Impuesto(1),
                10));

        OrdenPago ordenPago = new OrdenPago(1,
                ricki.getCuitProveedor(),
                documentos,
                retenciones,
                formaPagos,
                LocalDate.of(2021, 6, 27));

        ArrayList<Proveedor.DTOProveedor> proveedores = new ArrayList<>();
        proveedores.add(ricki.toDTO());

        abmController.guardarProveedores(proveedores);

        ArrayList<OrdenPago.DTOOrdenPago> ordenPagos = new ArrayList<>();
        ordenPagos.add(ordenPago.toDTO());

        abmController.guardarOrdenesPago(ordenPagos);

        ArrayList<Factura.DTOFactura> facturas = new ArrayList<>();
        facturas.add(factura.toDTO());

        ArrayList<NotaDebito.DTONotaDebito> notaDebitos = new ArrayList<>();
        notaDebitos.add(notaDebito.toDTO());

        abmController.guardarFacturas(facturas);
        abmController.guardarNotasDebito(notaDebitos);

    }

}

