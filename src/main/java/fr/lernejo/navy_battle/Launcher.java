package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.Server.Client_Server;
import fr.lernejo.navy_battle.Server.Http_Server;
import java.io.IOException;


public class Launcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 1) {
            Http_Server http_server = new Http_Server(args[0]);
            http_server.createServer();
        }
        else if (args.length == 2) {
            if (Integer.parseInt(args[0]) < 1024 || Integer.parseInt(args[0]) > 65535){throw new IllegalArgumentException("Arg1 out of range !");}
            else if (!args[1].contains("http://localhost:")){throw new IllegalArgumentException("Arg2 is not an URL !");}
            else{
                Client_Server client_server = new Client_Server(args[0]);
                client_server.Send_Request(args[1]);
                client_server.Fire_Request(args[1],"B2");
            }
        }
        else{throw new IllegalArgumentException("Argument error !");}
    }
}

