package main.java.main;

import com.sun.org.apache.xpath.internal.operations.Or;
import main.java.controllers.ABMController;
import main.java.controllers.ConsultaGeneralController;
import main.java.controllers.OrdenesYDocumentosController;
import main.java.models.Documentos.*;
import main.java.models.FormaDePago.Cheque;
import main.java.models.FormaDePago.Efectivo;
import main.java.models.FormaDePago.FormaPago;
import main.java.models.FormaDePago.TipoCheque;
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

        //////////////////// Agregamos proveedores ////////////////////
        List<Certificado> certificados = new ArrayList<>();
        certificados.add(new Certificado(666,
                LocalDate.of(2021, 6, 27),
                LocalDate.of(2030, 6, 27),
                false,
                new Impuesto(1)));
        Proveedor proveedor = new Proveedor(666,
                "Ricarchi Forchi",
                100000d,
                true,
                certificados,
                "ricky@miameeeee.com",
                "080031431",
                "Miameeee");
        Proveedor proveedor1 = new Proveedor(777,
                "Batman",
                100000000d,
                true,
                new ArrayList<>(),
                "bruce@wayne.com",
                "0800847349",
                "Baticueva");


        guardarProveedores(proveedor, proveedor1);

        //////////////////// Creamos Rubros y productos ////////////////////
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

        guardarProveedores(proveedor, proveedor1);

        //////////////////// Ordenes de Compra ////////////////////

        ArrayList<PrecioProductoPorProveedor> compras = new ArrayList<>();
        compras.add(PPPP3);
        proveedor.addOrdenDeCompra(new OrdenCompra(1, compras));

        ArrayList<PrecioProductoPorProveedor> compras1 = new ArrayList<>();
        compras1.add(PPPP4);
        proveedor1.addOrdenDeCompra(new OrdenCompra(2, compras1));

        guardarProveedores(proveedor, proveedor1);

        //////////////////// Facturas ////////////////////

        Factura factura = new Factura(1,
                ResponsableIVA.RESPONSABLE_INSCRIPTO,
                proveedor.getOrdenesDeCompra(),
                LocalDate.now(),
                proveedor.getCuitProveedor());

        Factura factura1 = new Factura(2,
                ResponsableIVA.RESPONSABLE_INSCRIPTO,
                proveedor1.getOrdenesDeCompra(),
                LocalDate.now(),
                proveedor1.getCuitProveedor());

        abmController.guardarFactura(factura.toDTO());
        abmController.guardarFactura(factura1.toDTO());

        //////////////////// Nota Debito ////////////////////

        NotaDebito notaDebito = new NotaDebito(11,
                LocalDate.now(),
                proveedor.getCuitProveedor());

        notaDebito.setProductos(proveedor.getOrdenesDeCompra().get(0).getProductos());

        NotaDebito notaDebito1 = new NotaDebito(120,
                LocalDate.now(),
                proveedor1.getCuitProveedor());

        notaDebito1.setProductos(proveedor1.getOrdenesDeCompra().get(0).getProductos());

        ArrayList<NotaDebito.DTONotaDebito> notaDebitos = new ArrayList<>();
        notaDebitos.add(notaDebito.toDTO());
        notaDebitos.add(notaDebito1.toDTO());

        abmController.guardarNotasDebito(notaDebitos);

        //////////////////// Nota Credito ////////////////////

        NotaCredito notaCredito = new NotaCredito(382,
                LocalDate.now(),
                proveedor.getCuitProveedor());

        notaCredito.setProductos(proveedor.getOrdenesDeCompra().get(0).getProductos());

        NotaCredito notaCredito1 = new NotaCredito(572,
                LocalDate.now(),
                proveedor1.getCuitProveedor());

        notaCredito1.setProductos(proveedor1.getOrdenesDeCompra().get(0).getProductos());

        ArrayList<NotaCredito.DTONotaCredito> notaCreditos = new ArrayList<>();
        notaCreditos.add(notaCredito.toDTO());
        notaCreditos.add(notaCredito1.toDTO());

        abmController.guardarNotasCredito(notaCreditos);

        //////////////////// Orden Pago ////////////////////

        Efectivo efectivo = new Efectivo(1000);
        Efectivo efectivo1 = new Efectivo(200);

        Cheque cheque = new Cheque(100,
                TipoCheque.COMUN,
                LocalDate.now(),
                LocalDate.of(2028, 5, 6),
                "Firma1");

        Cheque cheque1 = new Cheque(300,
                TipoCheque.DE_PAGO_DIFERIDO,
                LocalDate.now(),
                LocalDate.of(2029, 3, 4),
                "Firma2");

        ArrayList<Documento> documentos = new ArrayList<>();
        documentos.add(factura);
        documentos.add(notaCredito);
        documentos.add(notaDebito);

        ArrayList<FormaPago> pagos = new ArrayList<>();
        pagos.add(efectivo);
        pagos.add(cheque);

        OrdenPago ordenPago = new OrdenPago(123,
                proveedor.getCuitProveedor(),
                documentos,
                new ArrayList<>(),
                pagos,
                LocalDate.now());

        abmController.guardarOrdenPago(ordenPago.toDTO());

        ArrayList<Documento> documentos1 = new ArrayList<>();
        documentos1.add(factura1);
        documentos1.add(notaCredito1);
        documentos1.add(notaDebito1);

        ArrayList<FormaPago> pagos1 = new ArrayList<>();
        pagos1.add(efectivo1);
        pagos1.add(cheque1);

        OrdenPago ordenPago1 = new OrdenPago(675,
                proveedor1.getCuitProveedor(),
                documentos1,
                new ArrayList<>(),
                pagos1,
                LocalDate.now());

        abmController.guardarOrdenPago(ordenPago1.toDTO());

    }

    private static void guardarProveedores(Proveedor proveedor, Proveedor proveedor1) {
        ABMController abmController = ABMController.getInstancia();
        ArrayList<Proveedor.DTOProveedor> proveedores = new ArrayList<>();
        proveedores.add(proveedor.toDTO());
        proveedores.add(proveedor1.toDTO());
        abmController.guardarProveedores(proveedores);
    }

}

