package pro.retor.mapmarkerstest.data.api.errors;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by retor on 20.03.2016.
 */
public class ErrorAdapter implements JsonDeserializer<ServerError> {

    @Override
    public ServerError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int code = 0;
        String mes = "Ooppps...";
        ServerError error = new ServerError(code, mes);
        if (json.isJsonObject()) {
            if (json.getAsJsonObject().has("authLost")) {
                mes = json.getAsJsonObject().get("message").getAsString();
                code = 401;
            } else if (json.getAsJsonObject().get("message") != null) {
//                code = json.getAsJsonObject().get("code").getAsInt();
                mes = json.getAsJsonObject().get("message").getAsString();
            } else if (json.getAsJsonObject().get("non_field_errors") != null) {
                mes = json.getAsJsonObject().get("non_field_errors").getAsString();
                code = 500;
            } else if (json.getAsJsonObject().get("detail") != null) {
                mes = json.getAsJsonObject().get("detail").getAsString();
                code = 400;
            } else if (json.getAsJsonObject().has("success") &&
                    !json.getAsJsonObject().get("success").getAsBoolean() &&
                    json.getAsJsonObject().get("errors").getAsJsonArray() != null) {
                String string = json.getAsJsonObject().get("errors").getAsJsonArray().get(0).getAsJsonPrimitive().getAsString();
                error = new ServerError(string);
            }else
                error = new ServerError(code, mes);
        }
        return error;
    }
}
