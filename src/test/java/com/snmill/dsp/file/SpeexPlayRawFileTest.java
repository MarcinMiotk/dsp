package com.snmill.dsp.file;

import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpeexPlayRawFileTest {


    @Ignore
    @Test
    public void play() throws Exception {
        Path path = FileSystems.getDefault().getPath("src", "test", "resources", "audio_files", "32k_mono_16bit_signed_mono.raw");
        SpeexPlayRawFile.play(path);
    }

    @Test
    public void lengthInMilliseconds() throws Exception {
        Path path = FileSystems.getDefault().getPath("src", "test", "resources", "audio_files", "32k_mono_16bit_signed_mono.raw");
        long milliseconds = SpeexPlayRawFile.lengthInMilliseconds(path);
        System.out.println(milliseconds);
        assertThat(milliseconds, equalTo(13100L));
    }
}