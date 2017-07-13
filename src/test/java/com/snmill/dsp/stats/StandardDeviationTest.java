package com.snmill.dsp.stats;

import org.junit.Test;

import java.math.BigDecimal;

import static com.snmill.dsp.stats.Amplitude.of;
import static org.junit.Assert.*;

public class StandardDeviationTest {


    /**
     * Based on sample on https://en.wikipedia.org/wiki/Standard_deviation
     */
    @Test
    public void standard_deviation() {
        Signal signal = new Signal();
        signal.append(
                of(2), of(4), of(4), of(4),
                of(5), of(5), of(7), of(9)
        );

        StandardDeviation standardDeviation = StandardDeviation.of(signal);

        BigDecimal expected = new BigDecimal("2.0");
        assertEquals(expected, standardDeviation.get());

    }
}