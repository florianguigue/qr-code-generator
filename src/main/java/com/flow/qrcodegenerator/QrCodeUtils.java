package com.flow.qrcodegenerator;

import com.flow.qrcodegenerator.enums.ErrorCorrectionLevel;
import com.flow.qrcodegenerator.enums.Mode;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public abstract class QrCodeUtils {
    private final static String NUMERIC_REGEX = "[0-9]*";
    private final static String ALPHANUMERIC_REGEX = "[A-Z0-9$%*+\\-./: ]*";

    public static Mode determineMode(String stringToEncode) {
        if (stringToEncode.matches(NUMERIC_REGEX)) {
            return Mode.NUMERIC;
        }

        if (stringToEncode.matches(ALPHANUMERIC_REGEX)) {
            return Mode.ALPHANUMERIC;
        }

        return Mode.BYTE;
    }

    public static int determineVersion(String textToEncode, Mode mode) {
        int textLength = textToEncode.length();

        var characterCapacitiesByVersion = getCharacterCapacitiesList(ErrorCorrectionLevel.L);

        return characterCapacitiesByVersion.stream().filter(characterCapacities -> switch (mode) {
            case NUMERIC -> characterCapacities.numericMode() > textLength;
            case ALPHANUMERIC -> characterCapacities.alphanumericMode() > textLength;
            case BYTE -> characterCapacities.byteMode() > textLength;
            case KANJI -> characterCapacities.kanjiMode() > textLength;
        }).min((o1, o2) -> switch (mode) {
            case NUMERIC -> o1.numericMode() - o2.numericMode();
            case ALPHANUMERIC -> o1.alphanumericMode() - o2.alphanumericMode();
            case BYTE -> o1.byteMode() - o2.byteMode();
            case KANJI -> o1.kanjiMode() - o2.kanjiMode();
        }).orElseThrow().version();
    }

    private static List<CharacterCapacities> getCharacterCapacitiesList(ErrorCorrectionLevel errorCorrectionLevel) {
        return new CsvToBeanBuilder<CharacterCapacities>(
                new InputStreamReader(Objects.requireNonNull(
                        Thread.currentThread()
                                .getContextClassLoader()
                                .getResourceAsStream("%s.csv".formatted(errorCorrectionLevel.name()))
                ))
        )
                .withType(CharacterCapacities.class)
                .withSeparator(';')
                .build()
                .parse();
    }
}
