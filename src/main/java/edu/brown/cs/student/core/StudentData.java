package edu.brown.cs.student.core;

import edu.brown.cs.student.client.Student;
import edu.brown.cs.student.recommender.IntegratedRecommender;

import java.util.HashMap;

public class StudentData {
  private HashMap<Integer, Student> studentHashMap = null;
  private Student[] studentArray = null;
  private IntegratedRecommender recommender = null;

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

  public IntegratedRecommender getRecommender() {
    return recommender;
  }

  public void setRecommender(IntegratedRecommender recommender) {
    this.recommender = recommender;
  }
}
