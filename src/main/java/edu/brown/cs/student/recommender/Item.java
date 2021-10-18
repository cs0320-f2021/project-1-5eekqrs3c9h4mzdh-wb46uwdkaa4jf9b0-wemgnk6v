package edu.brown.cs.student.recommender;

import java.util.List;

public interface Item {
  // bloom filter
  List<String> getVectorRepresentation();
  int getId();

  // kdTree
  Double[] getKdPoint();
  int getElementID(); // i know this is repetitive but i don't want to change all the kdTree code now and we can clean up later
}
