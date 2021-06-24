package main.java.main;

import main.java.collections.ProveedorCollection;
import main.java.models.Proveedor.Proveedor;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        ProveedorCollection collection = new ProveedorCollection();
        ArrayList<Proveedor> proveedores = collection.getDatos();

        proveedores.add(new Proveedor(666,
                "Ricardo Fort",
                10d,
                true,
                new ArrayList<>(),
                "rickiF@elComandante.com",
                "1122223333",
                "Miameeeeee"));

        collection.setDatos(proveedores);
    }

}

