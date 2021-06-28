package src.main.view.ABMUsuario;


import src.main.java.models.Usuario.Usuario;
import src.main.view.abm.TableColumn;

import java.util.ArrayList;

public class UsuarioABMTable {

    public UsuarioABMTable(ArrayList<Usuario.DTOUsuario> lista, ArrayList<TableColumn> columnas) {
        super(lista, columnas);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario.DTOUsuario dto = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return dto.nombre;
            case 1:
                return dto.tipo;
            default:
                return "";
        }
    }
}
