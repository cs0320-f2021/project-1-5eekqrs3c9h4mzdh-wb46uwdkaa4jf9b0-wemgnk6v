package edu.brown.cs.student.client;

import com.google.gson.JsonObject;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;

/**
 * Class that aggregates the APIs, retrieves their data, and stores it.
 */
public class Aggregator {

  // Create API Client to make requests
  private final ApiClient client = new ApiClient();

  // Fields to hold the final datasets of user, review, and rent data
  private Collection<User> usersData;
  private Collection<JsonObject> reviewData;
  private Collection<JsonObject> rentData;

  // Fields to hold a hashmap from ids -> jsonObjects (user, review, or rent) in order
  //   to keep track of what elements we've already seen
  private final HashMap<Integer, User> seenUsers = new HashMap<>();
  private final HashMap<Integer, JsonObject> seenReviews = new HashMap<>();
  private final HashMap<Integer, JsonObject> seenRent = new HashMap<>();

  // Links for Users
  private final URI users1 =
      URI.create("https://runwayapi.herokuapp.com/users-one?auth=abredvik&key=27pjaN8");
  private final URI users2 =
      URI.create("https://runwayapi.herokuapp.com/users-two?auth=abredvik&key=27pjaN8");
  private final URI users3 =
      URI.create("https://runwayapi.herokuapp.com/users-three?auth=abredvik&key=27pjaN8");
  private final URI users4 =
      URI.create("https://runwayapi.herokuapp.com/users-four?auth=abredvik&key=27pjaN8");
  private final URI users5 =
      URI.create("https://runwayapi.herokuapp.com/users-five?auth=abredvik&key=27pjaN8");

  // Links for Reviews
  private final URI reviews1 =
      URI.create("https://runwayapi.herokuapp.com/reviews-one?auth=abredvik&key=27pjaN8");
  private final URI reviews2 =
      URI.create("https://runwayapi.herokuapp.com/reviews-two?auth=abredvik&key=27pjaN8");
  private final URI reviews3 =
      URI.create("https://runwayapi.herokuapp.com/reviews-three?auth=abredvik&key=27pjaN8");
  private final URI reviews4 =
      URI.create("https://runwayapi.herokuapp.com/reviews-four?auth=abredvik&key=27pjaN8");
  private final URI reviews5 =
      URI.create("https://runwayapi.herokuapp.com/reviews-five?auth=abredvik&key=27pjaN8");

  // Links for Rent
  private final URI rent1 =
      URI.create("https://runwayapi.herokuapp.com/rent-one?auth=abredvik&key=27pjaN8");
  private final URI rent2 =
      URI.create("https://runwayapi.herokuapp.com/rent-two?auth=abredvik&key=27pjaN8");
  private final URI rent3 =
      URI.create("https://runwayapi.herokuapp.com/rent-three?auth=abredvik&key=27pjaN8");
  private final URI rent4 =
      URI.create("https://runwayapi.herokuapp.com/rent-four?auth=abredvik&key=27pjaN8");
  private final URI rent5 =
      URI.create("https://runwayapi.herokuapp.com/rent-five?auth=abredvik&key=27pjaN8");

  /**
   * Constructor for the Aggregator (empty).
   */
  public Aggregator() {
  }

  /**
   * Method to retrieve the User data.
   *
   * @return - a copy of the User data.
   */
  public User[] getUsersData() {
    User[] copy = new User[this.usersData.size()];
    int i = 0;
    for (User u : this.usersData) {
      copy[i] = u;
      i++;
    }
    return copy;
  }

  public HashMap<Integer, User> getUserHash() {
    return this.seenUsers;
  }

  /**
   * Method to retrieve the Review data.
   *
   * @return - a copy of the Review data
   */
  public JsonObject[] getReviewData() {
    JsonObject[] copy = new JsonObject[this.reviewData.size()];
    int i = 0;
    for (JsonObject rev : this.reviewData) {
      copy[i] = rev;
      i++;
    }
    return copy;
  }

  /**
   * Method to retrieve Rent data.
   *
   * @return - a copy of the Rent data
   */
  public JsonObject[] getRentData() {
    JsonObject[] copy = new JsonObject[this.rentData.size()];
    int i = 0;
    for (JsonObject rev : this.rentData) {
      copy[i] = rev;
      i++;
    }
    return copy;
  }

  /**
   * Method that handles pinging/loading the data (runs the aggregator).
   *
   * @param type - the type of data to retrieve
   */
  public void loadData(String type) {

    if (type.equals("all") || type.equals("user")) {
      // Ping user apis
      ping(users1, "user", 1); // one call (frequent errors)
      ping(users2, "user", 3); // three calls (good)
      ping(users3, "user", 3); // three calls (good)
      ping(users4, "user", 1); // one call (long time)
      ping(users5, "user", 2); // two calls (fast, but sometimes unreliable)

      // Store the final dataset in the usersData field
      this.usersData = this.seenUsers.values();
    }

    if (type.equals("all") || type.equals("review")) {
      // Ping review apis
      ping(reviews1, "review", 1); // one call (many errors)
      ping(reviews2, "review", 2); // two calls (good, a little slow)
      ping(reviews3, "review", 3); // three calls (changes data, but fast)
      ping(reviews4, "review", 1); // one call (slow)
      ping(reviews5, "review", 3); // three calls (fast, reliable)

      // Store the final dataset in the reviewData field
      this.reviewData = this.seenReviews.values();
    }

    if (type.equals("all") || type.equals("rent")) {
      // Ping rent apis
      ping(rent1, "rent", 1); // one call (many errors)
      ping(rent2, "rent", 2); // two calls (a little slow, but good)
      ping(rent3, "rent", 3); // three calls (fast, few errors)
      ping(rent4, "rent", 1); // one call (very slow)
      ping(rent5, "rent", 2); // two calls (fast, inaccurate data)

      // Store the final dataset in the rentData field
      this.rentData = this.seenRent.values();
    }
  }

  /**
   * Method that pings an API a certain number of times and merges its data.
   *
   * @param api  - the API to ping
   * @param type - the type of data to retrieve
   * @param n    - the number of times to ping the API
   */
  public void ping(URI api, String type, Integer n) {
    for (int k = 1; k <= n; k++) {
      JsonObject[] apiData = getData(api, type);
      if (apiData != null) {
        merge(apiData, type);
      }
    }
  }


  /**
   * Method that makes a GET request, opens the JSON, and returns the data.
   *
   * @param from - the URI to make the GET request
   * @param type - the type of data to retrieve
   * @return - the data held in an array of JsonObjects
   */
  public JsonObject[] getData(URI from, String type) {
    JSONopener jopen = new JSONopener(this.client.
        makeRequest(ClientRequestGenerator.getRequest(from)), false);

    // Check to make sure it's the right type of data
    if ((type.equals("user") && jopen.getIsUserData())
        || (type.equals("review") && jopen.getIsReviewData())
        || (type.equals("rent") && jopen.getIsRentData())) {
      return jopen.getData();
    } else {
      // If it's not (wrong type of data or a malicious error), then return null
      return null;
    }
  }

  /**
   * Method that merges a new JsonObject array with an existing one (prevents repeated elements).
   *
   * @param with - the JsonObject array to merge with the current
   * @param type - the type of data the array holds
   */
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
      default: // Should never reach this point
    }
  }
}
