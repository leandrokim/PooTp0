package main.view.CuentaCorrientesProveedores;

import main.java.models.Documentos.Documento;
import main.view.documentos.Documentos;

import java.util.ArrayList;

public class CCDocumentosAsociados extends Documentos {

    public CCDocumentosAsociados(ArrayList<Documento.DTODocumento> documentosRecibidos) {
        super(documentosRecibidos);
    }

    @Override
    protected String getTitle() {
        return "Documentos Asociados";
    }


}
