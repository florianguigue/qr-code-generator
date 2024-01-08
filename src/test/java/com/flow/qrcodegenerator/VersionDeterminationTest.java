package com.flow.qrcodegenerator;

import com.flow.qrcodegenerator.enums.ErrorCorrectionLevel;
import com.flow.qrcodegenerator.enums.Mode;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VersionDeterminationTest {

    private static final Map<Mode, String> ALPHABET_MAP = Map.of(
            Mode.NUMERIC, "0123456789",
            Mode.ALPHANUMERIC, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:",
            Mode.BYTE, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz $%*+-./:=_{}[]()&^#@!?<>,';|~`±§"
    );

    @ParameterizedTest
    @ValueSource(strings = {
            "1:41:L:NUMERIC", "1:25:L:ALPHANUMERIC", "1:17:L:BYTE", //"1:10:L:KANJI",
            "1:34:M:NUMERIC", "1:20:M:ALPHANUMERIC", "1:14:M:BYTE", //"1:8:M:KANJI",
            //"1:27:Q:NUMERIC", "1:16:Q:ALPHANUMERIC", "1:11:Q:BYTE", "1:7:Q:KANJI",
            //"1:17:H:NUMERIC", "1:10:H:ALPHANUMERIC", "1:7:H:BYTE", "1:4:H:KANJI",
    })
    void shouldReturnVersion1(String input) {
        String[] params = input.split(":");
        int minSize = Integer.parseInt(params[0]);
        int maxSize = Integer.parseInt(params[1]);
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.valueOf(params[2]);
        Mode mode = Mode.valueOf(params[3]);

        String stringToEncode = getTextToEncode(minSize, maxSize, mode);
        int version = QrCodeUtils.determineVersion(stringToEncode.length(), mode, errorCorrectionLevel);
        assertEquals(1, version);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "42:77:L:NUMERIC", "26:47:L:ALPHANUMERIC", "18:32:L:BYTE", //"11:20:L:KANJI",
            "35:63:M:NUMERIC", "21:38:M:ALPHANUMERIC", "15:26:M:BYTE", //"9:16:M:KANJI",
            //"28:77:Q:NUMERIC", "17:47:Q:ALPHANUMERIC", "12:20:Q:BYTE", "8:12:Q:KANJI",
            //"18:34:H:NUMERIC", "11:20:H:ALPHANUMERIC", "8:14:H:BYTE", "5:8:H:KANJI",
    })
    void shouldReturnVersion2(String input) {
        String[] params = input.split(":");
        int minSize = Integer.parseInt(params[0]);
        int maxSize = Integer.parseInt(params[1]);
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.valueOf(params[2]);
        Mode mode = Mode.valueOf(params[3]);

        String stringToEncode = getTextToEncode(minSize, maxSize, mode);
        int version = QrCodeUtils.determineVersion(stringToEncode.length(), mode, errorCorrectionLevel);
        assertEquals(2, version);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "6744:7089:L:NUMERIC", "4088:4296:L:ALPHANUMERIC", "2810:2953:L:BYTE", //"1730:1817:L:KANJI",
            "5314:5596:M:NUMERIC", "3221:3391:M:ALPHANUMERIC", "2214:2331:M:BYTE", //"1393:1435:M:KANJI",
            //"3792:3993:Q:NUMERIC", "2299:2420:Q:ALPHANUMERIC", "1580:1663:Q:BYTE", "973:1024:Q:KANJI",
            //"2928:3057:H:NUMERIC", "1775:1852:H:ALPHANUMERIC", "1220:1273:H:BYTE", "751:784:H:KANJI",
    })
    void shouldReturnVersion40(String input) {
        String[] params = input.split(":");
        int minSize = Integer.parseInt(params[0]);
        int maxSize = Integer.parseInt(params[1]);
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.valueOf(params[2]);
        Mode mode = Mode.valueOf(params[3]);

        String stringToEncode = getTextToEncode(minSize, maxSize, mode);
        int version = QrCodeUtils.determineVersion(stringToEncode.length(), mode, errorCorrectionLevel);
        assertEquals(40, version);
    }

    private String getTextToEncode(int minSize, int maxSize, Mode mode) {
        String alphabet = ALPHABET_MAP.get(mode);
        int alphabetSize = alphabet.length();

        var r = new Random();

        int size = minSize != maxSize ? r.nextInt(maxSize - minSize) + minSize : minSize;

        String text = StringUtils.EMPTY;

        for (int i = 0; i < size; i++) {
            text = text.concat(String.valueOf(alphabet.charAt(r.nextInt(alphabetSize - 1))));
        }

        return text;
    }
}
