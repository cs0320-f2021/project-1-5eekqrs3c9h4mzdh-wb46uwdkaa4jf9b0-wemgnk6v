package edu.brown.cs.student.bloomfilter;

import edu.brown.cs.student.recommender.Item;
import edu.brown.cs.student.recommender.Recommender;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class BloomFilterRecommender<T extends Item> implements Recommender<T> {
  private HashMap<String, T> items;
  private HashMap<String, BloomFilter<String>> bloomFilters;
  private double desiredFalsePositiveRate;
  private int maxNumValues = 0;

  // comparator to use when comparing similarity of bloom filters
  private Comparator<Map.Entry<String, BloomFilter<String>>> bloomFilterComparator;

  public BloomFilterRecommender(HashMap<String, T> items, double desiredFalsePositiveRate) {
    this.items = items;
    this.desiredFalsePositiveRate = desiredFalsePositiveRate;

    // find the maximum number of attributes for a given item
    for (T value : this.items.values()) {
      int currSize = value.getVectorRepresentation().size();
      if (currSize > this.maxNumValues) {
        this.maxNumValues = currSize;
      }
    }

    // construct a bloom filter for each item containing the attributes of that item
    this.bloomFilters = new HashMap<>();
    for (Map.Entry<String, T> entry : this.items.entrySet()) {
      BloomFilter<String> currFilter = new BloomFilter<>(desiredFalsePositiveRate, this.maxNumValues);
      List<String> itemVector = entry.getValue().getVectorRepresentation();
      for (String s : itemVector) {
        currFilter.add(s);
      }

      this.bloomFilters.put(entry.getKey(), currFilter);
    }
  }


  @Override
  public List<T> getTopKRecommendations(T item, int k) {
    if (this.bloomFilterComparator == null) {
      return null;
    }

    BloomFilter<String> inputFilter = new BloomFilter<>(desiredFalsePositiveRate, this.maxNumValues);
    List<String> inputItemVector = item.getVectorRepresentation();
    for (String s : inputItemVector) {
      inputFilter.add(s);
    }

    return this.bloomFilters.entrySet()
            .stream()
            .sorted(this.bloomFilterComparator)
            .filter(entry -> !entry.getKey().equals(item.getId()))
            .limit(k)
            .map(entry -> this.items.get(entry.getKey()))
            .collect(toList());
  }


  public void setBloomFilterComparator(
      Comparator<Map.Entry<String, BloomFilter<String>>> bloomFilterComparator) {
    this.bloomFilterComparator = bloomFilterComparator;
  }

  public HashMap<String, T> getItems() {
    return this.items;
  }

  public double getDesiredFalsePositiveRate() {
    return this.desiredFalsePositiveRate;
  }

  public int getMaxNumValues() {
    return this.maxNumValues;
  }
}
