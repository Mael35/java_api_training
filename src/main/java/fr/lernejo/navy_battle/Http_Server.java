package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Http_Server  {

    private final int port;

    public Http_Server(String arg) {
        try {
            this.port = Integer.parseInt(arg);
        }catch (NumberFormatException e){
            throw new NumberFormatException("The port is not a number !");
        }
        if (this.port < 1024 || this.port > 65535){
            throw new ArithmeticException("The number of the port is not available (between 1024 and 65535) !");
        }
    }

    public void createServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(this.port), 0);
        server.setExecutor(Executors.newFixedThreadPool(1));
        server.createContext("/ping", new CallHandler());
        server.start();
    }
}
