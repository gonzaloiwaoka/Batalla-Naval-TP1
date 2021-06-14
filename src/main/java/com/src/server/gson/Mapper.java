package com.src.server.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.src.errorManagement.Error;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Mapper {
    private static final Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static <T> T fromJson(InputStream in, Type type) {
        return gson.fromJson(new InputStreamReader(in), type);
    }

    public static <T> T fromJsonListErrors(InputStream in) {
        Type listOfMyClassObject = new TypeToken<ArrayList<Error>>() {}.getType();
        return gson.fromJson(new InputStreamReader(in), listOfMyClassObject);
    }
}
