package edu.brown.cs.student.core;

import edu.brown.cs.student.client.ApiClient;
import edu.brown.cs.student.kdtree.KdTree;


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
//    KdTree tree = new KdTree(2);
//    int[][] values =
//        new int[][] {new int[] {6, 5}, new int[] {0, 5}, new int[] {6, 2}, new int[] {4, 1},
//            new int[] {4, 6}, new int[] {7, 0}, new int[] {6, 4}};
//    tree.printKNN(new int[] {5, 4}, values, 1);
  }
}
