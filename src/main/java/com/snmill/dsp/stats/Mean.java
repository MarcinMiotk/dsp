package com.snmill.dsp.stats;

import java.math.BigDecimal;

class Mean {

    final BigDecimal mean;

    Mean(Signal signal) {
        Summarizer sum = new Summarizer();
        Counter count = new Counter();
        signal.forEach(amplitude->{
            sum.it(amplitude);
            count.increment();
        });
        mean = sum.get().divide(count.get());
    }

    static Mean of(Signal signal) {
        return new Mean(signal);
    }

    BigDecimal get() {
        return mean;
    }
}
