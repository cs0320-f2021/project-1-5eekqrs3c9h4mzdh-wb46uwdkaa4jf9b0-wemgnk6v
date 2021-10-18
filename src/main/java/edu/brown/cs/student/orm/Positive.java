package edu.brown.cs.student.orm;

import java.util.Map;

public class Positive {
  private String id;
  private String trait;

  public Positive(Map<String, String> map) {
    this.id = map.get("id");
    this.trait = map.get("trait");
  }

  @Override
  public String toString() {
    return "Positive{" +
        "id='" + id + '\'' +
        ", trait='" + trait + '\'' +
        '}';
  }

  public String getId() {
    return id;
  }

  public String getTrait() {
    return trait;
  }
}
