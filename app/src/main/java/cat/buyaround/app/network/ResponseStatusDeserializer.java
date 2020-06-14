package cat.buyaround.app.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import cat.buyaround.app.network.model.SimpleResponse;

import java.lang.reflect.Type;

import javax.inject.Singleton;

@Singleton
public class ResponseStatusDeserializer implements JsonDeserializer<SimpleResponse.Status> {
    private SimpleResponse.Status[] responseStatuses;

    public ResponseStatusDeserializer() {
        this.responseStatuses = SimpleResponse.Status.values();
    }

    @Override
    public SimpleResponse.Status deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int statusCode = json.getAsInt();
        return responseStatuses[statusCode];
    }
}
