package com.ttvnp.ttj_point_business_user_android_client.presentation.debug

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.ActivityDebugBinding
import com.ttvnp.ttj_point_business_user_android_client.presentation.login.LoginActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.tabs.TabsActivity


class DebugActivity : AppCompatActivity() {
    private val binding: ActivityDebugBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_debug) as ActivityDebugBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.goToLoginButton.setOnClickListener {
            startActivity(LoginActivity.intent(applicationContext))
        }
        binding.goToTabsButton.setOnClickListener {
            startActivity(TabsActivity.intent(applicationContext))
        }
    }
}
