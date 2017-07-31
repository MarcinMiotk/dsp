package com.snmill.dsp.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;


public class Signal {

    private final List<Amplitude> samples = new ArrayList();

    public void append(Amplitude... amplitudes) {
        for(Amplitude amplitude : amplitudes) {
            samples.add(amplitude);
            updateMinMax(amplitude);
        }
    }

    public void forEach(Consumer<Amplitude> consumer) {
        samples.forEach(consumer);
    }

    public void forEach(BiConsumer<Amplitude, Integer> consumer) {
        for(int i=0; i<samples.size(); i++) {
            consumer.accept(samples.get(i), i);
        }
    }

    private void updateMinMax(Amplitude candidate) {
        if(min==null) {
            min = candidate;
        }
        if(max == null) {
            max = candidate;
        }

        int minCompare = candidate.get().compareTo(min.get());
        int maxCompare = candidate.get().compareTo(max.get());

        if(minCompare<0) {
            min = candidate;
        }
        if(maxCompare>0) {
            max = candidate;
        }
    }


    Amplitude min = null;
    Amplitude max = null;

    Amplitude min() {
        return min;
    }

    Amplitude max() {
        return max;
    }

    public int countAllSamples() {
        return samples.size();
    }
}
