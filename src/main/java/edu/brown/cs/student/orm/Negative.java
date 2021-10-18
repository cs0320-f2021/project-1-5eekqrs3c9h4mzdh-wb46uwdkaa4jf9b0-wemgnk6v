package edu.brown.cs.student.orm;

import java.util.Map;

public class Negative {
  private String id;
  private String trait;

  public Negative(Map<String, String> map) {
    this.id = map.get("id");
    this.trait = map.get("trait");
  }

  @Override
  public String toString() {
    return "Negative{" +
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

