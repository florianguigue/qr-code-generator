package com.flow.qrcodegenerator;

import com.flow.qrcodegenerator.enums.ErrorCorrectionLevel;
import com.flow.qrcodegenerator.enums.Mode;

public class QrCodeGenerator {

    /**
     * Generate a {@link QrCode} from a string. If this method is called, we set the error correction level to L by default.
     * For a specific error correction level, please use {@link QrCodeGenerator#generateQrCodeFromString(String, ErrorCorrectionLevel)} instead.
     * @param stringToEncode as the string to be encoded into a {@link QrCode}
     * @return {@link QrCode}
     */
    public QrCode generateQrCodeFromString(String stringToEncode) {
        return generateQrCodeFromString(stringToEncode, ErrorCorrectionLevel.L);
    }

    /**
     * Generate a {@link QrCode} from a string and error correction level (L, M, Q, H).
     * @param stringToEncode as the string to be encoded into a {@link QrCode}
     * @param errorCorrectionLevel as the error correction level to be used in the encoding
     * @return {@link QrCode}
     */
    public QrCode generateQrCodeFromString(String stringToEncode, ErrorCorrectionLevel errorCorrectionLevel) {
        Mode mode = QrCodeUtils.determineMode(stringToEncode);

        return new QrCode(null, mode, errorCorrectionLevel, 1);
    }
}
