package fr.lernejo.navy_battle;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        if (args.length == 1) {
            Http_Server http_server = new Http_Server(args[0]);
            http_server.createServer();
        }

        else{
            throw new IllegalArgumentException("Argument error !");
        }
    }
}

