package com.flow.qrcodegenerator;

import com.flow.qrcodegenerator.enums.CharacterCapacities;
import com.flow.qrcodegenerator.enums.ErrorCorrectionLevel;
import com.flow.qrcodegenerator.enums.Mode;
import com.flow.qrcodegenerator.enums.Version;

import java.util.Comparator;
import java.util.List;

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

    public static Version determineMinimumVersion(String stringToEncode, Mode mode, ErrorCorrectionLevel errorCorrectionLevel) {
        int characterCount = stringToEncode.length();
        List<CharacterCapacities> characterCapacities =
                CharacterCapacities.getAllCharactersCapacitiesByErrorCorrectionLevel(errorCorrectionLevel);
        return characterCapacities.stream().filter(characterCapacity -> switch (mode) {
                    case NUMERIC -> characterCapacity.getCharacterCapacitiesNumeric() > characterCount;
                    case ALPHANUMERIC -> characterCapacity.getCharacterCapacitiesAlphanumeric() > characterCount;
                    case BYTE -> characterCapacity.getCharacterCapacitiesByte() > characterCount;
                    case KANJI -> characterCapacity.getCharacterCapacitiesKanji() > characterCount;
                }).min(Comparator.comparingInt(characterCapacity -> switch (mode) {
                    case NUMERIC -> characterCapacity.getCharacterCapacitiesNumeric();
                    case ALPHANUMERIC -> characterCapacity.getCharacterCapacitiesAlphanumeric();
                    case BYTE -> characterCapacity.getCharacterCapacitiesByte();
                    case KANJI -> characterCapacity.getCharacterCapacitiesKanji();
                }))
                .map(CharacterCapacities::getVersion)
                .orElse(Version.VERSION_40);
    }
}
