package com.flow.qrcodegenerator;

import com.flow.qrcodegenerator.enums.Mode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModeDeterminationTest {

    @Test
    void shouldReturnNumericMode() {
        assertEquals(Mode.NUMERIC, QrCodeUtils.determineMode("123456789"));
    }

    @Test
    void shouldReturnAlphanumericMode() {
        assertEquals(Mode.ALPHANUMERIC, QrCodeUtils.determineMode("HELLO WORLD$%*+-./: "));
    }

    @Test
    void shouldReturnByteMode() {
        assertEquals(Mode.BYTE, QrCodeUtils.determineMode("http://www.unsecureurlandijtoldme.com"));
    }
}
