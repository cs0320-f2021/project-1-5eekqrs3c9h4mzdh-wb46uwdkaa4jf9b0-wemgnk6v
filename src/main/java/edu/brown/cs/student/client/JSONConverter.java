package edu.brown.cs.student.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class JSONConverter {

  public static User jsonToUser(JsonObject userJson) {

    GsonBuilder gsonBuilder = new GsonBuilder();

    JsonDeserializer<User> deserializer =  new JsonDeserializer<User>() {
      @Override
      public User deserialize(JsonElement jsonElement, Type type,
                              JsonDeserializationContext jsonDeserializationContext)
          throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Double weight = Double.parseDouble(jsonObject.get("weight").getAsString().replace("lbs", ""));
        String[] heightArray = jsonObject.get("height").getAsString().split("'");
        Double height = Double.parseDouble(heightArray[0]) * 12 +
            Double.parseDouble(heightArray[1].replaceAll("[^0-9]", ""));

        return new User(
            jsonObject.get("user_id").getAsInt(),
            weight,
            jsonObject.get("bust_size").getAsString(),
            height,
            jsonObject.get("age").getAsDouble(),
            jsonObject.get("body_type").getAsString(),
            jsonObject.get("horoscope").getAsString()
        );
      }
    };

    gsonBuilder.registerTypeAdapter(User.class, deserializer);

    Gson customGson = gsonBuilder.create();
    User customUser = customGson.fromJson(userJson, User.class);
    return customUser;

  }

}
