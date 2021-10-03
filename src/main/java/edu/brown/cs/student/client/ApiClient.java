package edu.brown.cs.student.client;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * This class encapsulates the client request handling logic. It is agnostic of what kinds of requests are being made.
 * The exact request formatting is outsourced to ClientRequestGenerator.
 */
public class ApiClient {

  // Field to store the HttpClient
  private final HttpClient client;

  // Constructor for the ApiClient
  public ApiClient() {

    this.client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(60))
            .build();
  }

  // Method that makes a request to the API and returns a string in the form
  //   of a Json
  public String makeRequest(HttpRequest req) {

    try {
      HttpResponse<String> apiResponse = client.send(req, HttpResponse.BodyHandlers.ofString());
      return apiResponse.body();
    } catch (IOException ioe) {
      System.out.println("An I/O error occurred when sending or receiving data.");
      System.out.println(ioe.getMessage());
      return "An I/O error occurred when sending or receiving data.";

    } catch (InterruptedException ie) {
      System.out.println("The operation was interrupted.");
      System.out.println(ie.getMessage());
      return "The operation was interrupted.";

    } catch (IllegalArgumentException iae) {
      System.out.println(
              "The request argument was invalid. It must be built as specified by HttpRequest.Builder.");
      System.out.println(iae.getMessage());
      return "The request argument was invalid. It must be built as specified by HttpRequest.Builder.";

    } catch (SecurityException se) {
      System.out.println("There was a security configuration error.");
      System.out.println(se.getMessage());
      return "There was a security configuration error.";
    }
  }
}
