package com.snmill.dsp.stats;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * https://en.wikipedia.org/wiki/Histogram
 */
class Histogram {

    final List<Bin> histogramBins = new ArrayList<>();

    void forEach(Consumer<Bin> consumer) {
     //   histogramBins.forEach(consumer);
    }

    Histogram(Signal signal, int bins) {
        BigDecimal min = signal.min().get();
        BigDecimal max = signal.max().get();
        BigDecimal delta = max.subtract(min);
        binSize = delta.divide(BigDecimal.valueOf(bins));
        for(int i=0; i<bins; i++) {
            histogramBins.add(new Bin());
        }
    }

    final BigDecimal binSize;

    BigDecimal binSize() {
        return binSize;
    }


    static HistogramBuilder of(Signal signal) {
        return new HistogramBuilder(signal);
    }

    static class HistogramBuilder {

        int expectedNumberOfBeans;
        Signal signal;

        HistogramBuilder(Signal signal) {
            this.signal = signal;
        }

        HistogramBuilder bins(int expectedNumberOfBeans) {
            this.expectedNumberOfBeans = expectedNumberOfBeans;
            return this;
        }

        Histogram build() {
            return new Histogram(signal, expectedNumberOfBeans);
        }
    }

    static class Bin {
        static Bin range(Amplitude from, Amplitude to) {
            return new Bin();
        }

        String getRange() {
            return "fake";
        }

        int size() {
            return 0;
        }
    }
}
