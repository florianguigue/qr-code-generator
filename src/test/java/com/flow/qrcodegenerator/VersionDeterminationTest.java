package com.flow.qrcodegenerator;

import com.flow.qrcodegenerator.enums.Mode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VersionDeterminationTest {

    @Test
    void shouldReturnVersion1() {
        String stringToEncode = "123456789";
        int version = QrCodeUtils.determineVersion(stringToEncode, Mode.NUMERIC);
        assertEquals(1, version);
    }

    @Test
    void shouldReturnVersion2() {
        String stringToEncode = "01234567890123456789012345678901234567890123456789";
        int version = QrCodeUtils.determineVersion(stringToEncode, Mode.NUMERIC);
        assertEquals(2, version);
    }

    @Test
    void shouldReturnVersion3() {
        String stringToEncode = "921857567626346509436793933886203573313809341237048029233979584008685170985487409312243822800544791064452127075042130268395517";
        int version = QrCodeUtils.determineVersion(stringToEncode, Mode.NUMERIC);
        assertEquals(3, version);
    }
}
