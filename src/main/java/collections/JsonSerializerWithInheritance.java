package main.java.collections;

import com.google.gson.*;
import main.java.models.Documentos.Documento;

import java.lang.reflect.Type;

public class JsonSerializerWithInheritance implements JsonSerializer<Documento> {

    @Override
    public JsonElement serialize(Documento documento, Type type, JsonSerializationContext jsonSerializationContext) {
        try {
            Class bodyClassType = Class.forName("main.java.models.Documentos." + documento.getTipoDocumento());
            return jsonSerializationContext.serialize(documento, bodyClassType);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }

}