package com.snmill.dsp.stats;

import java.math.BigDecimal;

class Summarizer {

    BigDecimal sum = BigDecimal.ZERO;

    void it(Amplitude amplitude) {
        sum = sum.add(amplitude.get());
    }

    void it(Deviation deviation) {
        sum = sum.add(deviation.get());
    }

    BigDecimal get() {
        return sum;
    }
}
