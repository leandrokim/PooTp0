package main.java.controllers;

import main.java.collections.ProveedorCollection;
import main.java.models.Proveedor.Proveedor;

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

    public Proveedor.DTOProveedor getProveedor(int cuitProveedor) {
        ProveedorCollection collection = new ProveedorCollection();
        List<Proveedor> proveedores = collection.getDatos();
        for (Proveedor proveedor : proveedores) {
            if (cuitProveedor == proveedor.getCuitProveedor()) {
                return proveedor.toDTO();
            }
        }
        return null;
    }

    public ArrayList<Proveedor.DTOProveedor> getProveedores() {
        ArrayList<Proveedor.DTOProveedor> dtoProveedores = new ArrayList<>();
        ProveedorCollection collection = new ProveedorCollection();
        ArrayList<Proveedor> proveedores = collection.getDatos();
        for (Proveedor proveedor : proveedores) {
            dtoProveedores.add(proveedor.toDTO());
        }
        return dtoProveedores;
    }

    public void guardarProveedores(ArrayList<Proveedor.DTOProveedor> datos) {
        ProveedorCollection collection = new ProveedorCollection();
        collection.grabar(datos);
    }

}
