package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;


class Client_ServerTest {

    @Test
    void Send_Request_true() {
        try {
            Http_Server http_server1 = new Http_Server("5010");
            http_server1.createServer();
            Client_Server client_server1 = new Client_Server("5020");
            org.assertj.core.api.Assertions.assertThatNoException().isThrownBy(() -> client_server1.Send_Request("http://localhost:5010"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void Not_Same_Port_true() {
        try {
            String http_port = "5030";
            String client_port = "5040";
            Http_Server http_server2 = new Http_Server(http_port);
            http_server2.createServer();
            org.assertj.core.api.Assertions.assertThat(client_port).isNotEqualTo(http_port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void Not_Same_Port_false() {
        try {
            String http_port = "5050";
            String client_port = "5050";
            Http_Server http_server2 = new Http_Server(http_port);
            http_server2.createServer();
            org.assertj.core.api.Assertions.assertThat(client_port).isEqualTo(http_port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
