package edu.brown.cs.student.recommender;

import edu.brown.cs.student.bloomfilter.BloomFilterRecommender;
import edu.brown.cs.student.client.User;
import edu.brown.cs.student.kdtree.KdTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IntegratedRecommender<T extends Item> {
  // <T extends Item>
  // change User to student
  private List<User> studentList = null;
  private BloomFilterRecommender bloomFilterRecommender = null;
  private KdTree kdTree = null;

  public IntegratedRecommender(HashMap<Integer, T> studentMap) {
    this.studentList = studentList;
    // Convert student IDs to KD tree coordinates and Bloom filter coordinates
    this.bloomFilterRecommender = new BloomFilterRecommender(studentMap, 0.0005);
    // get number of KDtree dimensions
    this.kdTree = new KdTree(5, studentMap.values().toArray());
  }

  public List<Integer> getTopKRecommendationIDs(User targetItem, int k) {
    List<T> bloomRecs = this.bloomFilterRecommender.getTopKRecommendations(targetItem, k);
    int[] kdTreeRecIDs = this.kdTree.getArrayOfKnnIds(targetItem.getKdPoint(), k);
    List<Integer> mergedRecs = this.mergeRecommendationLists(bloomRecs, kdTreeRecIDs, k);
    return mergedRecs;
  }

  private List<Integer> mergeRecommendationLists(List<User> bloomFilterRecs, int[] kdTreeRecs,
                                              int k) {
    List<Integer> recList = new ArrayList<Integer>();
    // add protection for when k > # students
    for (int i = 0; i < k; i++) {
      User bloomRec = bloomFilterRecs.get(i);
      if (!recList.contains(bloomRec) && recList.size() < k) {
        recList.add(bloomRec.getElementID());
      }
      int kdRecID = kdTreeRecs[i];
      if (!recList.contains(kdRecID) && recList.size() < k) {
        recList.add(kdRecID);
      }
      if (recList.size() >= k) {
        return recList;
      }
    }
    return recList;
  }
}
