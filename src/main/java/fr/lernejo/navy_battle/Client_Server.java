package fr.lernejo.navy_battle;

import java.net.URI;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Client_Server {
    private final int port;

    public Client_Server(String string) {
        this.port = Integer.parseInt(string);
    }

    public void Send_Request(String adversaryURL) throws IOException, InterruptedException {
        HttpClient client_server = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(adversaryURL + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString("{\"id\":\"2\", \"url\":\"http://localhost:" + port + "\", \"message\":\"hello\"}"))
            .build();

        HttpResponse<String> response = client_server.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
