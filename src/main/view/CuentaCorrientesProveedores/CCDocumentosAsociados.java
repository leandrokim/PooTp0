package main.view.CuentaCorrientesProveedores;

import main.java.models.Documentos.Documento;

import java.util.ArrayList;

public class CCDocumentosAsociados extends CCDocumentosRecibidos {

    public CCDocumentosAsociados(ArrayList<Documento.DTODocumento> documentosRecibidos) {
        super(documentosRecibidos);
    }

    @Override
    protected String getTitle() {
        return "Documentos Asociados";
    }


}
