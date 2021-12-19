package fr.lernejo.navy_battle.Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import java.io.IOException;
import java.io.OutputStream;


public class FireHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String body = "";
        if (!"GET".equals(exchange.getRequestMethod())){exchange.sendResponseHeaders(404, 0);}
        else {
            if (checkBody(exchange)){
                body = createBody();
                exchange.sendResponseHeaders(202, body.length());
            }
            else{exchange.sendResponseHeaders(400, 0);}
        }
        try (OutputStream os = exchange.getResponseBody()){os.write(body.getBytes());}
    }

    private boolean checkBody(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();

        if (query.contains("cell=")){
            try {
                String cell = query.split("=")[1];
                String letter = cell.substring(0,1);
                String number = cell.substring(1);
                System.out.println(letter + number);
                return true;
            } catch (Exception e) {e.printStackTrace();}
        }
        return false;
    }

    private String createBody(){
        JSONObject object = new JSONObject();
        object.put("consequence", "sunk");
        object.put("shipLeft", "true");
        return object.toString();
    }
}
