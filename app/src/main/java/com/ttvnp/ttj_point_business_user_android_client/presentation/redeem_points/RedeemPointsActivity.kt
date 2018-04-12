package com.ttvnp.ttj_point_business_user_android_client.presentation.redeem_points

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.ActivityRedeemPointsBinding
import com.ttvnp.ttj_point_business_user_android_client.presentation.qrcode.QRCodeActivity
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.inputmethod.InputMethodManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class RedeemPointsActivity : DaggerAppCompatActivity()  {

    @Inject lateinit var viewModel: RedeemPointsViewModel

    private val binding: ActivityRedeemPointsBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_redeem_points) as ActivityRedeemPointsBinding
    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, RedeemPointsActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }

    fun initViews() {
        binding.goToQRCodeButton.isEnabled = false
        binding.goToQRCodeButton.alpha = 0.3F
        binding.pointText.text = ""
    }

    fun setPointText(point: String) {
        binding.goToQRCodeButton.isEnabled = true
        binding.goToQRCodeButton.alpha = 1.0F
        binding.pointText.text = "${point}pt"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        binding.goToQRCodeButton.setOnClickListener {
            var value = binding.pointField.text.toString().toLong()
            viewModel.usePoint(value) { transaction ->

                var intent = Intent(applicationContext, QRCodeActivity::class.java)
                intent.putExtra("uuid", transaction.uuid)
                startActivity(intent)
            }

        }

        binding.pointField.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.pointField.windowToken,
                        InputMethodManager.RESULT_UNCHANGED_SHOWN)
                true
            }
            false
        }

        binding.pointField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString() == "") {
                    initViews()
                } else {
                    setPointText(p0.toString())
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        initViews()
    }

    override fun onDestroy() {
        viewModel.disposable.clear()
        super.onDestroy()
    }
}