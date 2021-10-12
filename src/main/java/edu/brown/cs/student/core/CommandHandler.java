package edu.brown.cs.student.core;

import edu.brown.cs.student.client.Aggregator;
import edu.brown.cs.student.client.JSONopener;
import edu.brown.cs.student.kdtree.KdTree;
import java.util.StringTokenizer;

/**
 * Interface for a CommandHandler.
 */
public interface CommandHandler {
  /**
   * Method that runs the desired code when a user types a command.
   * @param st - a StringTokenizer containing the arguments of the user's command
   * @param userData - a variable that holds two fields for the command to access
   */
  void run(StringTokenizer st, UserData userData);
}

/**
 * CommandHandler for command "users".
 */
class Users implements CommandHandler {

  Users() { }

  public void run(StringTokenizer st, UserData userData) {

    String arg = st.nextToken();

    if (arg.equals("online")) {
      // Instantiate new Aggregator
      Aggregator aggregator = new Aggregator();

      // Load the data
      aggregator.loadData("user");

      // Store the data
      userData.userArray = aggregator.getUsersData();
      userData.userHashMap = aggregator.getUserHash();
    } else {
      JSONopener jsopen = new JSONopener(arg, true);
      userData.userArray = jsopen.getUserArray();
      userData.userHashMap = jsopen.getUserHashMap();
    }
    System.out.println("Loaded " + userData.userArray.length + " users");
  }
}

/**
 * CommandHandler for command "similar".
 */
class Similar implements CommandHandler {

  Similar() { }

  public void run(StringTokenizer st, UserData userData) {
    int kArg = Integer.parseInt(st.nextToken());
    String idOrWeightArgString = st.nextToken();
    Double weightArg;
    Double heightArg;
    Double ageArg;
    if (st.hasMoreTokens()) {
      heightArg = Double.parseDouble(st.nextToken());
      ageArg = Double.parseDouble(st.nextToken());
      weightArg = Double.parseDouble(idOrWeightArgString);
    } else {
      int id = Integer.parseInt(idOrWeightArgString);
      heightArg = userData.userHashMap.get(id).getHeight();
      weightArg = userData.userHashMap.get(id).getWeight();
      ageArg = userData.userHashMap.get(id).getAge();
    }
    KdTree kdTree = new KdTree(3);
    int[] neighborArray =
            kdTree.getArrayOfKnnIds(new Double[]{weightArg, heightArg, ageArg}, userData.userArray,
                    kArg);
    for (int id : neighborArray) {
      System.out.println(id);
    }
  }
}

/**
 * CommandHandler for command "classify".
 */
class Classify implements CommandHandler {

  Classify() { }

  public void run(StringTokenizer st, UserData userData) {
    int kArg = Integer.parseInt(st.nextToken());
    String idOrWeightArgString = st.nextToken();
    Double weightArg;
    Double heightArg;
    Double ageArg;
    if (st.hasMoreTokens()) {
      heightArg = Double.parseDouble(st.nextToken());
      ageArg = Double.parseDouble(st.nextToken());
      weightArg = Double.parseDouble(idOrWeightArgString);
    } else {
      int id = Integer.parseInt(idOrWeightArgString);
      heightArg = userData.userHashMap.get(id).getHeight();
      weightArg = userData.userHashMap.get(id).getWeight();
      ageArg = userData.userHashMap.get(id).getAge();
    }
    KdTree kdTree = new KdTree(3);
    kdTree.classifyUsers(new Double[]{weightArg, heightArg, ageArg}, userData.userArray, kArg,
            userData.userHashMap);
  }
}
