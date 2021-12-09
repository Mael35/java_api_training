package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

class CallHandlerTest {

    @Test
    void CallHandler_true() {
        try {
            Http_Server http_server1 = new Http_Server("9876");
            http_server1.createServer();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:9876/ping"))
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("POST"))
                .build();
            CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            completableFuture
                .thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> response = completableFuture.join();
            Assertions.assertEquals(response.statusCode(), 200);
            Assertions.assertEquals(response.body(), "OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void CallHandler_path_false() {
        try {
            Http_Server http_server1 = new Http_Server("6789");
            http_server1.createServer();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:6789/something/false"))
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("POST"))
                .build();
            CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            completableFuture
                .thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> response = completableFuture.join();
            Assertions.assertEquals(response.statusCode(), 404);
            Assertions.assertEquals(response.body(), "<h1>404 Not Found</h1>No context found for request");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
