package main.java.collections;

import com.google.gson.*;
import main.java.models.Documentos.Documento;
import main.java.models.FormaDePago.FormaPago;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public abstract class Collection<T, H> {

    protected ArrayList<T> datos;

    public Collection() {
        datos = leer();
    }

    public ArrayList<T> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<T> datos) {
        this.datos = datos;
        grabar();
    }

    public abstract String nombreArchivo();

    public void grabar() {
        File archivo = new File("./" + nombreArchivo() + ".txt");
        FileWriter fileWriter;
        BufferedWriter bwEscritor;
        String texto;
        Gson g = getGson();
        texto = g.toJson(datos);
        //grabo el String
        try {
            //Este bloque de codigo obligatoriamente debe ir dentro de un try.
            fileWriter = new FileWriter(archivo);
            fileWriter.write(texto);
            bwEscritor = new BufferedWriter(fileWriter);
            bwEscritor.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    public void grabar(ArrayList<H> datosDTO) {
        File archivo = new File("./" + nombreArchivo() + ".txt");
        FileWriter fileWriter;
        BufferedWriter bwEscritor;
        String texto;
        Gson g = getGson();
        texto = g.toJson(datosDTO);
        //grabo el String
        try {
            //Este bloque de codigo obligatoriamente debe ir dentro de un try.
            fileWriter = new FileWriter(archivo);
            fileWriter.write(texto);
            bwEscritor = new BufferedWriter(fileWriter);
            bwEscritor.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private ArrayList<T> leer() {
        ArrayList<T> lista = new ArrayList<>();
        String cadena;
        File archivo = new File("./" + nombreArchivo() + ".txt");
        if (archivo.exists()) {
            FileReader f;
            try {
                f = new FileReader(archivo);
                BufferedReader b = new BufferedReader(f);
                cadena = b.readLine();
                JsonParser parser = new JsonParser();
                JsonArray gsonArr = parser.parse(cadena).getAsJsonArray();
                Gson g = getGson();
                for (JsonElement js : gsonArr) {
                    lista.add(g.fromJson(js, clase()));
                }

                b.close();

                return lista;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    private Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Documento.class, new JsonSerializerWithInheritance());
        builder.registerTypeAdapter(Documento.class, new JsonDeserializerWithInheritance<Documento>());
        builder.registerTypeAdapter(FormaPago.class, new FormaDePagoJsonSerializerWithInheritance());
        builder.registerTypeAdapter(FormaPago.class, new JsonDeserializerWithInheritance<FormaPago>());
        return builder.create();
    }

    protected abstract Class<T> clase();

}
