package main.java.collections;

import main.java.models.Documentos.NotaDebito;
import main.java.models.Documentos.NotaDebito.DTONotaDebito;

public class NotaDebitoCollection extends Collection<NotaDebito, DTONotaDebito> {
    public NotaDebitoCollection() {
    }

    public String nombreArchivo() {
        return NotaDebitoCollection.class.getSimpleName();
    }

    protected Class<NotaDebito> clase() {
        return NotaDebito.class;
    }
}
