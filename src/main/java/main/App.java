package main.java.main;

import main.java.controllers.ABMController;
import main.java.controllers.ConsultaGeneralController;
import main.java.controllers.OrdenesYDocumentosController;
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
        ConsultaGeneralController consultaGeneralController = ConsultaGeneralController.getInstancia();
        OrdenesYDocumentosController ordenesYDocumentosController = OrdenesYDocumentosController.getInstancia();

        // Agregamos proveedores
        List<Certificado> certificados = new ArrayList<>();
        certificados.add(new Certificado(666,
                LocalDate.of(2021, 6, 27),
                LocalDate.of(2030, 6, 27),
                false,
                new Impuesto(1)));
        ArrayList<Proveedor.DTOProveedor> proveedores = new ArrayList<>();
        Proveedor proveedor = new Proveedor(666,
        "Ricarchi Forchi",
        100000d,
        true,
        certificados,
        "ricky@miameeeee.com",
        "080031431",
        "Miameeee");
        proveedores.add(proveedor.toDTO());
        Proveedor proveedor1 = new Proveedor(777,
                "Batman",
                100000000d,
                true,
                new ArrayList<>(),
                "bruce@wayne.com",
                "0800847349",
                "Baticueva");
        proveedores.add(proveedor1.toDTO());
        abmController.guardarProveedores(proveedores);

        // Creamos Rubros y productos
        Rubro rubro = new Rubro(1, "Golosinas");

        Producto producto = new Producto(
                1,
                "Chocolate Felfort",
                Unidad.UNIDAD,
                new ArrayList<>(),
                TipoIVA.VEINTIUNO
        );

        Producto producto1 = new Producto(
                10,
                "Marroc",
                Unidad.PESO,
                new ArrayList<>(),
                TipoIVA.CINCO
        );

        Producto producto2 = new Producto(
                14,
                "Marroc",
                Unidad.UNIDAD,
                new ArrayList<>(),
                TipoIVA.DIEZ_COMA_CINCO
        );

        Rubro rubro1 = new Rubro(2, "Automovil");
        Rubro rubro2 = new Rubro(3, "Limpieza");
        Rubro rubro3 = new Rubro(3, "Limpieza");

        Producto producto3 = new Producto(
                2,
                "Batimovil",
                Unidad.UNIDAD,
                new ArrayList<>(),
                TipoIVA.VEINTISIETE
        );

        Producto producto4 = new Producto(
                3,
                "Limpieza ciudad Gothica",
                Unidad.HORA,
                new ArrayList<>(),
                TipoIVA.DOS_COMA_CINCO
        );

        Producto producto5 = new Producto(
                3,
                "Limpieza ciudad Gothica",
                Unidad.HORA,
                new ArrayList<>(),
                TipoIVA.DOS_COMA_CINCO
        );

        PrecioProductoPorProveedor PPPP1 = new PrecioProductoPorProveedor(100, 666, 1);
        PrecioProductoPorProveedor PPPP2 = new PrecioProductoPorProveedor(35, 666, 10);
        PrecioProductoPorProveedor PPPP3 = new PrecioProductoPorProveedor(350, 666, 14);

        PrecioProductoPorProveedor PPPP4 = new PrecioProductoPorProveedor(100000000, 777, 2);
        PrecioProductoPorProveedor PPPP5 = new PrecioProductoPorProveedor(1, 777, 3);
        PrecioProductoPorProveedor PPPP6 = new PrecioProductoPorProveedor(1000000, 666, 3);

        producto.addPreciosPorProveedor(PPPP1);
        producto1.addPreciosPorProveedor(PPPP2);
        producto2.addPreciosPorProveedor(PPPP3);
        producto3.addPreciosPorProveedor(PPPP4);
        producto4.addPreciosPorProveedor(PPPP5);
        producto5.addPreciosPorProveedor(PPPP6);

        rubro.addProducto(producto);
        rubro.addProducto(producto1);
        rubro.addProducto(producto2);

        rubro1.addProducto(producto3);
        rubro2.addProducto(producto4);

        rubro3.addProducto(producto5);

        ArrayList<Rubro> rickyRubros = new ArrayList<>();
        rickyRubros.add(rubro);
        rickyRubros.add(rubro3);

        ArrayList<Rubro> batiRubros = new ArrayList<>();
        batiRubros.add(rubro1);
        batiRubros.add(rubro2);


        proveedor.setRubros(rickyRubros);
        proveedor1.setRubros(batiRubros);

        ArrayList<Proveedor.DTOProveedor> proveedores2 = new ArrayList<>();
        proveedores2.add(proveedor.toDTO());
        proveedores2.add(proveedor1.toDTO());

        abmController.guardarProveedores(proveedores2);



