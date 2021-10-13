package edu.brown.cs.student.bloomfilter;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Map;

public class AndSimilarityComparator
    implements Comparator<Map.Entry<String, BloomFilter<String>>>, Serializable {
  private BloomFilter<String> target;

  public AndSimilarityComparator(BloomFilter<String> target) {
    this.target = target;
  }

  @Override
  public int compare(Map.Entry<String, BloomFilter<String>> o1,
                     Map.Entry<String, BloomFilter<String>> o2) {
    BitSet o1BitSet = o1.getValue().getBitSet();
    BitSet o2BitSet = o2.getValue().getBitSet();

    // AND the bitsets together, compare based on the number of 1s set
    o1BitSet.and(this.target.getBitSet());
    int o1AndTargetCardinality = o1BitSet.cardinality();

    o2BitSet.and(this.target.getBitSet());
    int o2AndTargetCardinality = o2BitSet.cardinality();

    return o2AndTargetCardinality - o1AndTargetCardinality;
  }
}
