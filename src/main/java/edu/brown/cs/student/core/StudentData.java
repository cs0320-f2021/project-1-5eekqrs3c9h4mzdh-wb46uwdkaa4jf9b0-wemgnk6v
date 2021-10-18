package edu.brown.cs.student.core;

import edu.brown.cs.student.client.Student;
import edu.brown.cs.student.recommender.IntegratedRecommender;

import java.util.HashMap;

public class StudentData {
  private HashMap<Integer, Student> studentHashMap = null;
  private Student[] studentArray = null;

  public StudentData() { }

  public HashMap<Integer, Student> getStudentHashMap() {
    return studentHashMap;
  }

  public void setStudentHashMap(HashMap<Integer, Student> studentHashMap) {
    this.studentHashMap = studentHashMap;
  }

  public Student[] getStudentArray() {
    return studentArray;
  }

  public void setStudentArray(Student[] studentArray) {
    this.studentArray = studentArray;
  }
}
