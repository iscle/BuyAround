package com.selepdf.hackovid.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.selepdf.hackovid.model.ResponseStatus;

import java.lang.reflect.Type;

import javax.inject.Singleton;

@Singleton
public class ResponseStatusDeserializer implements JsonDeserializer<ResponseStatus> {
    private ResponseStatus[] responseStatuses;

    public ResponseStatusDeserializer() {
        this.responseStatuses = ResponseStatus.values();
    }

    @Override
    public ResponseStatus deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int statusCode = json.getAsInt();
        return responseStatuses[statusCode];
    }
}
