package edu.brown.cs.student.bloomfilter;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Map;

public class XnorSimilarityComparator implements Comparator<Map.Entry<String, BloomFilter<String>>>,
    Serializable {
  private BloomFilter<String> target;

  public XnorSimilarityComparator(BloomFilter<String> target) {
    this.target = target;
  }

  @Override
  public int compare(Map.Entry<String, BloomFilter<String>> o1,
                     Map.Entry<String, BloomFilter<String>> o2) {
    BitSet o1BitSet = o1.getValue().getBitSet();
    BitSet o2BitSet = o2.getValue().getBitSet();

    // XNOR the bitsets together, compare based on the number of 1s set
    // System.out.println((int) (xnorSimilarity(o2BitSet) - xnorSimilarity(o1BitSet)));
    return (int) (xnorSimilarity(o2BitSet) - xnorSimilarity(o1BitSet));
  }

  private double xnorSimilarity(BitSet b1) {
    BitSet targetBitSet = this.target.getBitSet();
    BitSet result = new BitSet();
    result.or(b1); // Copies b1
    result.or(targetBitSet); // Makes the OR
    result.flip(0,result.size()); // Negates - the result is a NOR
    result.clear(Math.min(b1.size(), targetBitSet.size()), result.size()); // Only retain the common size
    return result.cardinality();
  }
}
