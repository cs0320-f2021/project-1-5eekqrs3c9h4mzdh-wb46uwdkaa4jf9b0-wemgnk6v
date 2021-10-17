package edu.brown.cs.student.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GroupMaker {

  public List<Student> students;
  public int teamSize;

  public GroupMaker(int teamSize, List<Student> students) {
    this.teamSize = teamSize;
    this.students = students;
  }

  // ASSUMPTION: EACH STUDENT'S NEAREST NEIGHBORS CONTAINS ALL STUDENTS,
  //             ORDERED IN TERMS OF BEST PAIRING (WITH ITSELF FIRST, OBVIOUSLY)

  public List<List<Student>> makeGroups() {
    HashSet<Student> groupedStudents = new HashSet<>(students.size());

    List<List<Student>> groups = new ArrayList<>(students.size() / teamSize);

    for (Student current : students) {
      if (!groupedStudents.contains(current)) {
        groupedStudents.add(current);
        List<Student> thisGroup = new ArrayList<>(teamSize);
        thisGroup.add(current);
        for (Student neighbor : current.neighbors) {
          if (!groupedStudents.contains(neighbor) && thisGroup.size() < teamSize) {
            groupedStudents.add(neighbor);
            thisGroup.add(neighbor);
          }
        }
        groups.add(thisGroup);
      }
    }
    return groups;
  }
}
