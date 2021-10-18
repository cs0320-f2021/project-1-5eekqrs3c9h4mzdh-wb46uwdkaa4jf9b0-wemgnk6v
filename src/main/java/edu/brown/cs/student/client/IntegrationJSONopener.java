package edu.brown.cs.student.client;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;

/**
 * Class that handles opening JSONs.
 */
public class IntegrationJSONopener {

  // Field to store the resulting JsonObject array
  private final Student[] data;

  private final HashMap<Integer, Student> studentHashMap;

  /**
   * Constructor for an IntegrationJSONopener.
   */
  public IntegrationJSONopener(String csLogin) {

    // Create a Gson instance
    Gson gson = new Gson();

    ApiClient client = new ApiClient();

    String url = "https://runwayapi.herokuapp.com/integration";

    JsonArray jsonArray =
        gson.fromJson(
            client.makeRequest(
                ClientRequestGenerator.postRequest(url, csLogin)), JsonArray.class);

    this.data = new Student[jsonArray.size()];

    this.studentHashMap = new HashMap<>(jsonArray.size());

    for (int i = 0; i < jsonArray.size(); i++) {
      JsonObject jsonStudent = jsonArray.get(i).getAsJsonObject();

      Student student = new Student();
      student.setId(jsonStudent.get("id").getAsInt());
      student.setName(jsonStudent.get("name").getAsString());
      if (jsonStudent.get("meeting").getAsString().equals("Personally")) {
        student.setMeetType(0.0);
      } else {
        student.setMeetType(100.0);
      }




      this.data[i] = student;

    }
  }

  /**
   * Method to retrieve the JsonObject array.
   *
   * @return - a copy of the JsonObject array
   */
  public Student[] getData() {
    return data.clone();
  }
}
