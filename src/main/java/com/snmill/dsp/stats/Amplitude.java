package com.snmill.dsp.stats;

import java.math.BigDecimal;

class Amplitude {

    final BigDecimal value;

    Amplitude(BigDecimal value) {
        this.value = value;
    }

    static Amplitude of(int value) {
        return new Amplitude(BigDecimal.valueOf(value));
    }


    BigDecimal get() {
        return value;
    }

}
