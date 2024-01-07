package com.flow.qrcodegenerator;

import com.opencsv.bean.CsvBindByName;

public final class CharacterCapacities {
    @CsvBindByName
    private int version;
    @CsvBindByName(column = "numeric")
    private int numericMode;
    @CsvBindByName(column = "alphanumeric")
    private int alphanumericMode;
    @CsvBindByName(column = "byte")
    private int byteMode;
    @CsvBindByName(column = "kanji")
    private int kanjiMode;

    public CharacterCapacities() {
        // Used by OpenCSV
    }

    public int version() {
        return version;
    }

    public int numericMode() {
        return numericMode;
    }

    public int alphanumericMode() {
        return alphanumericMode;
    }

    public int byteMode() {
        return byteMode;
    }

    public int kanjiMode() {
        return kanjiMode;
    }
}
