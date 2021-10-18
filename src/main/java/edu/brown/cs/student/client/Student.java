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
  private Set<String> meetTime; // BLOOM
  private String lang; // BLOOM
  private Set<String> groups; // BLOOM?
  private Boolean preferGroup;

  // from ORM
  private Set<String> interests; // BLOOM
  private Set<String> neg; // BLOOM
  private Set<String> pos; // BLOOM
  private HashMap<String, Double> skills; // KD

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMeetType(Double meetType) {
    this.meetType = meetType;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public void setExperience(Double experience) {
    this.experience = experience;
  }

  public void setHoroscope(String horoscope) {
    this.horoscope = horoscope;
  }

  public void setMeetTime(Set<String> meetTime) {
    this.meetTime = meetTime;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public void setGroups(Set<String> groups) {
    this.groups = groups;
  }

  public void setInterests(Set<String> interests) {
    this.interests = interests;
  }

  public void setNeg(Set<String> neg) {
    this.neg = neg;
  }

  public void setPos(Set<String> pos) {
    this.pos = pos;
  }

  public void setSkills(HashMap<String, Double> skills) {
    this.skills = skills;
  }

  @Override
  public List<String> getVectorRepresentation() {
    List<String> vectorRepresentation = new ArrayList<>();
    for (String i : interests) {
      vectorRepresentation.add(i);
    }
    for (String i : pos) {
      vectorRepresentation.add(i);
    }
    for (String i : neg) {
      vectorRepresentation.add(i);
    }
    for (String i : meetTime) {
      vectorRepresentation.add(i);
    }
    if (preferGroup) {
      for (String i : groups) {
        vectorRepresentation.add(i);
      }
    }
    return vectorRepresentation;
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

  @Override
  public String toString() {
    return "Student: " + id;
  }
}
