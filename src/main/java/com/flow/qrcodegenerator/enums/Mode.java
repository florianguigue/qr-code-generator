package com.flow.qrcodegenerator.enums;

public enum Mode {
    NUMERIC("0001"),
    ALPHANUMERIC("0010"),
    BYTE("0100"),
    KANJI("1000");

    private final String modeByteEncoding;

    Mode(String modeByteEncoding) {
        this.modeByteEncoding = modeByteEncoding;
    }

    public String getModeByteEncoding() {
        return modeByteEncoding;
    }
}
