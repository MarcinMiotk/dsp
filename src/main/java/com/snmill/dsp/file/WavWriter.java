package com.snmill.dsp.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

class WavWriter {

    static void write(Path target, WavHeader header, RawFile raw) throws IOException {
        try(FileChannel channel = FileChannel.open(target, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            ByteBuffer h = header.header;
            h.rewind();
            channel.write(h);
            ByteBuffer p = raw.getPayload();
            p.rewind();
            channel.write(p);
        }
    }
}
