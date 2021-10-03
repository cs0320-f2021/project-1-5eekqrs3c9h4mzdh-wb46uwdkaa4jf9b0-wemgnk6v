package edu.brown.cs.student;

import edu.brown.cs.student.client.User;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserTest {

  @Test
  public void toStringTest() {
    User user0 = new User(100, 0.0, "32B", 0.0, 0.0, "type0", "Gemini");
    String result = "[user_id: 100, weight: 0.0 lbs, bust_size: 32B, height: 0.0 in., age: 0.0, body_type: type0, horoscope: Gemini]";
    assertEquals(result, user0.toString());
  }

  @Test
  public void getKdPointTest() {
    User user0 = new User(100, 0.0, "32B", 0.0, 0.0, "type0", "Gemini");
    Double[] result = {0.0, 0.0, 0.0};
    assertEquals(result, user0.getKdPoint());
  }

  @Test
  public void getElementIDTest() {
    User user0 = new User(100, 0.0, "32B", 0.0, 0.0, "type0", "Gemini");
    assertEquals(100, user0.getElementID(), 0.01);
  }
}
