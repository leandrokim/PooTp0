package main.java.collections;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import main.java.models.Documentos.Documento;
import main.java.models.FormaDePago.FormaPago;

import java.lang.reflect.Type;

public class FormaDePagoJsonSerializerWithInheritance implements JsonSerializer<FormaPago> {

    @Override
    public JsonElement serialize(FormaPago formaPago, Type type, JsonSerializationContext jsonSerializationContext) {
        try {
            Class bodyClassType = Class.forName("main.java.models.FormaDePago." + formaPago.getTipoClase());
            return jsonSerializationContext.serialize(formaPago, bodyClassType);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }

}