package edu.brown.cs.student.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * The REPL class for this lab.
 */
public class Repl {

  // Hashmap of available commands for the REPL
  private final HashMap<String, CommandHandler> commands;

  // A field to hold user data (user hashmap and user array)
  private final UserData userData = new UserData();

  /**
   * instantiates a REPL.
   * <p>
   * @param commands - a hashmap from String -> CommandHandler that holds
   *                 all available commands for the REPL
   */
  public Repl(HashMap<String, CommandHandler> commands) {
    this.commands = commands;
  }

  /**
   * A run method for the REPL.
   */
  public void run() {
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

          if (this.commands.containsKey(command)) {
            this.commands.get(command).run(st, userData);
          } else { // command unrecognized
            System.out.println("ERROR: Unrecognized command.");
          }
        }
      } catch (IOException e) { // some kind of read error, so the repl exits
        System.out.println("ERROR: Failed parsing input.");
        break;
      } //catch (Exception e) { // some kind of read error, so the repl exits
        //System.out.println("ERROR: Failed parsing input.");
        //break;
      //}
    }
    try {
      reader.close();
    } catch (IOException e) {
      System.out.println("ERROR: Failed to close reader.");
    }
  }
}

