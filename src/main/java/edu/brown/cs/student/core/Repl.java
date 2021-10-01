package edu.brown.cs.student.core;

import com.google.gson.JsonObject;
import edu.brown.cs.student.client.Aggregator;
import edu.brown.cs.student.client.ApiClient;
import edu.brown.cs.student.client.JSONConverter;
import edu.brown.cs.student.client.JSONopener;
import edu.brown.cs.student.client.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * The REPL class for this lab.
 */
public class Repl {

  /**
   * instantiates a REPL.
   *
   * // @param commands - a map of string command names to IReplMethod objects, which
   *                 are a wrapper for a method to be called
   */
  public Repl() {
  }

  /**
   * This run method for the REPL requires an ApiClient object.
   *
   * @param client
   */
  public void run(ApiClient client) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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

          if (command.equals("getAll")) {
            // Instantiate new aggregator
            Aggregator aggregator = new Aggregator();

            // Load the data
            aggregator.loadData("all");

            // Print the data
            System.out.println(aggregator.usersData);
            System.out.println("User data size: " + aggregator.usersData.size());
            System.out.println(aggregator.reviewData);
            System.out.println("Review data size: " + aggregator.reviewData.size());
            System.out.println(aggregator.rentData);
            System.out.println("Rent data size: " + aggregator.rentData.size());

          } else if (command.equals("getUsers")) {
            // Instantiate new Aggregator
            Aggregator aggregator = new Aggregator();

            // Load the data
            aggregator.loadData("user");

            // Print the data
            System.out.println(aggregator.usersData);
            System.out.println("User data size: " + aggregator.usersData.size());

          } else if (command.equals("getReviews")) {
            // Instantiate new Aggregator
            Aggregator aggregator = new Aggregator();

            // Load the data
            aggregator.loadData("review");

            // Print the data
            System.out.println(aggregator.reviewData);
            System.out.println("Review data size: " + aggregator.reviewData.size());

          } else if (command.equals("getRent")) {
            // Instantiate new Aggregator
            Aggregator aggregator = new Aggregator();

            // Load the data
            aggregator.loadData("rent");

            // Print the data
            System.out.println(aggregator.rentData);
            System.out.println("Rent data size: " + aggregator.rentData.size());

          } else { // command unrecognized
            System.out.println("ERROR: Unrecognized command.");
          }
        }
      } catch (IOException e) { // some kind of read error, so the repl exits
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

