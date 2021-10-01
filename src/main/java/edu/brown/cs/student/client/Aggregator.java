package edu.brown.cs.student.client;

import com.google.gson.JsonObject;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;

public class Aggregator {

  // Create API Client to make requests
  ApiClient client = new ApiClient();

  // Fields to hold the final datasets of user, review, and rent data
  public Collection<User> usersData;
  public Collection<JsonObject> reviewData;
  public Collection<JsonObject> rentData;

  // Fields to hold a hashmap from ids -> jsonObjects (user, review, or rent) in order
  //   to keep track of what elements we've already seen
  public HashMap<Integer, User> seenUsers = new HashMap<>();
  public HashMap<Integer, JsonObject> seenReviews = new HashMap<>();
  public HashMap<Integer, JsonObject> seenRent = new HashMap<>();

  // Empty constructor for the aggregator
  public Aggregator() { }

  // Links for Users
  URI users1 = URI.create("https://runwayapi.herokuapp.com/users-one?auth=abredvik&key=27pjaN8"); // one call? (frequent errors)
  URI users2 = URI.create("https://runwayapi.herokuapp.com/users-two?auth=abredvik&key=27pjaN8"); // three calls (good)
  URI users3 = URI.create("https://runwayapi.herokuapp.com/users-three?auth=abredvik&key=27pjaN8"); // three calls (good)
  URI users4 = URI.create("https://runwayapi.herokuapp.com/users-four?auth=abredvik&key=27pjaN8"); // one call (long time)
  URI users5 = URI.create("https://runwayapi.herokuapp.com/users-five?auth=abredvik&key=27pjaN8"); // two calls (fast, but sometimes unreliable)

  // Links for Reviews
  URI reviews1 = URI.create("https://runwayapi.herokuapp.com/reviews-one?auth=abredvik&key=27pjaN8"); // one call (many errors)
  URI reviews2 = URI.create("https://runwayapi.herokuapp.com/reviews-two?auth=abredvik&key=27pjaN8"); // two calls (good, a little slow)
  URI reviews3 = URI.create("https://runwayapi.herokuapp.com/reviews-three?auth=abredvik&key=27pjaN8"); // three calls (changes data, but fast)
  URI reviews4 = URI.create("https://runwayapi.herokuapp.com/reviews-four?auth=abredvik&key=27pjaN8"); // one call (slow)
  URI reviews5 = URI.create("https://runwayapi.herokuapp.com/reviews-five?auth=abredvik&key=27pjaN8"); // three calls (fast, reliable)

  // Links for Rent
  URI rent1 = URI.create("https://runwayapi.herokuapp.com/rent-one?auth=abredvik&key=27pjaN8"); // one call (many errors)
  URI rent2 = URI.create("https://runwayapi.herokuapp.com/rent-two?auth=abredvik&key=27pjaN8"); // two calls (a little slow, but good)
  URI rent3 = URI.create("https://runwayapi.herokuapp.com/rent-three?auth=abredvik&key=27pjaN8"); // three calls (fast, few errors)
  URI rent4 = URI.create("https://runwayapi.herokuapp.com/rent-four?auth=abredvik&key=27pjaN8"); // one call (very slow)
  URI rent5 = URI.create("https://runwayapi.herokuapp.com/rent-five?auth=abredvik&key=27pjaN8"); // two calls (fast, inaccurate data)

  // Main method that runs the aggregator
  public void loadData(String type) {

    if (type.equals("all") || type.equals("user")) {
      // Ping user apis
      ping(users1, "user", 1);
      ping(users2, "user", 3);
      ping(users3, "user", 3);
      ping(users4, "user", 1);
      ping(users5, "user", 2);

      // Store the final dataset in the usersData field
      this.usersData = this.seenUsers.values();
    }

    if (type.equals("all") || type.equals("review")) {
      // Ping review apis
      ping(reviews1, "review", 1);
      ping(reviews2, "review", 2);
      ping(reviews3, "review", 3);
      ping(reviews4, "review", 1);
      ping(reviews5, "review", 3);

      // Store the final dataset in the reviewData field
      this.reviewData = this.seenReviews.values();
    }

    if (type.equals("all") || type.equals("rent")) {
      // Ping rent apis
      ping(rent1, "rent", 1);
      ping(rent2, "rent", 2);
      ping(rent3, "rent", 3);
      ping(rent4, "rent", 1);
      ping(rent5, "rent", 2);

      // Store the final dataset in the rentData field
      this.rentData = this.seenRent.values();
    }
  }

  // Method that calls an API a set number of times
  public void ping(URI api, String type, Integer n) {
    for (int k = 1; k <= n; k++) {
      JsonObject[] apiData = getData(api, type);
      if (apiData != null) {
        merge(apiData, type);
      }
    }
  }

  // Method that pings the API, opens the JSON, and returns the resulting JsonObject array
  public JsonObject[] getData(URI from, String type) {
    JSONopener jopen = new JSONopener(this.client.makeRequest(ClientRequestGenerator.getRequest(from)));

    // Check to make sure it's the right type of data
    if ((type.equals("user") && jopen.isUserData) || (type.equals("review") && jopen.isReviewData) ||
            (type.equals("rent") && jopen.isRentData)) {
      return jopen.data;
    } else {
      // If it's not (wrong type of data or a malicious error), then return null
      return null;
    }
  }

  // Merge a dataset (JsonObject array) with the final dataset, making sure
  //   not to add repeats
  public void merge(JsonObject[] with, String type) {

    switch (type) {
      case "user":
        for (JsonObject jo : with) {
          if (!seenUsers.containsKey(jo.get("user_id").getAsInt())) {
            seenUsers.put(jo.get("user_id").getAsInt(), JSONConverter.jsonToUser(jo));
          }
        }
        break;
      case "review":
        for (JsonObject jo : with) {
          if (!seenReviews.containsKey(jo.get("id").getAsInt())) {
            seenReviews.put(jo.get("id").getAsInt(), jo);
          }
        }
        break;
      case "rent":
        for (JsonObject jo : with) {
          if (!seenRent.containsKey(jo.get("id").getAsInt())) {
            seenRent.put(jo.get("id").getAsInt(), jo);
          }
        }
        break;
    }
  }
}
