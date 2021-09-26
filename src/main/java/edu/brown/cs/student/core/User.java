package edu.brown.cs.student.core;

public class User {
  // may need UserJson class
  private int user_id;
  private int weight;
  private String bust_size;
  private int height;
  private int age;
  private String body_type;
  private String horoscope;

  public User(int user_id, int weight, String bust_size, int height, int age,
              String body_type, String horoscope) {
    this.user_id = user_id;
    this.weight = weight;
    this.bust_size = bust_size;
    this.height = height;
    this.age = age;
    this.body_type = body_type;
    this.horoscope = horoscope;
  }

  public int[] to3dPoint() {
    return new int[] {this.height, this.weight, this.age};
  }
}
