package com.snmill.dsp.file;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import static com.snmill.utils.HexUtil.toHex;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.*;

public class WavHeaderTest {

    @Test
    public void header_has_length_44() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        assertThat(header.header.array().length, equalTo(44));
    }

    @Test
    public void ChunkID_is_RIFF() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        String hex = toHex(header.header.array());
        assertThat(hex, startsWith("52494646"));    // RIFF
    }

    @Test
    public void Format_is_WAVE() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        String hex = toHex(header.header.array()).substring(8*2); // skips 8 bytes
        assertThat(hex, startsWith("57415645"));    // RIFF
    }

    @Test
    public void Subchunk1ID_is_fmt() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        String hex = toHex(header.header.array()).substring(12*2); // skips 8 bytes
        assertThat(hex, startsWith("666d7420"));    // fmt_
    }

    @Test
    public void Subchunk1Size_is_16_due_to_PCM() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        assertThat(header.getSubchunk1Size(), equalTo(16));
    }

    @Test
    public void AudioFormat_is_1_PCM() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        assertThat(header.getAudioFormat(), equalTo(1));
    }

    @Test
    public void NumChannels_is_1_mono() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        assertThat(header.getNumChannels(), equalTo(1));
    }

    @Test
    public void SampleRate_is_32000_mono() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        assertThat(header.getSampleRate(), equalTo(32000));
    }

    @Test
    public void BitsPerSample_is_16() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        assertThat(header.getBitsPerSample(), equalTo(16));
    }

    @Test
    public void ByteRate_is_64000() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        assertThat(header.getByteRate(), equalTo(64000));
    }

    @Test
    public void BlockAlign_is_2() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        assertThat(header.getBlockAlign(), equalTo(2));
    }

    @Test
    public void Subchunk2ID_is_64617461() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        String hex = toHex(header.header.array()).substring(36*2); // skips 36 bytes
        assertThat(hex, startsWith("64617461"));                   // data
    }

    @Test
    public void Subchunk2Size_is_838400() throws IOException {
        WavHeader header = example_32k_mono_16bit_signed_mono();
        assertThat(header.getSubchunk2Size(), equalTo(838400L));
    }

    WavHeader example_32k_mono_16bit_signed_mono() throws IOException {
        Path path = FileSystems.getDefault().getPath("src", "test", "resources", "audio_files", "32k_mono_16bit_signed_mono.raw");
        RawFile file = RawFile.load(path);
        WavHeader header = WavHeader.createFor(file);
        return header;
    }


}