package com.example.test.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter {
    public static <T> String getJson(T t) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(t);
    }
}
