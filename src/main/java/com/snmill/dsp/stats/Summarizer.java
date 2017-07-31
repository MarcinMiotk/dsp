package com.snmill.dsp.stats;

import java.math.BigDecimal;

public class Summarizer {

    BigDecimal sum = BigDecimal.ZERO;

    public void it(Amplitude amplitude) {
        sum = sum.add(amplitude.get());
    }

    public void it(Deviation deviation) {
        sum = sum.add(deviation.get());
    }

    public BigDecimal get() {
        return sum;
    }
}
