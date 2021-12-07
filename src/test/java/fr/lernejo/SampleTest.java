package fr.lernejo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SampleTest {

    private final Sample sample = new Sample();

    @Test
    void opADD() {
        int a = 2;
        int b = 3;
        int addition = sample.op(Sample.Operation.ADD, a, b);
        org.assertj.core.api.Assertions.assertThat(addition).as("addition of 2 + 3").isEqualTo(5);
    }

    @Test
    void opMULT() {

        int a = 2;
        int b = 3;
        int multiplication = sample.op(Sample.Operation.MULT, a, b);
        org.assertj.core.api.Assertions.assertThat(multiplication).as("multiplication of 2 x 3").isEqualTo(6);
    }

    @Test
    void factExceptionBy0() {
        int n = 0;
        int factor = sample.fact(n);
        org.assertj.core.api.Assertions.assertThat(factor).as("factor of 0!").isEqualTo(1);
    }

    @Test
    void factExceptionByNegative() {
        int n = -3;
        org.assertj.core.api.Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> sample.fact(n));
    }

    @Test
    void fact() {
        int n = 3;
        int factor = sample.fact(n);
        Assertions.assertThat(factor).as("factor of 3!").isEqualTo(6);
    }
}
