package edu.brown.cs.student.recommender;

import edu.brown.cs.student.bloomfilter.BloomFilterRecommender;
import edu.brown.cs.student.client.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IntegratedRecommender<T extends Item> {
  // <T extends Item>
  // change User to student
  private List<User> studentList = null;
  private BloomFilterRecommender bloomFilterRecommender = null;

  public IntegratedRecommender(HashMap<Integer, T> studentMap) {
    this.studentList = studentList;
    // Convert student IDs to KD tree coordinates and Bloom filter coordinates
    this.bloomFilterRecommender = new BloomFilterRecommender(studentMap, 0.0005);
  }

  public List<User> getTopKRecommendations(User targetItem, int k) {
    List<T> bloomRecs = this.bloomFilterRecommender.getTopKRecommendations(targetItem, k);
    List<T> bloomRecs2 = this.bloomFilterRecommender.getTopKRecommendations(targetItem, k);
    List<T> mergedRecs = this.mergeRecommendationLists(bloomRecs, bloomRecs2, k);
    return bloomRecs;
  }

  private List<User> mergeRecommendationLists(List<User> bloomFilterRecs, List<User> kdTreeRecs,
                                              int k) {
    List<User> recList = new ArrayList<User>();
    for (int i = 0; i < k; i++) {
      User bloomRec = bloomFilterRecs.get(i);
      if (!recList.contains(bloomRec) && recList.size() < k) {
        recList.add(bloomRec);
      }
      User kdRec = kdTreeRecs.get(i);
      if (!recList.contains(kdRec) && recList.size() < k) {
        recList.add(kdRec);
      }
      if (recList.size() >= k) {
        return recList;
      }
    }
    return recList;
  }
}
