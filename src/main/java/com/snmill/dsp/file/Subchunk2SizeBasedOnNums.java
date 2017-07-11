package com.snmill.dsp.file;

class Subchunk2SizeBasedOnNums implements Subchunk2Size {

    final int NumSamples;
    final int NumChannels;
    final int BitsPerSample;

    public Subchunk2SizeBasedOnNums(int numSamples, int numChannels, int bitsPerSample) {
        NumSamples = numSamples;
        NumChannels = numChannels;
        BitsPerSample = bitsPerSample;
    }

    @Override
    public long getSubchunk2Size() {
        return NumSamples*NumChannels*BitsPerSample/8;
    }
}
