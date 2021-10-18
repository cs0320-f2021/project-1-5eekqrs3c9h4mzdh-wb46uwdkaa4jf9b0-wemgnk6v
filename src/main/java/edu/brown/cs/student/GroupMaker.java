package edu.brown.cs.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GroupMaker {

  public HashMap<Integer, List<Integer>> students;
  public int teamSize;

  public GroupMaker(int teamSize, HashMap<Integer, List<Integer>> students) {
    this.teamSize = teamSize;
    this.students = students;
  }

  // ASSUMPTION: EACH STUDENT'S NEAREST NEIGHBORS CONTAINS ALL STUDENTS,
  //             ORDERED IN TERMS OF BEST PAIRING (WITH ITSELF FIRST, OBVIOUSLY)

  public List<List<Integer>> makeGroups() {
    HashSet<Integer> groupedStudents = new HashSet<>(students.size());

    List<List<Integer>> groups = new ArrayList<>(students.size() / teamSize);

    for (Integer current : students.keySet()) {
      if (!groupedStudents.contains(current)) {
        groupedStudents.add(current);
        List<Integer> thisGroup = new ArrayList<>(teamSize);
        thisGroup.add(current);
        for (Integer neighbor : students.get(current)) {
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
