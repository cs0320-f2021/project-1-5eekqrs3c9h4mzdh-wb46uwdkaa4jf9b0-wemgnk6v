package edu.brown.cs.student.orm;

import java.util.Map;

public class Interests {
  private String id;
  private String interest;

  public Interests(Map<String, String> map) {
    this.id = map.get("id");
    this.interest = map.get("interest");
  }

  @Override
  public String toString() {
    return "Interests{" +
        "id='" + id + '\'' +
        ", interest='" + interest + '\'' +
        '}';
  }

  public String getId() {
    return id;
  }

  public String getInterest() {
    return interest;
  }
}
