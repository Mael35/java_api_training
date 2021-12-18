package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import java.io.*;
import java.nio.charset.StandardCharsets;


public class StartHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String body = "";
        if (!"POST".equals(exchange.getRequestMethod())){exchange.sendResponseHeaders(404, 0);}
        else
        {
            InputStream requestBody = exchange.getRequestBody();
            if (checkBody(requestBody)){
                body = createBody(exchange);
                exchange.sendResponseHeaders(202, body.length());
            }
            else{exchange.sendResponseHeaders(400, 0);}
        }
        try (OutputStream os = exchange.getResponseBody()){os.write(body.getBytes());}
    }

    private boolean checkBody(InputStream requestBody) throws IOException {
        String request = new String(requestBody.readAllBytes(), StandardCharsets.UTF_8);
        JSONObject object = new JSONObject(request);
        return object.has("id") && object.has("url") && object.has("message");
    }

    private String createBody(HttpExchange exchange){
        int port = exchange.getHttpContext().getServer().getAddress().getPort();
        JSONObject object = new JSONObject();
        object.put("id", "1");
        object.put("url", "http://localhost:" + port);
        object.put("message", "Start success");
        return object.toString();
    }
}
