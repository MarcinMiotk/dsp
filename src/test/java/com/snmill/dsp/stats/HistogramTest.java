package com.snmill.dsp.stats;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

import static com.snmill.dsp.stats.Amplitude.of;
import static org.junit.Assert.*;

public class HistogramTest {


    @Test
    public void histogram_has_computed_binSize() {
        Signal signal = new Signal();
        signal.append(
                of(1), of(2), of(3), of(4),
                of(7), of(0), of(9), of(10)
        );
        Histogram histogram = Histogram.of(signal).bins(5).build();
        BigDecimal expected = new BigDecimal("2");
        assertEquals(expected, histogram.binSize());
    }

    @Test
    public void histogram_has_bins() {
        Signal signal = new Signal();
        signal.append(
                of(1), of(2), of(3), of(4),
                of(7), of(0), of(9), of(10)
        );
        Histogram histogram = Histogram.of(signal).bins(5).build();

        AtomicInteger counter = new AtomicInteger(0);

        histogram.forEach(bin->{
            counter.incrementAndGet();
        });

        assertEquals(5, counter.get());
    }
}