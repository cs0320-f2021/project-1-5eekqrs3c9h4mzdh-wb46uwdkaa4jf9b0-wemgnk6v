package edu.brown.cs.student;

import edu.brown.cs.student.client.Student;
import edu.brown.cs.student.orm.Database;
import edu.brown.cs.student.orm.Interests;
import edu.brown.cs.student.orm.Negative;
import edu.brown.cs.student.orm.Positive;
import edu.brown.cs.student.orm.Skills;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseTest {

  /*
  This test reads in the database, then writes the selection to a list of the datatype. Try running
  it to print out the first result in each list.
   */
  @Test
  public void skillsTest() throws SQLException, InvocationTargetException, InstantiationException,
      IllegalAccessException, NoSuchMethodException {
    String path = "./data/integration.sqlite3";
    Database db = null;
    try {
      db = new Database(path);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    Map<String, String> query = new HashMap<>();
    query.put("id", "2");
//    List<Skills> selectSkills = db.select(Skills.class, query);
//    System.out.println(selectSkills.get(0));
//    List<Interests> selectInterests = db.select(Interests.class, query);
//    System.out.println(selectInterests.get(0));
//    List<Positive> selectPositive = db.select(Positive.class, query);
//    System.out.println(selectPositive.get(0));
//    List<Negative> selectNegative = db.select(Negative.class, query);
//    System.out.println(selectNegative.get(0));
    Map<Integer, Student> idsToStudents = new HashMap<>();
    for (int id = 0; id <= 61; id++) {
      Map<String, String> queryMap = new HashMap<>();
      queryMap.put("id", String.valueOf(id));
      Student currStudent = new Student();
      List<Skills> selectSkills = db.select(Skills.class, queryMap);
      List<Interests> selectInterests = db.select(Interests.class, query);
      List<Positive> selectPositive = db.select(Positive.class, query);
      List<Negative> selectNegative = db.select(Negative.class, query);
    }
  }
}
