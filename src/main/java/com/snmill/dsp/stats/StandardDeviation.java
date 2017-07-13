package com.snmill.dsp.stats;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

class StandardDeviation {

    static class Deviation {

        final BigDecimal deviation;

        public Deviation(BigDecimal deviation) {
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

    StandardDeviation(Signal signal) {
        Mean mean = Mean.of(signal);


        List<Deviation> deviations = new ArrayList<>();

        signal.forEach(amplitude -> {
            Deviation deviation = Deviation.deviation(amplitude, mean);
            deviations.add(deviation);
        });

        AtomicReference<BigDecimal> sum = new AtomicReference<>(BigDecimal.ZERO);
        deviations.forEach(deviation -> {
            sum.set(sum.get().add(deviation.get()));
        });

        BigDecimal count = BigDecimal.valueOf(deviations.size());
        BigDecimal variance = sum.get().divide(count);

        standardDeviation = BigDecimal.valueOf(Math.sqrt(variance.doubleValue()));
    }

    static StandardDeviation of(Signal signal) {
        return new StandardDeviation(signal);
    }

    BigDecimal standardDeviation = BigDecimal.ZERO;

    BigDecimal get() {
        return standardDeviation;
    }

}
