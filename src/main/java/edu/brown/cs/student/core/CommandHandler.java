package edu.brown.cs.student.core;

import edu.brown.cs.student.GroupMaker;
import edu.brown.cs.student.client.Aggregator;
import edu.brown.cs.student.client.IntegrationJSONopener;
import edu.brown.cs.student.client.JSONopener;
import edu.brown.cs.student.client.Student;
import edu.brown.cs.student.recommender.IntegratedRecommender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Interface for a CommandHandler.
 */
public interface CommandHandler {
  /**
   * Method that runs the desired code when a user types a command.
   * @param st - a StringTokenizer containing the arguments of the user's command
   * @param studentData - a variable that holds two fields for the command to access
   */
  void run(StringTokenizer st, StudentData studentData);
}

///**
// * CommandHandler for command "users".
// */
//class Users implements CommandHandler {
//
//  Users() { }
//
//  public void run(StringTokenizer st, StudentData studentData) {
//
//    String arg = st.nextToken();
//
//    if (arg.equals("online")) {
//      // Instantiate new Aggregator
//      Aggregator aggregator = new Aggregator();
//
//      // Load the data
//      aggregator.loadData("user");
//
//      // Store the data
//      studentData.userArray = aggregator.getUsersData();
//      studentData.userHashMap = aggregator.getUserHash();
//    } else {
//      JSONopener jsopen = new JSONopener(arg, true);
//      studentData.userArray = jsopen.getUserArray();
//      studentData.userHashMap = jsopen.getUserHashMap();
//    }
//    System.out.println("Loaded " + studentData.userArray.length + " users");
//  }
//}
//
///**
// * CommandHandler for command "similar".
// */
//class Similar implements CommandHandler {
//
//  Similar() { }
//
//  public void run(StringTokenizer st, StudentData studentData) {
////    int kArg = Integer.parseInt(st.nextToken());
////    String idOrWeightArgString = st.nextToken();
////    Double weightArg;
////    Double heightArg;
////    Double ageArg;
////    if (st.hasMoreTokens()) {
////      heightArg = Double.parseDouble(st.nextToken());
////      ageArg = Double.parseDouble(st.nextToken());
////      weightArg = Double.parseDouble(idOrWeightArgString);
////    } else {
////      int id = Integer.parseInt(idOrWeightArgString);
////      heightArg = userData.userHashMap.get(id).getHeight();
////      weightArg = userData.userHashMap.get(id).getWeight();
////      ageArg = userData.userHashMap.get(id).getAge();
////    }
////    KdTree kdTree = new KdTree(3);
////    int[] neighborArray =
////            kdTree.getArrayOfKnnIds(new Double[]{weightArg,
////            heightArg, ageArg}, userData.userArray, kArg);
////    for (int id : neighborArray) {
////      System.out.println(id);
////    }
//  }
//}
//
///**
// * CommandHandler for command "classify".
// */
//class Classify implements CommandHandler {
//
//  Classify() { }
//
//  public void run(StringTokenizer st, StudentData studentData) {
////    int kArg = Integer.parseInt(st.nextToken());
////    String idOrWeightArgString = st.nextToken();
////    Double weightArg;
////    Double heightArg;
////    Double ageArg;
////    if (st.hasMoreTokens()) {
////      heightArg = Double.parseDouble(st.nextToken());
////      ageArg = Double.parseDouble(st.nextToken());
////      weightArg = Double.parseDouble(idOrWeightArgString);
////    } else {
////      int id = Integer.parseInt(idOrWeightArgString);
////      heightArg = userData.userHashMap.get(id).getHeight();
////      weightArg = userData.userHashMap.get(id).getWeight();
////      ageArg = userData.userHashMap.get(id).getAge();
////    }
////    KdTree kdTree = new KdTree(3);
////    kdTree.classifyUsers(new Double[]{weightArg, heightArg, ageArg}, userData.userArray, kArg,
////            userData.userHashMap);
//  }
//}

class RecsysLoadResponses implements CommandHandler {

  public void run(StringTokenizer st, StudentData studentData) {
    IntegrationJSONopener apiData = new IntegrationJSONopener();
    Student[] studentArray = apiData.getData();
    HashMap<Integer, Student> studentHashMap = apiData.getStudentHashMap();

    studentData.setStudentArray(studentArray);
    studentData.setStudentHashMap(studentHashMap);

    System.out.println("Loaded Recommender with " + studentArray.length + " students.");
  }

}

class RecsysRecs implements CommandHandler {

  @Override
  public void run(StringTokenizer st, StudentData studentData) {
    if (st.hasMoreTokens()) {
      int numRecs = Integer.parseInt(st.nextToken());
      if (st.hasMoreTokens()) {
        int studentId = Integer.parseInt(st.nextToken());
        HashMap<Integer, Student> map = studentData.getStudentHashMap();
        IntegratedRecommender recommender = new IntegratedRecommender(studentData.getStudentHashMap());
        List<Integer> recIDList = recommender.getTopKRecommendationIDs(map.get(studentId), numRecs);
        for (Integer i : recIDList) {
          System.out.println(i);
        }
      }
    }
  }
}

class RecsysGenGroups implements CommandHandler {

  RecsysGenGroups() { }

  public void run(StringTokenizer st, StudentData studentData) {

    IntegratedRecommender recommender = new IntegratedRecommender(studentData.getStudentHashMap());

    int k = Integer.parseInt(st.nextToken());

    HashMap<Integer, List<Integer>> neighbors = new HashMap<>(studentData.getStudentArray().length);

    for (Student student : studentData.getStudentArray()) {
      neighbors.put(
          student.getId(),
          recommender.getTopKRecommendationIDs(
              student,
              studentData.getStudentArray().length));
    }

    GroupMaker groupMaker = new GroupMaker(k, neighbors);

    List<List<Integer>> groups = groupMaker.makeGroups();

    for (List<Integer> group : groups) {
      System.out.println(group.toString());
    }
  }
}
