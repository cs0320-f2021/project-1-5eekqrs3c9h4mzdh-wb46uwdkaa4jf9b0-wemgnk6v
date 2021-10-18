package edu.brown.cs.student.client;

import edu.brown.cs.student.recommender.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Student implements Item {

  private int id;
  private String name;
  private Double meetType;
  private String grade; // Unused
  private Double experience;
  private String horoscope;
  private Set<String> meetTime; // Review
  private String lang;
  private Set<String> groups;

  // from ORM
  private int id2;
  private Set<String> interests;
  private Set<String> neg;
  private Set<String> pos;
  private HashMap<String, Double> skills;

  @Override
  public List<String> getVectorRepresentation() {
    return null;
  }

  @Override
  public int getId() {
    return 0;
  }

  @Override
  public Double[] getKdPoint() {
    return new Double[0];
  }

  @Override
  public int getElementID() {
    return 0;
  }
}
