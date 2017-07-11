package com.snmill.dsp.file;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class WavWriterTest {

    @Test
    public void write_speex_raw_into_wav() throws IOException {
        Path source = FileSystems.getDefault().getPath("src", "test", "resources", "audio_files", "32k_mono_16bit_signed_mono.raw");
        RawFile file = RawFile.load(source);
        WavHeader header = WavHeader.createFor(file);
        Path target = FileSystems.getDefault().getPath("target", "32k_mono_16bit_signed_mono.wav");
        WavWriter.write(target, header, file);
    }

    @Test
    public void write_44100_raw_into_wav() throws IOException {
        Path source = FileSystems.getDefault().getPath("src", "test", "resources", "audio_files", "44100_mono_16bit.raw");
        RawFile file = RawFile.load(source);
        WavHeader header = WavHeader.createFor(file, 44100);
        Path target = FileSystems.getDefault().getPath("target", "44100_mono_16bit.wav");
        WavWriter.write(target, header, file);
    }
}