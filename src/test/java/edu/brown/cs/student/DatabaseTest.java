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
import java.util.ArrayList;
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
    List<Map<String, List<?>>> listORMData = new ArrayList<>();
    for (int id = 1; id <= 61; id++) {
      Map<String, String> queryMap = new HashMap<>();
      queryMap.put("id", String.valueOf(id));
      Map<String, List<?>> currData = new HashMap<String, List<?>>();
      List<Skills> selectSkills = db.select(Skills.class, queryMap);
      if (selectSkills.size() > 0) {
        currData.put("skills", selectSkills.get(0).toList()); // Insert skills list
      } else currData.put("skills", new ArrayList<>());

      List<Interests> selectInterests = db.select(Interests.class, queryMap);
      List<String> currInterest = new ArrayList<>();

      List<Positive> selectPositive = db.select(Positive.class, queryMap);
      List<String> currPositive = new ArrayList<>();


      List<Negative> selectNegative = db.select(Negative.class, queryMap);
      List<String> currNegative = new ArrayList<>();

      if (selectInterests.size() > 0) {
        for (Interests interest : selectInterests) {
          currInterest.add(interest.getInterest());
        }
      }
      currData.put("interests", currInterest); // Insert Interests list

      if (selectPositive.size() > 0) {
        for (Positive pos : selectPositive) {
          currPositive.add(pos.getTrait());
        }
      }
      currData.put("pos", currPositive); // Insert positive list

      if (selectPositive.size() > 0) {
        for (Negative neg : selectNegative) {
          currNegative.add(neg.getTrait());
        }
      }
      currData.put("neg", currNegative); // Insert negative list

      listORMData.add(currData);
    }
  }
}
