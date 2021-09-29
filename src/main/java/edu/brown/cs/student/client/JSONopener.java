package edu.brown.cs.student.client;

import com.google.gson.*;

public class JSONopener {

    // Create a Gson instance
    Gson gson = new Gson();

    // Field to store the resulting JsonObject array
    JsonObject[] data;

    // Fields to store information about the dataset
    Boolean isUserData = false;
    Boolean isReviewData = false;
    Boolean isRentData = false;
    Boolean isNoData = false;

    public JSONopener(String json) {

        try {
            // Parse the Json string into a JsonArray
            JsonArray jsonArray = this.gson.fromJson(json, JsonArray.class);

            // Check if the array contains user, review, or rent data
            if (jsonArray.get(0).getAsJsonObject().has("weight")) {
                this.isUserData = true;
            } else if (jsonArray.get(0).getAsJsonObject().has("review_text")) {
                this.isReviewData = true;
            } else if (jsonArray.get(0).getAsJsonObject().has("fit")) {
                this.isRentData = true;
            } else {
                this.isNoData = true;
            }

            // If the array contains one of the data types, then loop over it and create
            //   another array containing JsonObjects (that are easier to work with) and
            //   store that array in the data field
            if (!this.isNoData) {
                this.data = new JsonObject[jsonArray.size()];
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject e = jsonArray.get(i).getAsJsonObject();
                    this.data[i] = e;
                }
            }

        } catch (Exception e) {
            this.isNoData = true;
        }

    }

}
