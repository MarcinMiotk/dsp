package com.snmill.dsp.fourier;


import com.snmill.dsp.stats.Amplitude;
import com.snmill.dsp.stats.Signal;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import static com.snmill.dsp.fourier.DFT.ForSignal.forSignal;

class DFT {

    static final BigDecimal PI = new BigDecimal(Math.PI);
    static final BigDecimal DoubledPI = PI.multiply(new BigDecimal(2));

    public List<Complex> dft(Signal signal) {
        int frequencyFrom = 0;
        int frequencyTo = signal.countAllSamples()/2;
        List<Complex> frequencyDomain = forSignal(signal).forEachFrequencyIndex(frequencyFrom, frequencyTo).computeHarmonic(DFT::compute_harmonic_part);
        return frequencyDomain;
    }

    static class ForSignal {

        static ActionsOnSignal forSignal(Signal signal) {
            return  new ActionsOnSignal(signal);
        }
    }

    static class ActionsOnSignal {

        final Signal signal;
        int frequencyFrom;
        int frequencyTo;

        public ActionsOnSignal(Signal signal) {
            this.signal = signal;
        }

        ActionsOnSignal forEachFrequencyIndex(int from, int to) {
            this.frequencyFrom = from;
            this.frequencyTo = to;
            return this;
        }

        List<Complex> computeHarmonic(HarmonicComputation computation) {
            final List<Complex> harmonics = new ArrayList<>();
            for(int frequencyIndexK = 0; frequencyIndexK<frequencyTo; frequencyIndexK++) {
                Complex harmonicForFrequencyIndexK = harmonicParticleForEachSample(signal, frequencyIndexK, signal.countAllSamples(), computation);
                harmonics.add(harmonicForFrequencyIndexK);
            }
            return harmonics;
        }
    }

    @FunctionalInterface
    interface HarmonicComputation {
        Complex compute(Amplitude amplitude, Integer sampleIndex, int frequencyIndexK, Integer allSamples);
    }

    static Complex compute_harmonic_part(Amplitude amplitude, Integer sampleIndex, int frequencyIndexK, Integer allSamples) {
        BigDecimal trigonometricParameter = DoubledPI.multiply(new BigDecimal(sampleIndex)).multiply(new BigDecimal(frequencyIndexK)).divide(new BigDecimal(allSamples), MathContext.DECIMAL128);
        BigDecimal real = amplitude.get().multiply(new BigDecimal(Math.cos(trigonometricParameter.doubleValue())));
        BigDecimal img = amplitude.get().multiply(new BigDecimal(Math.sin(trigonometricParameter.doubleValue())));
        return new Complex(real, img);
    }

    static Complex harmonicParticleForEachSample(Signal signal, Integer frequencyIndexK, Integer allSamples, HarmonicComputation fn) {
        ComplexSummarizer summarizer = new ComplexSummarizer();
        signal.forEach((amplitude, sampleIndex) -> {
            Complex complexForSample = fn.compute(amplitude, sampleIndex, frequencyIndexK, allSamples);
            summarizer.add(complexForSample);
        });
        return summarizer.build();
    }

    static class ComplexSummarizer {
        BigDecimal real = BigDecimal.ZERO;
        BigDecimal img = BigDecimal.ZERO;
        void add(Complex complex) {
            real = real.add(complex.real.value);
            img = img.add(complex.img.value);
        }
        Complex build() {
            return new Complex(real, img);
        }
    }
}
