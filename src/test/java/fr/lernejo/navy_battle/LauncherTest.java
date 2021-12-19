package fr.lernejo.navy_battle;

import fr.lernejo.navy_battle.Server.Http_Server;
import org.junit.jupiter.api.Test;
import java.io.IOException;


class LauncherTest {

    @Test
    void Argument_True() {
        org.assertj.core.api.Assertions.assertThatNoException()
            .isThrownBy(() -> Launcher.main(new String[] {"2000"}));
    }

    @Test
    void No_Argument_False() {
        org.assertj.core.api.Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Launcher.main(new String[] {}))
            .withMessage("Argument error !");
    }

    @Test
    void Two_Arguments_True() throws IOException {
        Http_Server http_server1 = new Http_Server("2100");
        http_server1.createServer();
        org.assertj.core.api.Assertions.assertThatNoException()
            .isThrownBy(() -> Launcher.main(new String[] {"2200", "http://localhost:2100"}));
    }

    @Test
    void First_Argument_False() {
        org.assertj.core.api.Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Launcher.main(new String[] {"-10000", "http://localhost:2300"}))
            .withMessage("Arg1 out of range !");
    }

    @Test
    void Second_Argument_False() {
        org.assertj.core.api.Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Launcher.main(new String[] {"2400", "localhost:4000"}))
            .withMessage("Arg2 is not an URL !");
    }

    @Test
    void TooMany_Argument_False() {
        org.assertj.core.api.Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> Launcher.main(new String[] {"2500", "2600", "2700"}))
            .withMessage("Argument error !");
    }
}
