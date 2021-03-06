package edu.brown.cs.student.client;

import edu.brown.cs.student.recommender.Item;

import java.util.*;

public class Student implements Item {

  private int id;
  private String name;
  private Double meetType; // KD
  private String grade; // Unused
  private Double experience; // KD
  private String horoscope; // Unused
  private String[] meetTime; // BLOOM
  private String lang; // BLOOM
  private String[] groups; // BLOOM?
  private Boolean preferGroup;

  // from ORM
  private List<String> interests; // BLOOM
  private List<String> neg; // BLOOM
  private List<String> pos; // BLOOM
  private List<Double> skills; // KD

  private List<Student> neighbors;

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

  public void setMeetTime(String[] meetTime) {
    this.meetTime = meetTime;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public void setGroups(String[] groups) {
    this.groups = groups;
  }

  public void setPreferGroup(Boolean preferGroup) {
    this.preferGroup = preferGroup;
  }

  public void setInterests(List<String> interests) {
    this.interests = interests;
  }

  public void setNeg(List<String> neg) {
    this.neg = neg;
  }

  public void setPos(List<String> pos) {
    this.pos = pos;
  }

  public void setSkills(List<Double> skills) {
    this.skills = skills;
  }

  public List<Student> getNeighbors() {
    return neighbors;
  }

  public void setNeighbors(List<Student> neighbors) {
    this.neighbors = neighbors;
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
    return this.id;
  }

  @Override
  public Double[] getKdPoint() {
    List<Double> KdList = new ArrayList<Double>();
    KdList.add(meetType);
    KdList.add(experience);
    for (Double skill : this.skills) {
      KdList.add(skill);
    }
    Double[] KdArray = KdList.toArray(new Double[KdList.size()]);
    return KdArray;
  }

  @Override
  public int getElementID() {
    return id;
  }

//  @Override
//  public String toString() {
//    return "Student: " + name;
//  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", meetType=" + meetType +
        ", grade='" + grade + '\'' +
        ", experience=" + experience +
        ", horoscope='" + horoscope + '\'' +
        ", meetTime=" + Arrays.toString(meetTime) +
        ", lang='" + lang + '\'' +
        ", groups=" + Arrays.toString(groups) +
        ", preferGroup=" + preferGroup +
        ", interests=" + interests +
        ", neg=" + neg +
        ", pos=" + pos +
        ", skills=" + skills +
        ", neighbors=" + neighbors +
        '}';
  }
}
