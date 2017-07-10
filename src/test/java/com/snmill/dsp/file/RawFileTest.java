package com.snmill.dsp.file;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class RawFileTest {

    @Test
    public void readRawFile() throws IOException {
        Path path = FileSystems.getDefault().getPath("src", "test", "resources", "audio_files", "32k_mono_16bit_signed_mono.raw");
        RawFile file = RawFile.load(path);
        assertNotNull(file);
    }
}