package com.snmill.dsp.stats;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

class Mean {

    static Mean of(Signal signal) {
        return new Mean(signal);
    }

    BigDecimal mean = BigDecimal.ZERO;
    final Signal signal;

    Mean(Signal signal) {
        this.signal = signal;
        recalculate();
    }

    private void recalculate() {
        AtomicReference<BigDecimal> sum = new AtomicReference<>(BigDecimal.ZERO);
        AtomicReference<BigDecimal> count = new AtomicReference<>(BigDecimal.ZERO);
        signal.forEach(amplitude->{
            sum.set(sum.get().add(amplitude.get()));
            count.set(count.get().add(BigDecimal.ONE));
        });
        mean = sum.get().divide(count.get());
    }

    BigDecimal get() {
        return mean;
    }
}
