package com.snmill.dsp.stats;

import java.math.BigDecimal;

class Counter {

    long count = 0;

    void increment() {
        count++;
    }

    BigDecimal get() {
        return BigDecimal.valueOf(count);
    }
}
