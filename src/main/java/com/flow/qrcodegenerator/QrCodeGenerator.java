package com.flow.qrcodegenerator;

import com.flow.qrcodegenerator.enums.ErrorCorrectionLevel;
import com.flow.qrcodegenerator.enums.Mode;

import java.nio.charset.StandardCharsets;

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
        int version = QrCodeUtils.determineVersion(stringToEncode.length(), mode, errorCorrectionLevel);

        String encodedData = mode.getModeByteEncoding();
        encodedData += Integer.toBinaryString(stringToEncode.length());

        return new QrCode(encodedData.getBytes(StandardCharsets.UTF_8), mode, errorCorrectionLevel, version);
    }
}
