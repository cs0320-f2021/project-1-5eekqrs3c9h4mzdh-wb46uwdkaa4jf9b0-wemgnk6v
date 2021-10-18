package edu.brown.cs.student;

import edu.brown.cs.student.client.User;
import edu.brown.cs.student.kdtree.KdElement;
import edu.brown.cs.student.kdtree.KdTree;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.HashMap;

public class KdTreeTest {

  @Test
  public void getArrayTest() {
//    Double[] threeD = {0.0, 0.0, 0.0};
//    Double[] threeD2 = {98.0, 65.0, 19.0};
//    User user0 = new User(100, 0.0, "32B", 0.0, 0.0, "type0", "Gemini");
//    User user1 = new User(101, 50.0, "32B", 62.0, 20.0, "type1", "Gemini");
//    KdElement[] kdArrayEmpty = {};
//    KdElement[] kdArraySimple = {user0, user1};
//    KdTree tree1 = new KdTree(3, kdArraySimple);
//    assertEquals(100, tree1.getArrayOfKnnIds(threeD, 1)[0], 0.01);
//    assertEquals(101, tree1.getArrayOfKnnIds(threeD, 2)[1], 0.01);
//    assertEquals(101, tree1.getArrayOfKnnIds(threeD2, 1)[0], 0.01);
//    KdTree tree2 = new KdTree(3, kdArrayEmpty);
//    assertEquals(0, tree2.getArrayOfKnnIds(threeD2, 1).length, 0.01); // Empty Case
  }

  @Test
  public void classifyUsersTest() {
//    Double[] threeD = {0.0, 0.0, 0.0};
//    Double[] threeD2 = {98.0, 65.0, 19.0};
//    User user0 = new User(100, 0.0, "32B", 0.0, 0.0, "type0", "Gemini");
//    User user1 = new User(101, 50.0, "32B", 62.0, 20.0, "type1", "Gemini");
//    KdElement[] kdArrayEmpty = {};
//    HashMap<Integer, User> userToID = new HashMap<>()
//    {{
//      put(100, user0);
//      put(101, user1);
//    }};
//    KdElement[] kdArraySimple = {user0, user1};
//    KdTree tree1 = new KdTree(3, kdArraySimple);
//    tree1.classifyUsers(threeD2, 1, userToID);
  }
}
