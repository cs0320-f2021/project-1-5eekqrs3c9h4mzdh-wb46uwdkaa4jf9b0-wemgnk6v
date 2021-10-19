package edu.brown.cs.student.client;

import edu.brown.cs.student.orm.Database;
import edu.brown.cs.student.orm.Interests;
import edu.brown.cs.student.orm.Negative;
import edu.brown.cs.student.orm.Positive;
import edu.brown.cs.student.orm.Skills;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntegrationORMopener {

  private final HashMap<Integer, Student> studentHashMap;

  public IntegrationORMopener(HashMap<Integer, Student> studentHashMap) {
    this.studentHashMap = studentHashMap;
  }

  public HashMap<Integer, Student> addORMData()
      throws SQLException, InvocationTargetException, InstantiationException,
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
    for (int id = 1; id <= 61; id++) {
      Student currStudent = this.studentHashMap.get(id);
      Map<String, String> queryMap = new HashMap<>();
      queryMap.put("id", String.valueOf(id));
      List<Skills> selectSkills = db.select(Skills.class, queryMap);

      if (selectSkills.size() > 0) {
        currStudent.setSkills(selectSkills.get(0).toList()); // Insert Skills List
      } else currStudent.setSkills(new ArrayList<>());

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
      currStudent.setInterests(currInterest); // Insert Interests list

      if (selectPositive.size() > 0) {
        for (Positive pos : selectPositive) {
          currPositive.add(pos.getTrait());
        }
      }
      currStudent.setPos(currPositive); // Insert positive list

      if (selectPositive.size() > 0) {
        for (Negative neg : selectNegative) {
          currNegative.add(neg.getTrait());
        }
      }
      currStudent.setNeg(currNegative); // Insert negative list
    }
    return this.studentHashMap;
  }
}
