package edu.brown.cs.student;

import java.util.*;

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
    List<Integer> lastGroup = groups.get(groups.size() - 1);
    if (lastGroup.size() < teamSize) {
      Random random = new Random();
      for (Integer student : lastGroup) {
        boolean foundGroup = false;
        while (!foundGroup) {
          List<Integer> nextGroup = groups.get(random.nextInt(groups.size() - 1));
          if (nextGroup.size() == teamSize) {
            nextGroup.add(student);
            foundGroup = true;
          }
        }
      }
      groups.remove(lastGroup);
    }
    return groups;
  }
}
