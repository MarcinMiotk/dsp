package com.snmill.dsp.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class Signal {

    private final List<Amplitude> samples = new ArrayList();

    void append(Amplitude... amplitudes) {
        for(Amplitude amplitude : amplitudes) {
            samples.add(amplitude);
        }
    }

    void forEach(Consumer<Amplitude> consumer) {
        samples.forEach(consumer);
    }
}
