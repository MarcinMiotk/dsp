package com.snmill.dsp.file;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

class WavHeader {

    final ByteBuffer header = ByteBuffer.allocate(44);

    WavHeader() {
        header.put(new byte[] {(byte)0x52, (byte)0x49, (byte)0x46, (byte)0x46});    // RIFF
        header.position(header.position()+4);       // skip ChunkSize
        header.put(new byte[] {(byte)0x57, (byte)0x41, (byte)0x56, (byte)0x45});    // WAVE
        header.put(new byte[] {(byte)0x66, (byte)0x6d, (byte)0x74, (byte)0x20});    // fmt_

        header.put(new byte[] {(byte)0x10, (byte)0x00, (byte)0x00, (byte)0x00});

        //http://soundfile.sapp.org/doc/WaveFormat/

    }

    int getSubchunk1Size() {
//        header.order(ByteOrder.LITTLE_ENDIAN);
        return header.getInt(16);
    }


    static WavHeader createFor(RawFile file) {
        return new WavHeader();
    }
}
