package com.snmill.dsp.stats;

import java.math.BigDecimal;

class Deviation {

    final BigDecimal deviation;

    Deviation(BigDecimal deviation) {
        this.deviation = deviation;
    }

    static Deviation deviation(Amplitude amplitude, Mean mean) {
        BigDecimal deviation = BigDecimal.ZERO;
        deviation = amplitude.get().subtract(mean.get());
        deviation = deviation.pow(2);
        return new Deviation(deviation);
    }

    BigDecimal get() {
        return  deviation;
    }
}
