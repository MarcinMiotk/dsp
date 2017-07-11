package com.snmill.dsp.file;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * http://soundfile.sapp.org/doc/WaveFormat/
 */
class WavHeader {

    final ByteBuffer header = ByteBuffer.allocate(44);

    WavHeader(final int NumChannels, final int SampleRate, final int BitsPerSample, final Subchunk2Size subchunk2Size) {
        if(NumChannels!=1) {
            throw new UnsupportedOperationException("Only MONO(1) is supported");
        }
        header.put(new byte[] {(byte)0x52, (byte)0x49, (byte)0x46, (byte)0x46});    // RIFF
        header.order(ByteOrder.LITTLE_ENDIAN);
        long ChunkSize = 36+subchunk2Size.getSubchunk2Size();
        header.putInt(4, (int)ChunkSize);

        header.position(header.position()+4);       // skip ChunkSize
        header.put(new byte[] {(byte)0x57, (byte)0x41, (byte)0x56, (byte)0x45});    // WAVE
        header.put(new byte[] {(byte)0x66, (byte)0x6d, (byte)0x74, (byte)0x20});    // fmt_
        header.put(new byte[] {(byte)0x10, (byte)0x00, (byte)0x00, (byte)0x00});    // SubChunk1Size = 16
        header.put(new byte[] {(byte)0x01, (byte)0x00});                            // AudioFormat = 1 = PCM
        header.order(ByteOrder.LITTLE_ENDIAN);
        header.putShort(22, (short)NumChannels);                                  // NumChannels = 1 = mono
        header.putInt(24, SampleRate);                                            // SampleRate
        header.putInt(28, SampleRate*NumChannels*BitsPerSample/8);             // ByteRate
        header.putShort(32, (short)((short)NumChannels*(short)BitsPerSample/(short)8)); // BlockAlign
        header.putShort(34, (short)BitsPerSample);
        header.position(36);
        header.put(new byte[] {(byte)0x64, (byte)0x61, (byte)0x74, (byte)0x61});    // data
        header.putInt(40, (int)subchunk2Size.getSubchunk2Size());
    }

    int getSubchunk1Size() {
        header.order(ByteOrder.LITTLE_ENDIAN);
        return header.getInt(16);
    }

    int getAudioFormat() {
        header.order(ByteOrder.LITTLE_ENDIAN);
        return header.getShort(20);
    }

    int getNumChannels() {
        header.order(ByteOrder.LITTLE_ENDIAN);
        return header.getShort(22);
    }

    int getSampleRate() {
        header.order(ByteOrder.LITTLE_ENDIAN);
        return header.getInt(24);
    }

    int getBitsPerSample() {
        header.order(ByteOrder.LITTLE_ENDIAN);
        return header.getShort(34);
    }

    int getByteRate() {
        header.order(ByteOrder.LITTLE_ENDIAN);
        return header.getInt(28);
    }

    int getBlockAlign() {
        header.order(ByteOrder.LITTLE_ENDIAN);
        return header.getShort(32);
    }

    long getSubchunk2Size() {
        header.order(ByteOrder.LITTLE_ENDIAN);
        return header.getInt(40);
    }

    static WavHeader createFor(RawFile file) {
        return new WavHeader(1, 32000, 16, new Subchunk2SizeBasedOnFileLength(file.getLength()));
    }


    static WavHeader createFor(RawFile file, final int SampleRate) {
        return new WavHeader(1, SampleRate, 16, new Subchunk2SizeBasedOnFileLength(file.getLength()));
    }
}
