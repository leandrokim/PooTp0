package main.java.main;

import main.java.collections.ProveedorCollection;
import main.java.collections.RubroCollection;
import main.java.models.IVA.TipoIVA;
import main.java.models.Productos.PrecioProductoPorProveedor;
import main.java.models.Productos.Producto;
import main.java.models.Productos.Rubro;
import main.java.models.Productos.Unidad;
import main.java.models.Proveedor.Proveedor;

import java.util.ArrayList;

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

    }

}

