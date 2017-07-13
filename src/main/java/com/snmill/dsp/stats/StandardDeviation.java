package com.snmill.dsp.stats;

import java.math.BigDecimal;

class StandardDeviation {

    final BigDecimal standardDeviation;

    StandardDeviation(Signal signal) {
        Mean mean = Mean.of(signal);
        Summarizer sum = new Summarizer();
        Counter count = new Counter();
        signal.forEach(amplitude -> {
            Deviation deviation = Deviation.deviation(amplitude, mean);
            sum.it(deviation);
            count.increment();
        });
        BigDecimal variance = sum.get().divide(count.get());
        standardDeviation = BigDecimal.valueOf(Math.sqrt(variance.doubleValue()));
    }

    static StandardDeviation of(Signal signal) {
        return new StandardDeviation(signal);
    }

    BigDecimal get() {
        return standardDeviation;
    }

}
