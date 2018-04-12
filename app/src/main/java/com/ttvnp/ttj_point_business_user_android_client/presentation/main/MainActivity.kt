package com.ttvnp.ttj_point_business_user_android_client.presentation.main

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }
    private val email: String? by lazy { intent.extras.get(EMAIL_INTENT_KEY) as? String }
    private val password: String? by lazy { intent.extras.get(PASSWORD_INTENT_KEY) as? String }

    companion object {
        const val EMAIL_INTENT_KEY = "EMAIL_KEY"
        const val PASSWORD_INTENT_KEY = "PASSWORD_INTENT_KEY"
        fun intent(context: Context, email: String, password: String): Intent {
            return Intent(context, MainActivity::class.java).apply {
                putExtra(EMAIL_INTENT_KEY, email)
                putExtra(PASSWORD_INTENT_KEY, password)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.email = email
        binding.password = password
    }
}
