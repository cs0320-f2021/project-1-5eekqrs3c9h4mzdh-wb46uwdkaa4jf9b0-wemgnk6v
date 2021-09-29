package edu.brown.cs.student.client;

import com.google.gson.JsonObject;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
  URI users1 = URI.create("https://runwayapi.herokuapp.com/users-one?auth=abredvik&key=27pjaN8");
  URI users2 = URI.create("https://runwayapi.herokuapp.com/users-two?auth=abredvik&key=27pjaN8");
  URI users3 = URI.create("https://runwayapi.herokuapp.com/users-three?auth=abredvik&key=27pjaN8");
  URI users4 = URI.create("https://runwayapi.herokuapp.com/users-four?auth=abredvik&key=27pjaN8");
  URI users5 = URI.create("https://runwayapi.herokuapp.com/users-five?auth=abredvik&key=27pjaN8");
  List<URI> userLinks = List.of(users1, users2, users3, users4, users5);

  // Links for Reviews
  URI reviews1 = URI.create("https://runwayapi.herokuapp.com/reviews-one?auth=abredvik&key=27pjaN8");
  URI reviews2 = URI.create("https://runwayapi.herokuapp.com/reviews-two?auth=abredvik&key=27pjaN8");
  URI reviews3 = URI.create("https://runwayapi.herokuapp.com/reviews-three?auth=abredvik&key=27pjaN8");
  URI reviews4 = URI.create("https://runwayapi.herokuapp.com/reviews-four?auth=abredvik&key=27pjaN8");
  URI reviews5 = URI.create("https://runwayapi.herokuapp.com/reviews-five?auth=abredvik&key=27pjaN8");
  List<URI> reviewLinks = List.of(reviews1, reviews2, reviews3, reviews4, reviews5);

  // Links for Rent
  URI rent1 = URI.create("https://runwayapi.herokuapp.com/rent-one?auth=abredvik&key=27pjaN8");
  URI rent2 = URI.create("https://runwayapi.herokuapp.com/rent-two?auth=abredvik&key=27pjaN8");
  URI rent3 = URI.create("https://runwayapi.herokuapp.com/rent-three?auth=abredvik&key=27pjaN8");
  URI rent4 = URI.create("https://runwayapi.herokuapp.com/rent-four?auth=abredvik&key=27pjaN8");
  URI rent5 = URI.create("https://runwayapi.herokuapp.com/rent-five?auth=abredvik&key=27pjaN8");
  List<URI> rentLinks = List.of(rent1, rent2, rent3, rent4, rent5);

  // Main method that runs the aggregator
  public void loadData() {

    // Loop over all the user links once and keep track of the data
    for (URI api : this.userLinks) {
      JsonObject[] apiData = getData(api, "user");
      if (apiData != null) {
        merge(apiData, "user");
      }
    }
    // Store the final dataset in the usersData field
    this.usersData = this.seenUsers.values();

    // Loop over all the review links once and keep track of the data
    for (URI api : this.reviewLinks) {
      JsonObject[] apiData = getData(api, "review");
      if (apiData != null) {
        merge(apiData, "review");
      }
    }
    // Store the final dataset in the reviewData field
    this.reviewData = this.seenReviews.values();

    // Loop over all the rent links once and keep track of the data
    for (URI api : this.rentLinks) {
      JsonObject[] apiData = getData(api, "rent");
      if (apiData != null) {
        merge(apiData, "rent");
      }
    }
    // Store the final dataset in the rentData field
    this.rentData = this.seenRent.values();
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
