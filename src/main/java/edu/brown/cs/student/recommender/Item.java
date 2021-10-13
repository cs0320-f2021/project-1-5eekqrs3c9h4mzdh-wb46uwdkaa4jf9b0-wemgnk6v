package edu.brown.cs.student.recommender;

import java.util.List;

public interface Item {
  List<String> getVectorRepresentation();
  String getId();
}
