package edu.brown.cs.student.client;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {

  /**
   * A GET request.
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

  /**
   * A POST request.
   * @param reqUri - the url of the post destination
   * @param param - your cs login
   * @return an HttpRequest object for accessing and posting to the secured resource.
   */
  public static HttpRequest postRequest(String reqUri, String param) {
    String apiKey = ClientAuth.getApiKey();
    return HttpRequest.newBuilder(URI.create(reqUri))
        .POST(HttpRequest.BodyPublishers.ofString("{\"auth\":\"" + param + "\"}"))
        .header("x-api-key", apiKey)
        .build();
  }
}
