package com.selepdf.buyaround.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.selepdf.buyaround.network.model.Response;

import java.lang.reflect.Type;

import javax.inject.Singleton;

@Singleton
public class ResponseStatusDeserializer implements JsonDeserializer<Response.Status> {
    private Response.Status[] responseStatuses;

    public ResponseStatusDeserializer() {
        this.responseStatuses = Response.Status.values();
    }

    @Override
    public Response.Status deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int statusCode = json.getAsInt();
        return responseStatuses[statusCode];
    }
}
