package main.java.controllers;

import main.java.collections.ProveedorCollection;
import main.java.models.Proveedor.Proveedor;

import java.util.List;

public class ABMController {

    private static ABMController instancia;

    public static ABMController getInstancia() {
        if (instancia == null) {
            instancia = new ABMController();
        }
        return instancia;
    }

    public Proveedor getProveedor(int cuitProveedor) {
        ProveedorCollection collection = new ProveedorCollection();
        List<Proveedor> proveedores = collection.getDatos();
        for (Proveedor proveedor : proveedores) {
            if (cuitProveedor == proveedor.getCuitProveedor()) {
                return proveedor;
            }
        }
        return null;
    }


}
