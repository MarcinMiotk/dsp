package com.snmill.dsp.stats;

import java.math.BigDecimal;

public class Amplitude {

    final BigDecimal value;

    Amplitude(BigDecimal value) {
        this.value = value;
    }

    public static Amplitude of(int value) {
        return new Amplitude(BigDecimal.valueOf(value));
    }

    public BigDecimal get() {
        return value;
    }
}
