package edu.brown.cs.student.kdtree;

import edu.brown.cs.student.client.User;
import edu.brown.cs.student.recommender.Item;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class KdTree {
  private Node root;
  private int numDimensions;
  private PriorityQueue<Neighbor> neighbors = new PriorityQueue<Neighbor>();
  private int len;

  public KdTree(int numDimensions, Item[] kdArray) {
    this.root = null;
    this.numDimensions = numDimensions;
    this.len = kdArray.length;
    // build KD tree
    buildKdTree(kdArray, 0);
  }

  private class Node {
    private Double[] kdPoint;
    private Node leftChild;
    private Node rightChild;
    private int axis;
    private int elementID;

    Node(Double[] kdPoint, int axis, int elementID) {
      // kdPoint is an array of k doubles
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
      // sort priority queue in descending order
      return Double.compare(neighbor.distance, this.distance);
    }
  }

  private Node buildKdTree(Item[] kdArray, int depth) {
    int arrayLen = kdArray.length;
    // if array is empty, stop building
    if (arrayLen == 0) {
      return null;
    }

    // Select axis
    int axis = depth % numDimensions;

    // Sort array of KD points by values in that axis
    Arrays.sort(kdArray, Comparator.comparingDouble(o -> o.getKdPoint()[axis]));

    // values that are greater than or equal to the median element are placed on the right side
    int middleNum = arrayLen / 2;
    int i = middleNum - 1;
    while ((i >= 0) && (kdArray[middleNum].getKdPoint()[axis] == kdArray[i].getKdPoint()[axis])) {
      middleNum = i;
      i = i - 1;
    }
    Item middleElement = kdArray[middleNum];

    Node newNode = new Node(middleElement.getKdPoint(), axis, middleElement.getElementID());
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
//      System.out.println("==null node==");
      return;
    }
//    System.out.println("ID: " + node.elementID);
//    System.out.println("KD Point: " + Arrays.toString(node.kdPoint));
//    System.out.println("Right Child: " + node.rightChild);
//    System.out.println("Left Child: " + node.leftChild);

    double distance = node.calcDistance(target);

    if (neighbors.size() < k) {
      Neighbor newNeighbor = new Neighbor(node.kdPoint, distance, node.elementID);
      neighbors.add(newNeighbor);
    } else if (distance < neighbors.peek().distance) {
      Neighbor newNeighbor = new Neighbor(node.kdPoint, distance, node.elementID);
      neighbors.poll();
      neighbors.add(newNeighbor);
    }

    int axis = node.axis;
    Double targetAxisVal = target[axis];
    Double nodeAxisVal = node.kdPoint[axis];

    if ((neighbors.size() < k)
        || (neighbors.peek().distance > (Math.abs(targetAxisVal - nodeAxisVal)))) {
      // System.out.println("recurring on both");
      findKNN(node.rightChild, target, k);
      findKNN(node.leftChild, target, k);
    } else if (nodeAxisVal <= targetAxisVal) {
      //System.out.println("recurring on right");
      findKNN(node.rightChild, target, k);
    } else {
      //System.out.println("recurring on left");
      findKNN(node.leftChild, target, k);
    }
  }

  public int[] getArrayOfKnnIds(Double[] target, int k) {

    int numNeighbors = Math.min(this.len, k);

    // search KD tree
    findKNN(root, target, k);

    // create an array in reverse order of priority queue (to print in order of nearest to farthest)
    int[] orderedNeighbors = new int[numNeighbors];
//    System.out.println("=== reverse order neighbors: ===");
    if (neighbors.size() > 0) {
      for (int i = 0; i < numNeighbors; i++) {
        // assumes neighbors.size() == k
        Neighbor neigh = neighbors.poll();
//        int id = neighbors.poll().elementID;
        int id = neigh.elementID;
//        System.out.println("ID: " + id);
//        System.out.println("Distance: " + neigh.distance);
        orderedNeighbors[numNeighbors - 1 - i] = id;
      }
      return orderedNeighbors;
    } else {
      return new int[0];
    }
  }

  public void classifyUsers(Double[] target, int k,
                            HashMap<Integer, User> mapIDtoUser) {
    HashMap<String, Integer> horoscopeCount = new HashMap<>() {{
      put("Aries", 0);
      put("Taurus", 0);
      put("Gemini", 0);
      put("Cancer", 0);
      put("Leo", 0);
      put("Virgo", 0);
      put("Libra", 0);
      put("Scorpio", 0);
      put("Sagittarius", 0);
      put("Capricorn", 0);
      put("Aquarius", 0);
      put("Pisces", 0);
    }};
    int[] similarID = getArrayOfKnnIds(target, k);
    for (int ID : similarID) {
      String horoscope = mapIDtoUser.get(ID).getHoroscope();
      if (horoscopeCount.containsKey(horoscope)) {
        horoscopeCount.put(horoscope, horoscopeCount.get(horoscope) + 1);
      }
    }
    for (String horoscopeKey : horoscopeCount.keySet()) {
      int count = horoscopeCount.get(horoscopeKey);
      System.out.println(horoscopeKey + ": " + count);
    }
  }
}
