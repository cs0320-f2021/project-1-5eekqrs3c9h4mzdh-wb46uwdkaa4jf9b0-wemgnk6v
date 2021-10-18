package edu.brown.cs.student;

import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.assertEquals;
import edu.brown.cs.student.GroupMaker;
import edu.brown.cs.student.client.Student;
import org.junit.Test;

public class GroupMakerTest {

  Integer a = 1;
  Integer b = 2;
  Integer c = 3;
  Integer d = 4;
  Integer e = 5;
  Integer f = 6;
  Integer g = 7;
  Integer h = 8;
  Integer i = 9;
  Integer j = 10;
  Integer k = 11;
  Integer l = 12;

 // List<Student> students = List.of(a, b, c, d, e, f, g, h, i, j, k, l);

  @Test
  public void makeGroups1Test() {

    HashMap<Integer, List<Integer>> students = new HashMap<>();
    students.put(a, List.of(a, b, c, d, e, f, g, h, i, j, k, l));
    students.put(b, List.of(b, c, d, e, f, g, h, i, j, k, l, a));
    students.put(c, List.of(c, d, e, f, g, h, i, j, k, l, a, b));
    students.put(d, List.of(d, e, f, g, h, i, j, k, l, a, b, c));
    students.put(e, List.of(e, f, g, h, i, j, k, l, a, b, c, d));
    students.put(f, List.of(f, g, h, i, j, k, l, a, b, c, d, e));
    students.put(g, List.of(g, h, i, j, k, l, a, b, c, d, e, f));
    students.put(h, List.of(h, i, j, k, l, a, b, c, d, e, f, g));
    students.put(i, List.of(i, j, k, l, a, b, c, d, e, f, g, h));
    students.put(j, List.of(j, k, l, a, b, c, d, e, f, g, h, i));
    students.put(k, List.of(k, l, a, b, c, d, e, f, g, h, i, j));
    students.put(l, List.of(l, a, b, c, d, e, f, g, h, i, j, k));

    GroupMaker groupMaker = new GroupMaker(3, students);

    List<Integer> g1 = List.of(a, b, c);
    List<Integer> g2 = List.of(d, e, f);
    List<Integer> g3 = List.of(g, h, i);
    List<Integer> g4 = List.of(j, k, l);

    List<List<Integer>> compare = List.of(g1, g2, g3, g4);

    List<List<Integer>> groups = groupMaker.makeGroups();

    assertEquals(compare, groups);
  }

//  @Test
//  public void makeGroups2Test() {
//    a.setNeighbors(List.of(a, b, c, d, e, f, g, h, i, j, k, l));
//    b.setNeighbors(List.of(b, a, c, d, e, f, g, h, i, j, k, l));
//    c.setNeighbors(List.of(c, a, b, d, e, f, g, h, i, j, k, l));
//    d.setNeighbors(List.of(d, a, b, c, e, f, g, h, i, j, k, l));
//    e.setNeighbors(List.of(e, a, b, c, d, f, g, h, i, j, k, l));
//    f.setNeighbors(List.of(f, a, b, c, d, e, g, h, i, j, k, l));
//    g.setNeighbors(List.of(g, a, b, c, d, e, f, h, i, j, k, l));
//    h.setNeighbors(List.of(h, a, b, c, d, e, f, g, i, j, k, l));
//    i.setNeighbors(List.of(i, a, b, c, d, e, f, g, h, j, k, l));
//    j.setNeighbors(List.of(j, a, b, c, d, e, f, g, h, i, k, l));
//    k.setNeighbors(List.of(k, a, b, c, d, e, f, g, h, i, j, l));
//    l.setNeighbors(List.of(l, a, b, c, d, e, f, g, h, i, j, k));
//
//    GroupMaker groupMaker = new GroupMaker(3, students);
//
//    List<Student> g1 = List.of(a, b, c);
//    List<Student> g2 = List.of(d, e, f);
//    List<Student> g3 = List.of(g, h, i);
//    List<Student> g4 = List.of(j, k, l);
//
//    List<List<Student>> compare = List.of(g1, g2, g3, g4);
//
//    List<List<Student>> groups = groupMaker.makeGroups();
//
//    assertEquals(compare, groups);
//  }
//
//  @Test
//  public void makeGroups3Test() {
//    a.setNeighbors(List.of(a, j, i, g, f, b, l, e, d, c, h, k));
//    b.setNeighbors(List.of(b, g, i, d, k, h, f, j, c, a, l, e));
//    c.setNeighbors(List.of(c, j, h, k, b, g, l, a, f, d, e, i));
//    d.setNeighbors(List.of(d, a, c, b, k, j, i, l, f, e, h, g));
//    e.setNeighbors(List.of(e, a, d, i, f, l, h, j, b, k, c, g));
//    f.setNeighbors(List.of(f, h, i, l, a, e, k, c, j, b, d, g));
//    g.setNeighbors(List.of(g, d, c, l, b, k, h, f, e, a, j, i));
//    h.setNeighbors(List.of(h, d, c, a, i, l, j, b, g, e, k, f));
//    i.setNeighbors(List.of(i, b, l, j, d, f, e, a, h, k, c, g));
//    j.setNeighbors(List.of(j, k, d, h, f, c, g, e, b, l, i, a));
//    k.setNeighbors(List.of(k, f, i, b, e, g, d, j, l, c, a, h));
//    l.setNeighbors(List.of(l, f, j, a, k, g, h, c, i, e, b, d));
//
//    GroupMaker groupMaker = new GroupMaker(3, students);
//
//    List<Student> g1 = List.of(a, j, i);
//    List<Student> g2 = List.of(b, g, d);
//    List<Student> g3 = List.of(c, h, k);
//    List<Student> g4 = List.of(e, f, l);
//
//    List<List<Student>> compare = List.of(g1, g2, g3, g4);
//
//    List<List<Student>> groups = groupMaker.makeGroups();
//
//    assertEquals(compare, groups);
//  }
}