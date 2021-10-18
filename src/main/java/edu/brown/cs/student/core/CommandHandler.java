package edu.brown.cs.student.core;

import edu.brown.cs.student.client.Aggregator;
import edu.brown.cs.student.client.IntegrationJSONopener;
import edu.brown.cs.student.client.JSONopener;
import edu.brown.cs.student.kdtree.KdTree;

import java.util.Arrays;
import java.util.List;
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

class RecsysGenGroups implements CommandHandler {

  RecsysGenGroups() { }

  // USED FOR DEMONSTRATION PURPOSES
  Student a = new Student("A");
  Student b = new Student("B");
  Student c = new Student("C");
  Student d = new Student("D");
  Student e = new Student("E");
  Student f = new Student("F");
  Student g = new Student("G");
  Student h = new Student("H");
  Student i = new Student("I");
  Student j = new Student("J");
  Student k = new Student("K");
  Student l = new Student("L");

  List<Student> students = List.of(a, b, c, d, e, f, g, h, i, j, k, l);

  public void run(StringTokenizer st, UserData userData) {

    a.neighbors = List.of(a, j, i, g, f, b, l, e, d, c, h, k);
    b.neighbors = List.of(b, g, i, d, k, h, f, j, c, a, l, e);
    c.neighbors = List.of(c, j, h, k, b, g, l, a, f, d, e, i);
    d.neighbors = List.of(d, a, c, b, k, j, i, l, f, e, h, g);
    e.neighbors = List.of(e, a, d, i, f, l, h, j, b, k, c, g);
    f.neighbors = List.of(f, h, i, l, a, e, k, c, j, b, d, g);
    g.neighbors = List.of(g, d, c, l, b, k, h, f, e, a, j, i);
    h.neighbors = List.of(h, d, c, a, i, l, j, b, g, e, k, f);
    i.neighbors = List.of(i, b, l, j, d, f, e, a, h, k, c, g);
    j.neighbors = List.of(j, k, d, h, f, c, g, e, b, l, i, a);
    k.neighbors = List.of(k, f, i, b, e, g, d, j, l, c, a, h);
    l.neighbors = List.of(l, f, j, a, k, g, h, c, i, e, b, d);

    GroupMaker groupMaker = new GroupMaker(Integer.parseInt(st.nextToken()), students);

    for (List<Student> group : groupMaker.makeGroups()) {
      System.out.println(group.toString());
    }

    System.out.println(Arrays.toString(new IntegrationJSONopener("abredvik").getData()));

  }
}
