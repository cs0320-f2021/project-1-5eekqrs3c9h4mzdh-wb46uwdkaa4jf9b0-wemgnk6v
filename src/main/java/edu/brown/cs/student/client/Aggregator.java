package edu.brown.cs.student.client;

import java.net.URI;

public class Aggregator {

  // Create API Client to make requests

  ApiClient client = new ApiClient();

  //public [ ] usersData;
  //public [ ] reviewsData;
  //public [ ] rentData;

  // Links for Users
  URI users1 = URI.create("https://runwayapi.herokuapp.com/users-one");
  URI users2 = URI.create("https://runwayapi.herokuapp.com/users-two");
  URI users3 = URI.create("https://runwayapi.herokuapp.com/users-three");
  URI users4 = URI.create("https://runwayapi.herokuapp.com/users-four");
  URI users5 = URI.create("https://runwayapi.herokuapp.com/users-five");

  // Links for Reviews
  URI reviews1 = URI.create("https://runwayapi.herokuapp.com/reviews-one");
  URI reviews2 = URI.create("https://runwayapi.herokuapp.com/reviews-two");
  URI reviews3 = URI.create("https://runwayapi.herokuapp.com/reviews-three");
  URI reviews4 = URI.create("https://runwayapi.herokuapp.com/reviews-four");
  URI reviews5 = URI.create("https://runwayapi.herokuapp.com/reviews-five");

  // Links for Rent
  URI rent1 = URI.create("https://runwayapi.herokuapp.com/rent-one");
  URI rent2 = URI.create("https://runwayapi.herokuapp.com/rent-two");
  URI rent3 = URI.create("https://runwayapi.herokuapp.com/rent-three");
  URI rent4 = URI.create("https://runwayapi.herokuapp.com/rent-four");
  URI rent5 = URI.create("https://runwayapi.herokuapp.com/rent-five");

  // Main method that runs the aggregator
  //public void loadData() {}

  //public getJSON(url, max-timeout?) {}

  //public requestUser() {}

  //public requestReviews() {}

  //public requestRent() {}

}
