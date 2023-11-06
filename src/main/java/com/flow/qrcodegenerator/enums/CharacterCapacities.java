package com.flow.qrcodegenerator.enums;

import java.util.Arrays;
import java.util.List;

public enum CharacterCapacities {
    L_1(Version.VERSION_1, ErrorCorrectionLevel.L, 41, 25, 17, 10),
    M_1(Version.VERSION_1, ErrorCorrectionLevel.M, 34, 20, 14, 8),
    Q_1(Version.VERSION_1, ErrorCorrectionLevel.Q, 27, 16, 11, 7),
    H_1(Version.VERSION_1, ErrorCorrectionLevel.H, 17, 10, 7, 4),
    L_2(Version.VERSION_2, ErrorCorrectionLevel.L, 77, 47, 32, 20),
    M_2(Version.VERSION_2, ErrorCorrectionLevel.M, 63, 38, 26, 16),
    Q_2(Version.VERSION_2, ErrorCorrectionLevel.Q, 48, 29, 20, 12),
    H_2(Version.VERSION_2, ErrorCorrectionLevel.H, 34, 20, 14, 8),
    L_3(Version.VERSION_3, ErrorCorrectionLevel.L, 127, 77, 53, 32),
    M_3(Version.VERSION_3, ErrorCorrectionLevel.M, 101, 61, 42, 26)
    ;

    private final Version version;
    private final ErrorCorrectionLevel errorCorrectionLevel;
    private final int characterCapacitiesNumeric;
    private final int characterCapacitiesAlphanumeric;
    private final int characterCapacitiesByte;
    private final int characterCapacitiesKanji;

    CharacterCapacities(Version version, ErrorCorrectionLevel errorCorrectionLevel, int characterCapacitiesNumeric,
                        int characterCapacitiesAlphanumeric, int characterCapacitiesByte, int characterCapacitiesKanji) {
        this.version = version;
        this.errorCorrectionLevel = errorCorrectionLevel;
        this.characterCapacitiesNumeric = characterCapacitiesNumeric;
        this.characterCapacitiesAlphanumeric = characterCapacitiesAlphanumeric;
        this.characterCapacitiesByte = characterCapacitiesByte;
        this.characterCapacitiesKanji = characterCapacitiesKanji;
    }

    public Version getVersion() {
        return version;
    }

    public ErrorCorrectionLevel getErrorCorrectionLevel() {
        return errorCorrectionLevel;
    }

    public int getCharacterCapacitiesNumeric() {
        return characterCapacitiesNumeric;
    }

    public int getCharacterCapacitiesAlphanumeric() {
        return characterCapacitiesAlphanumeric;
    }

    public int getCharacterCapacitiesByte() {
        return characterCapacitiesByte;
    }

    public int getCharacterCapacitiesKanji() {
        return characterCapacitiesKanji;
    }

    public static List<CharacterCapacities> getAllCharactersCapacitiesByErrorCorrectionLevel(
            ErrorCorrectionLevel errorCorrectionLevel
    ) {
        return Arrays.stream(CharacterCapacities.values())
                .filter(characterCapacity -> errorCorrectionLevel.equals(characterCapacity.getErrorCorrectionLevel()))
                .toList();
    }
}
