package com.snmill.dsp.file;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.util.concurrent.Semaphore;

class SpeexPlayRawFile {

    static long lengthInMilliseconds(Path path) {
        File file = path.toFile();
        long bytes = file.length();
        long duration = bytes/2/32; // 32000 Hz / 2 bytes * 1000 ms
        return duration;
    }

    static void play(Path path) throws Exception {
        long duration = lengthInMilliseconds(path);
        AudioFormat format = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                (float)32000 ,
                16,
                1,
                2,
                (float)32000,
                false);

        final Semaphore semaphore = new Semaphore(0);
        try(FileInputStream stream = new FileInputStream(path.toFile())) {
            try(AudioInputStream input = new AudioInputStream(stream, format, 32*duration)) {
                Clip clip = AudioSystem.getClip();
                clip.open(input);
                clip.addLineListener(lineEvent -> {
                    if(LineEvent.Type.STOP.equals(lineEvent.getType())) {
                        semaphore.release();
                    }
                    if(LineEvent.Type.START.equals(lineEvent.getType())) {

                    }
                });
                clip.start();
            }
        }
        semaphore.acquire();
    }

}
