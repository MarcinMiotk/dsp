package com.snmill.dsp.file;

class Subchunk2SizeBasedOnFileLength implements Subchunk2Size {

    final long fileSize;

    Subchunk2SizeBasedOnFileLength(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public long getSubchunk2Size() {
        return fileSize;
    }
}
