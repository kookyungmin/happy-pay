package net.happykoo.common.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CommonHttpClient {

  private final HttpClient httpClient;
  private final ObjectMapper objectMapper;

  public CommonHttpClient() {
    this.httpClient = HttpClient.newBuilder().build();
    this.objectMapper = new ObjectMapper();
  }

  public <T> T sendGetRequest(String url, Class<T> clazz) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    return objectMapper.readValue(response.body(), clazz);
  }

  public <T> T sendGetRequest(String url, TypeReference<T> typeRef) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    return objectMapper.readValue(response.body(), typeRef);
  }

  public <T> CompletableFuture<T> sendPostRequest(
      String url,
      String body,
      TypeReference<T> typeRef
  ) {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .POST(HttpRequest.BodyPublishers.ofString(body))
        .build();

    return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenApply(res -> {
          try {
            return objectMapper.readValue(res, typeRef);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
  }

  public <T> CompletableFuture<T> sendPostRequest(
      String url,
      String body,
      Class<T> clazz
  ) {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .POST(HttpRequest.BodyPublishers.ofString(body))
        .build();

    return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenApply(HttpResponse::body)
        .thenApply(res -> {
          try {
            return objectMapper.readValue(res, clazz);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
  }

}
