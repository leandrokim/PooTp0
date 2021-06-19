package main.java.collections;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class Collection<T> {

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
        Gson g = new Gson();
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
                Gson g = new Gson();
                for (JsonElement js : gsonArr) {
                    Type type = new TypeToken<T>() {
                    }.getType();
                    lista.add(g.fromJson(js, type));
                }

                b.close();

                return lista;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

}
