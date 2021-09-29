package edu.brown.cs.student.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JSONConverter {

  public User jsonToUser(JsonObject jsonObject) {
    Gson gson = new Gson();
    User user = gson.fromJson(jsonObject, User.class);
    return user;
  }

}
