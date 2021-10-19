package edu.brown.cs.student.core;

import edu.brown.cs.student.GroupMaker;
import edu.brown.cs.student.client.Aggregator;
import edu.brown.cs.student.client.IntegrationJSONopener;
import edu.brown.cs.student.client.IntegrationORMopener;
import edu.brown.cs.student.client.JSONopener;
import edu.brown.cs.student.client.Student;
import edu.brown.cs.student.recommender.IntegratedRecommender;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
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
    IntegrationORMopener ORMData = new IntegrationORMopener(studentHashMap);
    try {
      studentHashMap = ORMData.addORMData();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
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
        studentData.setRecommender(recommender);
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

//  // USED FOR DEMONSTRATION PURPOSES
//  private final Student a = new Student();
//  private final Student b = new Student();
//  private final Student c = new Student();
//  private final Student d = new Student();
//  private final Student e = new Student();
//  private final Student f = new Student();
//  private final Student g = new Student();
//  private final Student h = new Student();
//  private final Student i = new Student();
//  private final Student j = new Student();
//  private final Student k = new Student();
//  private final Student l = new Student();
//
//  private final List<Student> students = List.of(a, b, c, d, e, f, g, h, i, j, k, l);

  public void run(StringTokenizer st, StudentData studentData) {

//    a.setName("A");
//    b.setName("B");
//    c.setName("C");
//    d.setName("D");
//    e.setName("E");
//    f.setName("F");
//    g.setName("G");
//    h.setName("H");
//    i.setName("I");
//    j.setName("J");
//    k.setName("K");
//    l.setName("L");
//
//    a.setNeighbors(List.of(a, j, i, g, f, b, l, e, d, c, h, k));
//    b.setNeighbors(List.of(b, g, i, d, k, h, f, j, c, a, l, e));
//    c.setNeighbors(List.of(c, j, h, k, b, g, l, a, f, d, e, i));
//    d.setNeighbors(List.of(d, a, c, b, k, j, i, l, f, e, h, g));
//    e.setNeighbors(List.of(e, a, d, i, f, l, h, j, b, k, c, g));
//    f.setNeighbors(List.of(f, h, i, l, a, e, k, c, j, b, d, g));
//    g.setNeighbors(List.of(g, d, c, l, b, k, h, f, e, a, j, i));
//    h.setNeighbors(List.of(h, d, c, a, i, l, j, b, g, e, k, f));
//    i.setNeighbors(List.of(i, b, l, j, d, f, e, a, h, k, c, g));
//    j.setNeighbors(List.of(j, k, d, h, f, c, g, e, b, l, i, a));
//    k.setNeighbors(List.of(k, f, i, b, e, g, d, j, l, c, a, h));
//    l.setNeighbors(List.of(l, f, j, a, k, g, h, c, i, e, b, d));
//
//    GroupMaker groupMaker = new GroupMaker(Integer.parseInt(st.nextToken()), students);
//
//    for (List<Student> group : groupMaker.makeGroups()) {
//      System.out.println(group.toString());
//    }
  }
}
