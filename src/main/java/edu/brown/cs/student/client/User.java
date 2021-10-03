package edu.brown.cs.student.client;

import edu.brown.cs.student.kdtree.KdElement;

public class User implements KdElement{

  private final int user_id;
  private final Double weight;
  private final String bust_size;
  private final Double height;
  private final Double age;
  private final String body_type;
  private final String horoscope;

  public User(int user_id, Double weight, String bust_size, Double height, Double age,
              String body_type, String horoscope) {
    this.user_id = user_id;
    this.weight = weight;
    this.bust_size = bust_size;
    this.height = height;
    this.age = age;
    this.body_type = body_type;
    this.horoscope = horoscope;
  }

  public String getHoroscope() {
    return horoscope;
  }

  @Override
  public String toString() {
    return "[" + "user_id: " + user_id + ", weight: " + weight + " lbs, bust_size: " + bust_size
        + ", height: " + height + " in., age: " + age + ", body_type: " + body_type
        + ", horoscope: " + horoscope + "]";
  }

  @Override
  public Double[] getKdPoint() {
    return new Double[] {this.height, this.weight, this.age};
  }

  @Override
  public int getElementID() {
    return this.user_id;
  }
}
