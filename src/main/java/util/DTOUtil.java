package main.java.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import main.java.collections.JsonDeserializerWithInheritance;
import main.java.models.Documentos.Documento;
import main.java.models.FormaDePago.FormaPago;

public class DTOUtil {

    public static <T,H> T toClass(H dto, Class<T> clase) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Documento.class, new JsonDeserializerWithInheritance<Documento>());
        builder.registerTypeAdapter(FormaPago.class, new JsonDeserializerWithInheritance<FormaPago>());
        Gson gson = builder.create();

        String jsonString = gson.toJson(dto);
        return gson.fromJson(jsonString, clase);
    }

}
