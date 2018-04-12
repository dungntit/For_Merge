package com.ttvnp.ttj_point_business_user_android_client.extensions

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import java.util.*

@Throws(WriterException::class)
fun QRCodeWriter.generateQRCodeImage(contents: String, size: Int): Bitmap {
    // https://qiita.com/alingogo/items/3006e5685057c23db6bd
    val encodeHint = Hashtable<EncodeHintType, Any>()

    encodeHint[EncodeHintType.CHARACTER_SET] = "utf8"

    // L 7%, M 15%, Q 25%, H 30%
    encodeHint[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.L

    val qrCodeData = this.encode(contents, BarcodeFormat.QR_CODE, size, size, encodeHint)

    val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
    bitmap.eraseColor(Color.argb(255, 255, 255, 255))
    for (x in 0 until qrCodeData.width) {
        for (y in 0 until qrCodeData.height) {
            if (qrCodeData.get(x, y)) {
                // 0 Black
                bitmap.setPixel(x, y, Color.argb(255, 0, 0, 0))
            } else {
                // -1 White
                bitmap.setPixel(x, y, Color.argb(255, 255, 255, 255))
            }
        }
    }

    return bitmap
}