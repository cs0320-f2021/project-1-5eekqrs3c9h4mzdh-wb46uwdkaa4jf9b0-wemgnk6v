package edu.brown.cs.student.core;

import edu.brown.cs.student.client.Aggregator;
import edu.brown.cs.student.client.JSONopener;
import edu.brown.cs.student.client.User;
import edu.brown.cs.student.kdtree.KdTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * The REPL class for this lab.
 */
public class Repl {

  /**
   * instantiates a REPL.
   * <p>
   * // @param commands - a map of string command names to IReplMethod objects, which
   * are a wrapper for a method to be called
   */
  public Repl() {
  }

  /**
   * A run method for the REPL.
   */
  public void run() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    HashMap<Integer, User> userHashMap = null;
    User[] userArray = null;

    while (true) { // parsing input loop
      try {
        String s = reader.readLine();
        if (s == null) { // ctrl-D (exit) would result in null input
          break;
        }

        // initialize a StringTokenizer to help parse the input, broken by space or tabs
        StringTokenizer st = new StringTokenizer(s, " \t", false);

        if (st.hasMoreTokens()) { // if the input is not blank, get the first token (the command)
          String command = st.nextToken();

          if (command.equals("users")) {
            String arg = st.nextToken();
            if (arg.equals("online")) {
              // Instantiate new Aggregator
              Aggregator aggregator = new Aggregator();

              // Load the data
              aggregator.loadData("user");

              // Store the data
              userArray = aggregator.getUsersData();
              userHashMap = aggregator.getUserHash();
              System.out.println("Loaded " + userArray.length + " users");
            } else {
              JSONopener jsopen = new JSONopener(arg, true);
              userArray = jsopen.getUserArray();
              userHashMap = jsopen.getUserHashMap();
              System.out.println("Loaded " + userArray.length + " users");
            }
          } else if (command.equals("similar")) {
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
              heightArg = userHashMap.get(id).getHeight();
              weightArg = userHashMap.get(id).getWeight();
              ageArg = userHashMap.get(id).getAge();
            }
            KdTree kdTree = new KdTree(3);
            int[] neighborArray =
                kdTree.getArrayOfKnnIds(new Double[] {weightArg, heightArg, ageArg}, userArray,
                    kArg);
            for (int id : neighborArray) {
              System.out.println(id);
            }
          } else if (command.equals("classify")) {
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
              heightArg = userHashMap.get(id).getHeight();
              weightArg = userHashMap.get(id).getWeight();
              ageArg = userHashMap.get(id).getAge();
            }
            KdTree kdTree = new KdTree(3);
            kdTree.classifyUsers(new Double[] {weightArg, heightArg, ageArg}, userArray, kArg,
                userHashMap);
          } else { // command unrecognized
            System.out.println("ERROR: Unrecognized command.");
          }
        }
      } catch (IOException e) { // some kind of read error, so the repl exits
        System.out.println("ERROR: Failed parsing input.");
        break;
      } catch (Exception e) { // some kind of read error, so the repl exits
        System.out.println("ERROR: Failed parsing input.");
        break;
      }
    }
    try {
      reader.close();
    } catch (IOException e) {
      System.out.println("ERROR: Failed to close reader.");
    }
  }
}

