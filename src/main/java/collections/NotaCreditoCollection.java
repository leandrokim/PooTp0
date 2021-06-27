package main.java.collections;

import main.java.models.Documentos.NotaCredito;
import main.java.models.Documentos.NotaCredito.DTONotaCredito;

public class NotaCreditoCollection extends Collection<NotaCredito, DTONotaCredito> {
    public NotaCreditoCollection() {
    }

    public String nombreArchivo() {
        return NotaCreditoCollection.class.getSimpleName();
    }

    protected Class<NotaCredito> clase() {
        return NotaCredito.class;
    }
}
