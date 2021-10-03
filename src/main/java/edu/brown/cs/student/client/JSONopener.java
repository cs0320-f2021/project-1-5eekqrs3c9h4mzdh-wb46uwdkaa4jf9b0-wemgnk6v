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

  private User[] userArray;

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

    // Check if the string is as filepath
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
    if (jsonArray.get(0).getAsJsonObject().has("weight")) {
      this.isUserData = true;
    } else if (jsonArray.get(0).getAsJsonObject().has("review_text")) {
      this.isReviewData = true;
    } else if (jsonArray.get(0).getAsJsonObject().has("fit")) {
      this.isRentData = true;
    }

    // If the array contains one of the data types, then loop over it and create
    //   another array containing JsonObjects (that are easier to work with) and
    //   store that array in the data field
    if (isUserData || isReviewData || isRentData) {
      if (isFilePath) {
        this.userHashMap = new HashMap<>();
        this.userArray = new User[jsonArray.size()];
      } else {
        this.data = new JsonObject[jsonArray.size()];
      }

      for (int i = 0; i < jsonArray.size(); i++) {
        JsonObject e = jsonArray.get(i).getAsJsonObject();
        if (isFilePath) {
          User u = JSONConverter.jsonToUser(e);
          this.userArray[i] = u;
          this.userHashMap.put(e.get("user_id").getAsInt(), u);
        } else {
          this.data[i] = e;
        }
      }
    }
  }

  public User[] getUserArray() {
    return this.userArray;
  }

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
    // Prevent direct access to the field
    return isUserData.booleanValue();
  }

  /**
   * Method to retrieve isReviewData field.
   * @return - a boolean, the value of the field
   */
  public Boolean getIsReviewData() {
    return isReviewData.booleanValue();
  }

  /**
   * Method to retrieve isRentData field.
   * @return - a boolean, the value of the field
   */
  public Boolean getIsRentData() {
    return isRentData.booleanValue();
  }
}
