package com.flow.qrcodegenerator;

import com.flow.qrcodegenerator.enums.ErrorCorrectionLevel;
import com.flow.qrcodegenerator.enums.Mode;

/**
 * Representation of the QrCode and its configuration (Mode, Error correction level and version).
 * @param content The binary content of the QrCode.
 * @param mode The mode used to encode the QrCode (Numeric, Alphanumeric, Byte or Kanji). Automatically determined.
 * @param errorCorrectionLevel The error correction level used to encode the QrCode (L, M, Q, H). Default : L.
 * @param version The version used to encode the QrCode. Automatically determined.
 */
public record QrCode(byte[] content,

                     Mode mode,

                     ErrorCorrectionLevel errorCorrectionLevel,

                     int version) {
    public QrCode(byte[] content, Mode mode, ErrorCorrectionLevel errorCorrectionLevel, int version) {
        this.content = content;
        this.mode = mode;
        this.errorCorrectionLevel = errorCorrectionLevel;

        if (version < 1 || version > 40) {
            throw new IllegalArgumentException("Accepted versions: 1 to 40. Version received: %d".formatted(version));
        }

        this.version = version;
    }
}
