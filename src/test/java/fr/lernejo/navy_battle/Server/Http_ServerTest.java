package fr.lernejo.navy_battle.Server;

import org.junit.jupiter.api.Test;


class Http_ServerTest {

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
}
