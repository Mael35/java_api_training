package fr.lernejo.navy_battle.Handler;

import fr.lernejo.navy_battle.Server.Http_Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;


class FireHandlerTest {

    @Test
    void FireHandler_true() {
        try {
            Http_Server http_server = new Http_Server("7100");
            http_server.createServer();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:7100/api/game/fire?cell=J10")).build();
            CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            completableFuture.thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> response = completableFuture.join();
            Assertions.assertEquals(response.statusCode(), 202);
        } catch (Exception e) {e.printStackTrace();}
    }

    @Test
    void FireHandler_not_GET_false() {
        try {
            Http_Server http_server = new Http_Server("7200");
            http_server.createServer();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:7200/api/game/fire?cell=J10")).setHeader("Accept", "application/json")
                .setHeader("Content-Type", "application/json").PUT(HttpRequest.BodyPublishers.ofString("false")).build();
            CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            completableFuture.thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> response = completableFuture.join();
            Assertions.assertEquals(response.statusCode(), 404);
        } catch (Exception e) {e.printStackTrace();}
    }

    @Test
    void FireHandler_parameter_wrong_false() {
        try {
            Http_Server http_server = new Http_Server("7300");
            http_server.createServer();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:7300/api/game/fire?error=J10")).build();
            CompletableFuture<HttpResponse<String>> completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            completableFuture.thenApplyAsync(HttpResponse::headers);
            HttpResponse<String> response = completableFuture.join();
            Assertions.assertEquals(response.statusCode(), 400);
        } catch (Exception e) {e.printStackTrace();}
    }
}
