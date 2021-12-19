package fr.lernejo.navy_battle.Handler;

import fr.lernejo.navy_battle.Server.Http_Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;


class StartHandlerTest {

    @Test
    void StartHandler_true() {
        try {
            Http_Server http_server1 = new Http_Server("4530");
            http_server1.createServer();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:4530/api/game/start")).header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:4530\", \"message\":\"Start success\"}")).build();
            CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            completableFuture.thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> response = completableFuture.join();
            Assertions.assertEquals(response.statusCode(), 202);
            Assertions.assertEquals(response.body(), "{\"id\":\"1\",\"message\":\"Start success\",\"url\":\"http://localhost:4530\"}");
        } catch (Exception e) {e.printStackTrace();}
    }

    @Test
    void StartHandler_not_POST_false() {
        try {
            Http_Server http_server1 = new Http_Server("4540");
            http_server1.createServer();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:4540/api/game/start")).header("Accept", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:4540\", \"message\":\"Start success\"}")).build();
            CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            completableFuture.thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> response = completableFuture.join();
            Assertions.assertEquals(response.statusCode(), 404);
        } catch (Exception e) {e.printStackTrace();}
    }

    @Test
    void StartHandler_parameter_missing_false() {
        try {
            Http_Server http_server1 = new Http_Server("4550");
            http_server1.createServer();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:4550/api/game/start")).header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"1\", \"url\":\"http://localhost:4550\"}")).build();
            CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            completableFuture.thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> response = completableFuture.join();
            Assertions.assertEquals(response.statusCode(), 400);
        } catch (Exception e) {e.printStackTrace();}
    }
}
