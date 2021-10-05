package edu.brown.cs.student.client;

import edu.brown.cs.student.kdtree.KdElement;

public class User implements KdElement {

  // User ID, an integer
  private final int user_id;
  // User weight, in pounds, a double
  private final Double weight;
  // User bust size, a string
  private final String bust_size;
  // User height, in inches, a double
  private final Double height;
  // User height, in years, a double
  private final Double age;
  // User body type, a string
  private final String body_type;
  // User horoscope, a string
  private final String horoscope;

  /**
   * Method to retrieve user height.
   * @return - user height, in inches, a double
   */
  public Double getHeight() {
    return height;
  }

  /**
   * Method to retrieve user age.
   * @return - user age, in years, a double
   */
  public Double getAge() {
    return age;
  }

  /**
   * Method to retrieve user weight.
   * @return - user weight, in pounds, a double
   */
  public Double getWeight() {
    return weight;
  }

  /**
   * Method to retrieve user horoscope.
   * @return - user horoscope, a string
   */
  public String getHoroscope() {
    return horoscope;
  }

  /**
   * Constructor for a User.
   * @param user_id - the user ID, an integer
   * @param weight - the user weight, in pounds, a double
   * @param bust_size - the user bust size, a string
   * @param height - the user height, in inches, a double
   * @param age - the user age, in years, a double
   * @param body_type - the user body type, a string
   * @param horoscope - the user horoscope, a string
   */
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

  @Override
  public String toString() {
    return "[" + "user_id: " + user_id + ", weight: " + weight + " lbs, bust_size: " + bust_size
        + ", height: " + height + " in., age: " + age + ", body_type: " + body_type
        + ", horoscope: " + horoscope + "]";
  }

  /**
   * Method to retrieve a K-d point for the K-d tree.
   * @return - an array of doubles corresponding to height, weight, age
   */
  @Override
  public Double[] getKdPoint() {
    return new Double[] {this.weight, this.height, this.age};
  }

  /**
   * Method to retrieve this user's ID for the K-d tree.
   * @return - this user's ID, an integer
   */
  @Override
  public int getElementID() {
    return this.user_id;
  }
}
