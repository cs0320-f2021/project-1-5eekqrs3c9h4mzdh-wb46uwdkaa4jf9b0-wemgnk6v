package edu.brown.cs.student.core;

import edu.brown.cs.student.client.ApiClient;
import edu.brown.cs.student.client.ClientRequestGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

          if (command.equals("basicGet")) { // Basic GET request
            client.makeRequest(ClientRequestGenerator.getIntroGetRequest());

          } else if (command.equals("keyedGet")) { // GET request with an api key
            client.makeRequest(ClientRequestGenerator.getSecuredGetRequest());

          } else if (command.equals("keyedPost")) { // POST with an api key
            String name;
            if (st.hasMoreTokens()) {
              name = st.nextToken();
            } else {
              name = "";
            }
            client.makeRequest(ClientRequestGenerator.getSecuredPostRequest(name));

          } else if (command.equals("getHoroscopes")) { // GET with an api key and a string param
            String name;
            if (st.hasMoreTokens()) {
              name = st.nextToken();
            } else {
              name = "";
            }
            client.makeRequest(ClientRequestGenerator.getHoroscopeGetRequest(name));

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
