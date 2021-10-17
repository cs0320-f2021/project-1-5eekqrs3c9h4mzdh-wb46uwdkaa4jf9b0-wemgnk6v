package edu.brown.cs.student.core;

import java.util.List;

public class Student {

  public String name;

  // ASSUMPTION: EACH STUDENT'S NEAREST NEIGHBORS CONTAINS ALL STUDENTS,
  //             ORDERED IN TERMS OF BEST PAIRING (WITH ITSELF FIRST, OBVIOUSLY)
  // Prevents the event that someone does not have enough neighbors to form a group
  public List<Student> neighbors = null;

  public Student(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Student: " + name;
  }
}
