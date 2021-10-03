package edu.brown.cs.student;

import static org.junit.Assert.assertEquals;
import com.google.gson.JsonObject;

import edu.brown.cs.student.client.JSONConverter;
import edu.brown.cs.student.client.User;
import org.junit.Test;

public class JSONConverterTest {

  @Test
  public void toStringTest() {
    JSONConverter jsonConverter = new JSONConverter();
    JsonObject json = new JsonObject();
    json.addProperty("user_id", "101");
    json.addProperty("weight", "145lbs");
    json.addProperty("bust_size", "34b");
    json.addProperty("height", "5'9\"");
    json.addProperty("age", "27");
    json.addProperty("body_type", "athletic");
    json.addProperty("horoscope", "Libra");
    User result = new User(101, 145.0, "34b", 69.0, 27.0, "athletic", "Libra");
    User result2 = jsonConverter.jsonToUser(json);
    assertEquals(101, result2.getElementID());
    Double[] kdPoint = {69.0, 145.0, 27.0};
    assertEquals(kdPoint, result2.getKdPoint());
    assertEquals(result.toString(), result2.toString());
  }

}
