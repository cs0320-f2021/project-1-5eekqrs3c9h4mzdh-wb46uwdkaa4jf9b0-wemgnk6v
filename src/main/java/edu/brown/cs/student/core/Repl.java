package edu.brown.cs.student.core;

import com.google.gson.JsonObject;
import edu.brown.cs.student.client.ApiClient;
import edu.brown.cs.student.client.ClientRequestGenerator;
import edu.brown.cs.student.client.JSONopener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * The REPL class for this lab.
 */
public class Repl {

  /**
   * instantiates a REPL.
   *
   * // @param commands - a map of string command names to IReplMethod objects, which
   *                 are a wrapper for a method to be called
   */
  public Repl() {
  }

  /**
   * This run method for the REPL requires an ApiClient object.
   *
   * @param client
   */
  public void run(ApiClient client) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while (true) { // parsing input loop
      try {
        String s = reader.readLine();
        if (s == null) { // ctrl-D (exit) would result in null input
          break;
        }

        // initialize a StringTokenizer to help parse the input, broken by space or tabs
        StringTokenizer st = new StringTokenizer(s, " \t", false);

        if (st.hasMoreTokens()) { // if the input is not blank, get the first token (the command)
          String command = st.nextToken();

          if (command.equals("user")) {

            String json = "[{\"user_id\": \"151944\", \"weight\": \"145lbs\", \"bust_size\": \"34b\", \"height\": \"5' 9\\\"\", \"age\": \"27\", \"body_type\": \"athletic\", \"horoscope\": \"Libra\"},\n" +
                    "{\"user_id\": \"154309\", \"weight\": \"114lbs\", \"bust_size\": \"32b\", \"height\": \"5' 3\\\"\", \"age\": \"33\", \"body_type\": \"petite\", \"horoscope\": \"Scorpio\"},\n" +
                    "{\"user_id\": \"185966\", \"weight\": \"135lbs\", \"bust_size\": \"34b\", \"height\": \"5' 3\\\"\", \"age\": \"33\", \"body_type\": \"athletic\", \"horoscope\": \"Sagittarius\"},\n" +
                    "{\"user_id\": \"273551\", \"weight\": \"132lbs\", \"bust_size\": \"34b\", \"height\": \"5' 6\\\"\", \"age\": \"36\", \"body_type\": \"straight & narrow\", \"horoscope\": \"Virgo\"},\n" +
                    "{\"user_id\": \"336066\", \"weight\": \"112lbs\", \"bust_size\": \"34c\", \"height\": \"5' 3\\\"\", \"age\": \"27\", \"body_type\": \"hourglass\", \"horoscope\": \"Scorpio\"},\n" +
                    "{\"user_id\": \"391778\", \"weight\": \"142lbs\", \"bust_size\": \"36d\", \"height\": \"5' 2\\\"\", \"age\": \"29\", \"body_type\": \"apple\", \"horoscope\": \"Aries\"},\n" +
                    "{\"user_id\": \"420272\", \"weight\": \"137lbs\", \"bust_size\": \"34d\", \"height\": \"5' 8\\\"\", \"age\": \"28\", \"body_type\": \"hourglass\", \"horoscope\": \"Taurus\"},\n" +
                    "{\"user_id\": \"499943\", \"weight\": \"170lbs\", \"bust_size\": \"34c\", \"height\": \"5' 8\\\"\", \"age\": \"35\", \"body_type\": \"pear\", \"horoscope\": \"Leo\"},\n" +
                    "{\"user_id\": \"533900\", \"weight\": \"135lbs\", \"bust_size\": \"34b\", \"height\": \"5' 6\\\"\", \"age\": \"30\", \"body_type\": \"pear\", \"horoscope\": \"Aquarius\"},\n" +
                    "{\"user_id\": \"721308\", \"weight\": \"118lbs\", \"bust_size\": \"34b\", \"height\": \"5' 5\\\"\", \"age\": \"32\", \"body_type\": \"athletic\", \"horoscope\": \"Leo\"},\n" +
                    "{\"user_id\": \"734848\", \"weight\": \"138lbs\", \"bust_size\": \"32b\", \"height\": \"5' 8\\\"\", \"age\": \"45\", \"body_type\": \"athletic\", \"horoscope\": \"Pisces\"},\n" +
                    "{\"user_id\": \"829124\", \"weight\": \"140lbs\", \"bust_size\": \"34c\", \"height\": \"5' 7\\\"\", \"age\": \"30\", \"body_type\": \"hourglass\", \"horoscope\": \"Leo\"},\n" +
                    "{\"user_id\": \"86661\", \"weight\": \"118lbs\", \"bust_size\": \"34d+\", \"height\": \"5' 3\\\"\", \"age\": \"65\", \"body_type\": \"full bust\", \"horoscope\": \"Aries\"},\n" +
                    "{\"user_id\": \"87660\", \"weight\": \"120lbs\", \"bust_size\": \"36a\", \"height\": \"5' 6\\\"\", \"age\": \"26\", \"body_type\": \"straight & narrow\", \"horoscope\": \"Sagittarius\"},\n" +
                    "{\"user_id\": \"909926\", \"weight\": \"135lbs\", \"bust_size\": \"34c\", \"height\": \"5' 5\\\"\", \"age\": \"34\", \"body_type\": \"pear\", \"horoscope\": \"Libra\"}]\n";
            new JSONopener(json);
          }

//          if (command.equals("basicGet")) { // Basic GET request
//            client.makeRequest(ClientRequestGenerator.getIntroGetRequest());
//
//          } else if (command.equals("keyedGet")) { // GET request with an api key
//            client.makeRequest(ClientRequestGenerator.getSecuredGetRequest());
//
//          } else if (command.equals("keyedPost")) { // POST with an api key
//            String name;
//            if (st.hasMoreTokens()) {
//              name = st.nextToken();
//            } else {
//              name = "";
//            }
//            client.makeRequest(ClientRequestGenerator.getSecuredPostRequest(name));
//
//          } else if (command.equals("getHoroscopes")) { // GET with an api key and a string param
//            String name;
//            if (st.hasMoreTokens()) {
//              name = st.nextToken();
//            } else {
//              name = "";
//            }
//            client.makeRequest(ClientRequestGenerator.getHoroscopeGetRequest(name));

          } else { // command unrecognized
            System.out.println("ERROR: Unrecognized command.");
          }
        //}
      } catch (IOException e) { // some kind of read error, so the repl exits
        System.out.println("ERROR: Failed parsing input.");
        break;
      }
    }
    try {
      reader.close();
    } catch (IOException e) {
      System.out.println("ERROR: Failed to close reader.");
    }
  }
}
