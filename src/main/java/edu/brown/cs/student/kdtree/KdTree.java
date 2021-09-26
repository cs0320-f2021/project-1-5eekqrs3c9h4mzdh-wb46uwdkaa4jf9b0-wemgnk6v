package edu.brown.cs.student.kdtree;

import java.util.Arrays;
import java.util.Comparator;

public class KdTree {
  // "However, to make this data structure extensible and reusable,
  // you should write an abstraction, using the principles above,
  // that can handle any number of dimensions."


  // To do in another class?
  // - convert User list into array of 3d arrays (to3dPoint)
  // - call buildKdTree with the array of 3d points
  // - edit code to have some way to keep track of User IDs because they'll be necessary for printing results of KNN
  private Node root;
  private int numDimensions;

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
      this.kdPoint = kdPoint;
      this.leftChild = null;
      this.rightChild = null;
      this.axis = axis;
      // root = buildKDTree(array, dimension); ??
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
    if (root == null) {
      this.root = newNode;
    }

    newNode.rightChild = buildKdTree(Arrays.copyOfRange(kdArray, 0, middleNum), depth + 1);
    newNode.leftChild =
        buildKdTree(Arrays.copyOfRange(kdArray, middleNum + 1, arrayLen), depth + 1);
    return newNode;
  }
}
