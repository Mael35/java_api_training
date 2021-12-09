package fr.lernejo.navy_battle;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import static org.junit.jupiter.api.Assertions.*;

class Http_ServerTest {

    public boolean isActive(String p) {
        int port = Integer.parseInt(p);
        try (Socket s = new Socket()) {
            s.setReuseAddress(true);
            SocketAddress sa = new InetSocketAddress(port);
            s.connect(sa, 3000);
            return true;
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return false;
    }

    private Http_Server server;

    @Test
    void createServer_True() {
        String true_port = "7000";
        server = new Http_Server(true_port);
        org.assertj.core.api.Assertions.assertThatNoException()
            .isThrownBy(() -> server.createServer());
    }

    @Test
    void Port_Words_False() {
        String false_port = "words_port";
        org.assertj.core.api.Assertions.assertThatExceptionOfType(NumberFormatException.class)
            .isThrownBy(() -> server = new Http_Server(false_port));
    }

    @Test
    void Port_Negative_False() {
        String false_port = "-6000";
        org.assertj.core.api.Assertions.assertThatExceptionOfType(ArithmeticException.class)
            .isThrownBy(() -> server = new Http_Server(false_port));
    }

    @Test
    void Port_OutRange_False() {
        String false_port = "5000000";
        org.assertj.core.api.Assertions.assertThatExceptionOfType(ArithmeticException.class)
            .isThrownBy(() -> server = new Http_Server(false_port));
    }

    @Test
    void Port_True() {
        String true_port = "4000";
        org.assertj.core.api.Assertions.assertThatNoException()
            .isThrownBy(() -> server = new Http_Server(true_port));
    }

    @Test
    void Port_Matched_True() throws Exception {
        String true_port = "9010";
        server = new Http_Server(true_port);
        server.createServer();
        assertTrue(isActive(true_port));
    }

    @Test
    void Port_Matched_False() throws Exception {
        String true_port = "9020";
        String false_port = "9030";
        server = new Http_Server(true_port);
        server.createServer();
        assertFalse(isActive(false_port));
    }
}
