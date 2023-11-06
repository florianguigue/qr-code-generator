package com.flow.qrcodegenerator;

import com.flow.qrcodegenerator.enums.ErrorCorrectionLevel;
import com.flow.qrcodegenerator.enums.Mode;
import com.flow.qrcodegenerator.enums.Version;

/**
 * Representation of the com.flow.qrcodegenerator.QrCode and its information.
 * @param content The binary content of the com.flow.qrcodegenerator.QrCode.
 * @param mode The mode used to encode the com.flow.qrcodegenerator.QrCode (Numeric, Alphanumeric, Byte or Kanji). Automatically determined.
 * @param errorCorrectionLevel The error correction level used to encode the com.flow.qrcodegenerator.QrCode (L, M, Q, H). Default : L.
 * @param version The version used to encode the com.flow.qrcodegenerator.QrCode. Automatically determined.
 */
public record QrCode(byte[] content,

                     Mode mode,

                     ErrorCorrectionLevel errorCorrectionLevel,

                     Version version) { }
