package com.ttvnp.ttj_point_business_user_android_client.presentation.qrcode

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.ActivityQrcodeBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException

import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.EncodeHintType

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import com.ttvnp.ttj_point_business_user_android_client.presentation.redeem_points.RedeemPointsActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.tabs.TabEnum
import com.ttvnp.ttj_point_business_user_android_client.presentation.tabs.TabsActivity
import com.ttvnp.ttj_point_business_user_android_client.extensions.generateQRCodeImage
import java.util.*








class QRCodeActivity : AppCompatActivity() {
    private val binding: ActivityQrcodeBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_qrcode) as ActivityQrcodeBinding
    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, QRCodeActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.doneButton.setOnClickListener {
            val intent = Intent(applicationContext, TabsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("currentTab", TabEnum.WALLET.ordinal)
            startActivity(intent)
        }

        try {
            if (intent.hasExtra("uuid")) {
                val uuid = intent.extras.get("uuid") as String
                val codeStr = "type=use&transaction_id=$uuid"
                val image = QRCodeWriter().generateQRCodeImage(codeStr,480)
                binding.qrCodeImageView.setImageBitmap(image)
            }
        } catch (e: WriterException) {
            Log.e("ttj", "WriterException", e)
            binding.qrCodeImageView.setImageResource(0)
            return
        }
    }
}

