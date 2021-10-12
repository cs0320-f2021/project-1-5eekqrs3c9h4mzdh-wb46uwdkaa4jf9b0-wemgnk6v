package edu.brown.cs.student.core;

import edu.brown.cs.student.client.ApiClient;
import edu.brown.cs.student.kdtree.KdTree;

import java.util.HashMap;


/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {

    // Hashmap of commands
    HashMap<String, CommandHandler> commands = new HashMap<>();
    commands.put("users", new Users());
    commands.put("similar", new Similar());
    commands.put("classify", new Classify());

    Repl repl = new Repl(commands);
    repl.run();
  }
}
