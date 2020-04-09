package com.selepdf.hackovid;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.selepdf.hackovid.model.LoginResponse;

import java.lang.reflect.Type;

public class LoginResponseStatusDeserializer implements JsonDeserializer<LoginResponse.Status> {
    @Override
    public LoginResponse.Status deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int statusCode = json.getAsInt();

        return LoginResponse.Status.values()[statusCode];
    }
}
