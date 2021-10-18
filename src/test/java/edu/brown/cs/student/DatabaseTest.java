package edu.brown.cs.student;

import edu.brown.cs.student.orm.Database;
import edu.brown.cs.student.orm.Skills;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseTest {

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
    List<Skills> selection = db.select(Skills.class, query);
    System.out.println(selection.get(0));
  }
}
