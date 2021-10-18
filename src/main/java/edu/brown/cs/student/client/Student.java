package edu.brown.cs.student.client;

import edu.brown.cs.student.recommender.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Student implements Item {

  private int id;
  private String name;
  private Double meetType; // KD
  private String grade; // Unused
  private Double experience; // KD
  private String horoscope; // Unused
  private Set<String> meetTime; // Review
  private String lang; // BLOOM
  private Set<String> groups; // BLOOM?

  // from ORM
  private Set<String> interests; // BLOOM
  private Set<String> neg; // BLOOM
  private Set<String> pos; // BLOOM
  private HashMap<String, Double> skills; // KD

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
    List<Double> KdList = new ArrayList<Double>();
    KdList.add(meetType);
    KdList.add(experience);
    for (String key : skills.keySet()) {
      KdList.add(skills.get(key));
    }
    Double[] KdArray = (Double[]) KdList.toArray();
    return KdArray;
  }

  @Override
  public int getElementID() {
    return id;
  }
}
