package edu.brown.cs.student.core;

import edu.brown.cs.student.client.ApiClient;


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
    ApiClient client = new ApiClient();
    Repl repl = new Repl();
    repl.run(client);
  }
}
