package edu.brown.cs.student.client;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {

  /**
   * Similar to the introductory GET request, but restricted to api key holders only. Try calling it without the API
   * Key configured and see what happens!
   * @param reqUri - the URL to access
   * @return an HttpRequest object for accessing the secured resource.
   */
  public static HttpRequest getRequest(URI reqUri) {

    HttpRequest request = HttpRequest.newBuilder()
        .GET()
        .uri(reqUri)
        .build();

    return request;
  }
}
