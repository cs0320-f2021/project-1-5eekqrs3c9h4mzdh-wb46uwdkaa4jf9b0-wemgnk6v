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
    private Double[] kdPoint;
    private Node leftChild;
    private Node rightChild;
    private int axis;
    private int elementID;

    Node(Double[] kdPoint, int axis, int elementID) {
      // kdPoint is an array of length k
      this.kdPoint = kdPoint;
      this.leftChild = null;
      this.rightChild = null;
      this.axis = axis;
      this.elementID = elementID;
    }

    public double calcDistance(Double[] target) {
      double sum = 0;
      for (int i = 0; i < numDimensions; i++) {
        sum = sum + Math.pow((target[i] - kdPoint[i]), 2);
      }
      return Math.sqrt(sum);
    }
  }

  private class Neighbor implements Comparable<Neighbor> {
    private Double[] kdPoint;
    private double distance;
    private int elementID;

    Neighbor(Double[] kdPoint, double distance, int elementID) {
      this.kdPoint = kdPoint;
      this.distance = distance;
      this.elementID = elementID;
    }

    @Override
    public int compareTo(Neighbor neighbor) {
      // sort in descending order
      return Double.compare(neighbor.distance, this.distance);
    }
  }

  private Node buildKdTree(KdElement[] kdArray, int depth) {
    int arrayLen = kdArray.length;
    if (arrayLen == 0) {
      return null;
    }
    // if array is empty return null
    // if the array is 1 just return that array turned into a node

    // Select axis
    int axis = depth % numDimensions;

    // Sort array of KD points by axis
    Arrays.sort(kdArray, Comparator.comparingDouble(o -> o.getKdPoint()[axis]));

    // values that are greater than or equal to the median element are on the right side
    int middleNum = arrayLen / 2;
    int i = middleNum - 1;
    while ((i >= 0) && (kdArray[middleNum].getKdPoint()[axis] == kdArray[i].getKdPoint()[axis])) {
      middleNum = i;
      i = i - 1;
    }
    KdElement middleElement = kdArray[middleNum];

    Node newNode = new Node(middleElement.getKdPoint(), axis, middleElement.getElementID());
    // System.out.println(Arrays.toString(newNode.kdPoint));
    if (root == null) {
      this.root = newNode;
    }

    newNode.leftChild = buildKdTree(Arrays.copyOfRange(kdArray, 0, middleNum), depth + 1);
    newNode.rightChild =
        buildKdTree(Arrays.copyOfRange(kdArray, middleNum + 1, arrayLen), depth + 1);
    return newNode;
  }

  private void findKNN(Node node, Double[] target, int k) {
    if (node == null) {
      // System.out.println("null if");
      return;
    }

    double distance = node.calcDistance(target);

    if (neighbors.size() < k) {
      // System.out.println("neighbors.size() < k: " + Arrays.toString(node.kdPoint));
      Neighbor newNeighbor = new Neighbor(node.kdPoint, distance, node.elementID);
      neighbors.add(newNeighbor);
//      findKNN(node.rightChild, target, k);
//      findKNN(node.leftChild, target, k);
    } else if (distance < neighbors.peek().distance) {
      // System.out.println("distance<neighbors.peek().distance:" + Arrays.toString(node.kdPoint));
//      System.out.println("leftchild: " + node.leftChild); IS NULL!!
      Neighbor newNeighbor = new Neighbor(node.kdPoint, distance, node.elementID);
      neighbors.poll();
      neighbors.add(newNeighbor);
    }

    int axis = node.axis;
//    System.out.println("axis:" + axis);
    Double targetAxisVal = target[axis];
//    System.out.println("targetAxisVal:" + targetAxisVal);
    Double nodeAxisVal = node.kdPoint[axis];
//    System.out.println("nodeAxisVal:" + nodeAxisVal);
//    System.out.println("neighbors.peek().distance:" + neighbors.peek().distance);

    if ((neighbors.size() < k)
        || (neighbors.peek().distance > (Math.abs(targetAxisVal - nodeAxisVal)))) {
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

  public void printKNN(Double[] target, KdElement[] kdArray, int k) {
    buildKdTree(kdArray, 0);
    //System.out.println("=======");
    findKNN(root, target, k);
    for (int i = 0; i < k; i++) {
      // System.out.println(neighbors.size());
      Double[] arr = neighbors.poll().kdPoint;
      System.out.println(Arrays.toString(arr));
    }
  }
}
