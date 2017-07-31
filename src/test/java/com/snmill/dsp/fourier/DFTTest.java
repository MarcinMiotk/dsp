package com.snmill.dsp.fourier;

import com.snmill.dsp.stats.Signal;
import org.junit.Test;

import java.util.List;

import static com.snmill.dsp.stats.Amplitude.of;
import static org.junit.Assert.*;

public class DFTTest {

    @Test
    public void Discrete_Fourier_Transform() {
        Signal signal = new Signal();
        signal.append(
                of(1), of(2), of(3), of(4),
                of(7), of(0), of(9), of(10),
                of(8), of(2), of(4), of(6)
        );

        DFT dft = new DFT();
        List<Complex> complexes = dft.dft(signal);
        for(int k=0; k<complexes.size(); k++) {
            System.out.println("X["+k+"]="+complexes.get(k));
        }
    }


}