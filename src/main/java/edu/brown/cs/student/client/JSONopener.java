package edu.brown.cs.student.client;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;

public class JSONopener {

    User[] user;

    public JSONopener(String json) {

        Gson gson = new Gson();
        this.user = gson.fromJson(json, User[].class);
        System.out.println(Arrays.toString(this.user));

    }
}
