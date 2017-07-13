package com.snmill.dsp.stats;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MeanTest {


    @Test
    public void mean() {
        Signal signal = new Signal();
        signal.append(Amplitude.of(0));
        signal.append(Amplitude.of(1));
        signal.append(Amplitude.of(-2));
        signal.append(Amplitude.of(4));
        signal.append(Amplitude.of(0));
        signal.append(Amplitude.of(1));
        signal.append(Amplitude.of(-2));
        signal.append(Amplitude.of(2));

        Mean mean = Mean.of(signal);

        BigDecimal expected = new BigDecimal("0.5");
        assertEquals(expected, mean.get());
    }
}