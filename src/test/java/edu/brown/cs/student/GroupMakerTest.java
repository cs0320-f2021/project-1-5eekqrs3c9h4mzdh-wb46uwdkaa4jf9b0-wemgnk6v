package edu.brown.cs.student;

import edu.brown.cs.student.core.Student;
import edu.brown.cs.student.core.GroupMaker;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class GroupMakerTest {

  Student a = new Student("A");
  Student b = new Student("B");
  Student c = new Student("C");
  Student d = new Student("D");
  Student e = new Student("E");
  Student f = new Student("F");
  Student g = new Student("G");
  Student h = new Student("H");
  Student i = new Student("I");
  Student j = new Student("J");
  Student k = new Student("K");
  Student l = new Student("L");

  List<Student> students = List.of(a, b, c, d, e, f, g, h, i, j, k, l);

  @Test
  public void makeGroups1Test() {
    a.neighbors = List.of(a, b, c, d, e, f, g, h, i, j, k, l);
    b.neighbors = List.of(b, c, d, e, f, g, h, i, j, k, l, a);
    c.neighbors = List.of(c, d, e, f, g, h, i, j, k, l, a, b);
    d.neighbors = List.of(d, e, f, g, h, i, j, k, l, a, b, c);
    e.neighbors = List.of(e, f, g, h, i, j, k, l, a, b, c, d);
    f.neighbors = List.of(f, g, h, i, j, k, l, a, b, c, d, e);
    g.neighbors = List.of(g, h, i, j, k, l, a, b, c, d, e, f);
    h.neighbors = List.of(h, i, j, k, l, a, b, c, d, e, f, g);
    i.neighbors = List.of(i, j, k, l, a, b, c, d, e, f, g, h);
    j.neighbors = List.of(j, k, l, a, b, c, d, e, f, g, h, i);
    k.neighbors = List.of(k, l, a, b, c, d, e, f, g, h, i, j);
    l.neighbors = List.of(l, a, b, c, d, e, f, g, h, i, j, k);

    GroupMaker groupMaker = new GroupMaker(3, students);

    List<Student> g1 = List.of(a, b, c);
    List<Student> g2 = List.of(d, e, f);
    List<Student> g3 = List.of(g, h, i);
    List<Student> g4 = List.of(j, k, l);

    List<List<Student>> compare = List.of(g1, g2, g3, g4);

    List<List<Student>> groups = groupMaker.makeGroups();

    assertEquals(compare, groups);
  }

  @Test
  public void makeGroups2Test() {
    a.neighbors = List.of(a, b, c, d, e, f, g, h, i, j, k, l);
    b.neighbors = List.of(b, a, c, d, e, f, g, h, i, j, k, l);
    c.neighbors = List.of(c, a, b, d, e, f, g, h, i, j, k, l);
    d.neighbors = List.of(d, a, b, c, e, f, g, h, i, j, k, l);
    e.neighbors = List.of(e, a, b, c, d, f, g, h, i, j, k, l);
    f.neighbors = List.of(f, a, b, c, d, e, g, h, i, j, k, l);
    g.neighbors = List.of(g, a, b, c, d, e, f, h, i, j, k, l);
    h.neighbors = List.of(h, a, b, c, d, e, f, g, i, j, k, l);
    i.neighbors = List.of(i, a, b, c, d, e, f, g, h, j, k, l);
    j.neighbors = List.of(j, a, b, c, d, e, f, g, h, i, k, l);
    k.neighbors = List.of(k, a, b, c, d, e, f, g, h, i, j, l);
    l.neighbors = List.of(l, a, b, c, d, e, f, g, h, i, j, k);

    GroupMaker groupMaker = new GroupMaker(3, students);

    List<Student> g1 = List.of(a, b, c);
    List<Student> g2 = List.of(d, e, f);
    List<Student> g3 = List.of(g, h, i);
    List<Student> g4 = List.of(j, k, l);

    List<List<Student>> compare = List.of(g1, g2, g3, g4);

    List<List<Student>> groups = groupMaker.makeGroups();

    assertEquals(compare, groups);
  }

  @Test
  public void makeGroups3Test() {
    a.neighbors = List.of(a, j, i, g, f, b, l, e, d, c, h, k);
    b.neighbors = List.of(b, g, i, d, k, h, f, j, c, a, l, e);
    c.neighbors = List.of(c, j, h, k, b, g, l, a, f, d, e, i);
    d.neighbors = List.of(d, a, c, b, k, j, i, l, f, e, h, g);
    e.neighbors = List.of(e, a, d, i, f, l, h, j, b, k, c, g);
    f.neighbors = List.of(f, h, i, l, a, e, k, c, j, b, d, g);
    g.neighbors = List.of(g, d, c, l, b, k, h, f, e, a, j, i);
    h.neighbors = List.of(h, d, c, a, i, l, j, b, g, e, k, f);
    i.neighbors = List.of(i, b, l, j, d, f, e, a, h, k, c, g);
    j.neighbors = List.of(j, k, d, h, f, c, g, e, b, l, i, a);
    k.neighbors = List.of(k, f, i, b, e, g, d, j, l, c, a, h);
    l.neighbors = List.of(l, f, j, a, k, g, h, c, i, e, b, d);

    GroupMaker groupMaker = new GroupMaker(3, students);

    List<Student> g1 = List.of(a, j, i);
    List<Student> g2 = List.of(b, g, d);
    List<Student> g3 = List.of(c, h, k);
    List<Student> g4 = List.of(e, f, l);

    List<List<Student>> compare = List.of(g1, g2, g3, g4);

    List<List<Student>> groups = groupMaker.makeGroups();

    assertEquals(compare, groups);
  }
}