package edu.brown.cs.student.orm;

import java.util.Map;

public class Skills {
  private String id;
  private String name;
  private Double commenting;
  private Double testing;
  private Double OOP;
  private Double algorithms;
  private Double teamwork;
  private Double frontend;

  public Skills(Map<String, String> map) {
    System.out.println(map.keySet());
    this.id = map.get("id");
    this.name = map.get("name");
    this.commenting = Double.parseDouble(map.get("commenting"));
    this.testing = Double.parseDouble(map.get("testing"));
    this.OOP = Double.parseDouble(map.get("OOP"));
    this.algorithms = Double.parseDouble(map.get("algorithms"));
    this.teamwork = Double.parseDouble(map.get("teamwork"));
    this.frontend = Double.parseDouble(map.get("frontend"));
  }

  @Override
  public String toString() {
    return "Skills{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", commenting='" + commenting + '\'' +
        ", testing='" + testing + '\'' +
        ", OOP='" + OOP + '\'' +
        ", algorithms='" + algorithms + '\'' +
        ", teamwork='" + teamwork + '\'' +
        ", frontend='" + frontend + '\'' +
        '}';
  }

  public String getId() {
    return id;
  }
}
