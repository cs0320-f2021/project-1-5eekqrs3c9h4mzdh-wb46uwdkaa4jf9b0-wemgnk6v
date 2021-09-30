package edu.brown.cs.student.kdtree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KdTree {
  // "However, to make this data structure extensible and reusable,
  // you should write an abstraction, using the principles above,
  // that can handle any number of dimensions."


  // To do in another class?
  // - convert User list into array of 3d arrays (to3dPoint)
  // - call buildKdTree with the array of 3d points
  // - edit code to have some way to keep track of User IDs because
  //    they'll be necessary for printing results of KNN
  private Node root;
  private int numDimensions;
  private PriorityQueue<Neighbor> neighbors = new PriorityQueue<Neighbor>();

  public KdTree(int numDimensions) {
    this.root = null;
    this.numDimensions = numDimensions;
  }

  // private class Node has k-D point, left neighbor, right neighbor, ____
  private class Node {
    private int[] kdPoint;
    private Node leftChild;
    private Node rightChild;
    private int axis;

    Node(int[] kdPoint, int axis) {
      // kdPoint is an array of length k
      this.kdPoint = kdPoint;
      this.leftChild = null;
      this.rightChild = null;
      this.axis = axis;
    }

    public double calcDistance(int[] target) {
      double sum = 0;
      for (int i = 0; i < numDimensions; i++) {
        sum = sum + Math.pow((target[i] - kdPoint[i]), 2);
      }
      return Math.sqrt(sum);
    }
  }

  private class Neighbor implements Comparable<Neighbor> {
    private int[] kdPoint;
    private double distance;

    Neighbor(int[] kdPoint, double distance) {
      this.kdPoint = kdPoint;
      this.distance = distance;
    }

    @Override
    public int compareTo(Neighbor neighbor) {
      // sort in descending order
      return Double.compare(neighbor.distance, this.distance);
    }
  }

  private Node buildKdTree(int[][] kdArray, int depth) {
    int arrayLen = kdArray.length;
    if (arrayLen == 0) {
      return null;
    }
    // if array is empty return null
    // if the array is 1 just return that array turned into a node

    // Select axis
    int axis = depth % numDimensions;

    // Sort array of KD points by axis
    Arrays.sort(kdArray, Comparator.comparingInt(o -> o[axis]));

    // values that are greater than or equal to the median element are on the right side
    int middleNum = arrayLen / 2;
    int i = middleNum - 1;
    while ((i >= 0) && (kdArray[middleNum][axis] == kdArray[i][axis])) {
      middleNum = i;
      i = i - 1;
    }
    int[] middleElement = kdArray[middleNum];

    Node newNode = new Node(middleElement, axis);
    // System.out.println(Arrays.toString(newNode.kdPoint));
    if (root == null) {
      this.root = newNode;
    }

    newNode.leftChild = buildKdTree(Arrays.copyOfRange(kdArray, 0, middleNum), depth + 1);
    newNode.rightChild =
        buildKdTree(Arrays.copyOfRange(kdArray, middleNum + 1, arrayLen), depth + 1);
    return newNode;
  }

  private void findKNN(Node node, int[] target, int k) {
    if (node == null) {
      // System.out.println("null if");
      return;
    }

    double distance = node.calcDistance(target);

    if (neighbors.size() < k) {
      // System.out.println("neighbors.size() < k: " + Arrays.toString(node.kdPoint));
      Neighbor newNeighbor = new Neighbor(node.kdPoint, distance);
      neighbors.add(newNeighbor);
//      findKNN(node.rightChild, target, k);
//      findKNN(node.leftChild, target, k);
    } else if (distance < neighbors.peek().distance) {
      // System.out.println("distance < neighbors.peek().distance: " + Arrays.toString(node.kdPoint));
//      System.out.println("leftchild: " + node.leftChild); IS NULL!!
      Neighbor newNeighbor = new Neighbor(node.kdPoint, distance);
      neighbors.poll();
      neighbors.add(newNeighbor);
    }

    int axis = node.axis;
//    System.out.println("axis:" + axis);
    int targetAxisVal = target[axis];
//    System.out.println("targetAxisVal:" + targetAxisVal);
    int nodeAxisVal = node.kdPoint[axis];
//    System.out.println("nodeAxisVal:" + nodeAxisVal);
//    System.out.println("neighbors.peek().distance:" + neighbors.peek().distance);

    if ((neighbors.size() < k) ||
        (neighbors.peek().distance > (Math.abs(targetAxisVal - nodeAxisVal)))) {
      // System.out.println("recur both: " + Arrays.toString(node.kdPoint));
      findKNN(node.rightChild, target, k);
      findKNN(node.leftChild, target, k);
    } else if (nodeAxisVal <= targetAxisVal) {
      //System.out.println("recur right: " + Arrays.toString(node.kdPoint));
      findKNN(node.rightChild, target, k);
    } else {
      //System.out.println("recur left: " + Arrays.toString(node.kdPoint));
      findKNN(node.leftChild, target, k);
    }
  }

  public void printKNN(int[] target, int[][] kdArray, int k) {
    buildKdTree(kdArray, 0);
    //System.out.println("=======");
    findKNN(root, target, k);
    for (int i = 0; i < k; i++) {
      // System.out.println(neighbors.size());
      int[] arr = neighbors.poll().kdPoint;
      System.out.println(Arrays.toString(arr));
    }
  }
}
