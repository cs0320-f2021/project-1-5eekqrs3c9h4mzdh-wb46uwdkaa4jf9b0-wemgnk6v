package edu.brown.cs.student.client;

import com.google.gson.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Class that handles opening JSONs.
 */
public class JSONopener {

  // Field to store the resulting JsonObject array
  private JsonObject[] data;

  // Field to store an array of users (only used for opening local file path)
  private User[] userArray;

  // Field to store a hashmap of user id's -> users (only used for opening local file path)
  private HashMap<Integer, User> userHashMap;

  // Fields to store information about the dataset
  private Boolean isUserData = false;
  private Boolean isReviewData = false;
  private Boolean isRentData = false;

  /**
   * Constructor for a JSONopener.
   * @param json - a string either representing a JSON or a filepath to a .json file
   * @param isFilePath - a boolean indicating if json is a filepath
   */
  public JSONopener(String json, Boolean isFilePath) {

    // Create a Gson instance
    Gson gson = new Gson();

    // Initialize jsonArray variable
    JsonArray jsonArray = new JsonArray();

    // Check if the string is a filepath
    if (isFilePath) {
      try {
        // create a reader
        Reader reader = Files.newBufferedReader(Paths.get(json));

        // convert .json file to a JsonArray
        jsonArray = gson.fromJson(reader, JsonArray.class);

        // close reader
        reader.close();

      } catch (Exception ex) {
        System.out.println("Failed to parse the input");
      }
    } else {
      try {
        // Parse the Json string into a JsonArray
        jsonArray = gson.fromJson(json, JsonArray.class);
      } catch (Exception ignored) { }
    }

    // Check if the array contains user, review, or rent data
    if (jsonArray.size() > 0) {
      if (jsonArray.get(0).getAsJsonObject().has("weight")) {
        this.isUserData = true;
      } else if (jsonArray.get(0).getAsJsonObject().has("review_text")) {
        this.isReviewData = true;
      } else if (jsonArray.get(0).getAsJsonObject().has("fit")) {
        this.isRentData = true;
      }
    }

    // Instantiate a new array depending on the data type
    if (isUserData || isReviewData || isRentData) {
      if (isFilePath && this.isUserData) {
        this.userHashMap = new HashMap<>();
        this.userArray = new User[jsonArray.size()];
      } else {
        this.data = new JsonObject[jsonArray.size()];
      }

      // Loop over the objects in the jsonArray and copy them into another array
      for (int i = 0; i < jsonArray.size(); i++) {
        JsonObject e = jsonArray.get(i).getAsJsonObject();
        if (isFilePath && this.isUserData) {
          // if opening a file path, turn objects into users and store
          User u = JSONConverter.jsonToUser(e);
          this.userArray[i] = u;
          this.userHashMap.put(e.get("user_id").getAsInt(), u);
        } else {
          this.data[i] = e;
        }
      }
    }
  }

  /**
   * Method to retrieve the userArray field.
   * @return - an array of users; only populated if opening a local json file of users
   */
  public User[] getUserArray() {
    return this.userArray;
  }

  /**
   * Method to retrieve the userHashmap.
   * @return - a hashmap of users; only populated if opening a local json file of users
   */
  public HashMap<Integer, User> getUserHashMap() {
    return this.userHashMap;
  }

  /**
   * Method to retrieve the JsonObject array.
   * @return - a copy of the JsonObject array
   */
  public JsonObject[] getData() {
    return data.clone();
  }

  /**
   * Method to retrieve isUserData field.
   * @return - a boolean, the value of the field
   */
  public Boolean getIsUserData() {
    return isUserData;
  }

  /**
   * Method to retrieve isReviewData field.
   * @return - a boolean, the value of the field
   */
  public Boolean getIsReviewData() {
    return isReviewData;
  }

  /**
   * Method to retrieve isRentData field.
   * @return - a boolean, the value of the field
   */
  public Boolean getIsRentData() {
    return isRentData;
  }
}
