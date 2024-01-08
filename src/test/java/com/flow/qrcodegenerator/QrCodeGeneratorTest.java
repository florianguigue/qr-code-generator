package com.flow.qrcodegenerator;

import com.flow.qrcodegenerator.enums.ErrorCorrectionLevel;
import com.flow.qrcodegenerator.enums.Mode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QrCodeGeneratorTest {

    private final QrCodeGenerator qrCodeGenerator = new QrCodeGenerator();

    @Test
    void shouldGenerateQrCode() {
        QrCode qrCode = qrCodeGenerator.generateQrCodeFromString("HELLOWORLD");
        assertEquals(Mode.ALPHANUMERIC, qrCode.mode());
        assertEquals(1, qrCode.version());
        assertEquals(ErrorCorrectionLevel.L, qrCode.errorCorrectionLevel());
    }
}
