package com.snmill.dsp.file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

class RawFile {

    final FileChannel channel;

    public RawFile(FileChannel channel) {
        this.channel = channel;
    }

    public long getLength() {
        try {
            return channel.size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ByteBuffer getPayload() throws IOException {
        long size = getLength();
        ByteBuffer payload = ByteBuffer.allocate((int)size);
        channel.read(payload);
        return payload;
    }

    public static RawFile load(Path path) throws IOException {
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
        return new RawFile(channel);
    }
}