//        List<Certificado> certificados = new ArrayList<>();
//        certificados.add(new Certificado(666,
//                LocalDate.of(2021, 6, 27),
//                LocalDate.of(2030, 6, 27),
//                false,
//                new Impuesto(1)));
//
//        Proveedor ricki = new Proveedor(666,
//                "Ricardo Fort",
//                10d,
//                true,
//                certificados,
//                "rickiF@elComandante.com",
//                "1122223333",
//                "Miameeeeee");
//
//        ArrayList<Proveedor.DTOProveedor> aux = new ArrayList<>();
//        aux.add(ricki.toDTO());
//
//        abmController.guardarProveedores(aux);
//
//        Rubro rubro = new Rubro(1, "Golosinas");
//
//        Producto producto = new Producto(
//                1,
//                "Chocolate Felfort",
//                Unidad.UNIDAD,
//                new ArrayList<>(),
//                TipoIVA.VEINTIUNO
//        );
//        List<Producto> productos = new ArrayList<>();
//        productos.add(producto);
//
//        PrecioProductoPorProveedor precioProductoPorProveedor = new PrecioProductoPorProveedor(
//                10d,
//                ricki.getCuitProveedor(),
//                producto.getIdProducto()
//        );
//        List<PrecioProductoPorProveedor> precios = new ArrayList<>();
//        precios.add(precioProductoPorProveedor);
//
//        producto.setPreciosPorProveedor(precios);
//        rubro.setProductos(productos);
//        List<Rubro> rubros = new ArrayList<>();
//        rubros.add(rubro);
//        ricki.setRubros(rubros);
//
//        List<OrdenCompra> ordenCompras = new ArrayList<>();
//        ordenCompras.add(new OrdenCompra(1, precios));
//
//        Factura factura = new Factura(abmController.nuevoNumeroFactura(),
//                ResponsableIVA.MONOTRIBUTO,
//                ordenCompras,
//                LocalDate.of(2021, 6, 27),
//                ricki.getCuitProveedor());
//
//        ricki.setOrdenesDeCompra(ordenCompras);
//
//        factura.setProductos(precios);
//
//        NotaDebito notaDebito = new NotaDebito(11,
//                LocalDate.of(2021, 6, 27),
//                ricki.getCuitProveedor());
//        notaDebito.setProductos(precios);
//
//        ArrayList<Documento> documentos = new ArrayList<>();
//        documentos.add(factura);
//        documentos.add(notaDebito);
//
//        List<FormaPago> formaPagos = new ArrayList<>();
//        formaPagos.add(new Efectivo(10d));
//
//        List<Retencion> retenciones = new ArrayList<>();
//        retenciones.add(new Retencion(1,
//                new Impuesto(1),
//                10));
//
//        OrdenPago ordenPago = new OrdenPago(1,
//                ricki.getCuitProveedor(),
//                documentos,
//                retenciones,
//                formaPagos,
//                LocalDate.of(2021, 6, 27));
//
//        ArrayList<Proveedor.DTOProveedor> proveedores = new ArrayList<>();
//        proveedores.add(ricki.toDTO());
//
//        abmController.guardarProveedores(proveedores);
//
//        ArrayList<OrdenPago.DTOOrdenPago> ordenPagos = new ArrayList<>();
//        ordenPagos.add(ordenPago.toDTO());
//
//        abmController.guardarOrdenesPago(ordenPagos);
//
//        ArrayList<Factura.DTOFactura> facturas = new ArrayList<>();
//        facturas.add(factura.toDTO());
//
//        ArrayList<NotaDebito.DTONotaDebito> notaDebitos = new ArrayList<>();
//        notaDebitos.add(notaDebito.toDTO());
//
//        abmController.guardarFacturas(facturas);
//        abmController.guardarNotasDebito(notaDebitos);

        //TODO agregar orden pago con metodo
    }

}

