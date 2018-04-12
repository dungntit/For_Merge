package com.ttvnp.ttj_point_business_user_android_client.presentation.complete

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.ActivityCompleteBinding
import com.ttvnp.ttj_point_business_user_android_client.presentation.tabs.TabsActivity
import dagger.android.support.DaggerAppCompatActivity

class CompleteActivity : DaggerAppCompatActivity() {
    private val binding: ActivityCompleteBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_complete) as ActivityCompleteBinding
    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, CompleteActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)

        binding.startButton.setOnClickListener {
            startActivity(TabsActivity.intent(applicationContext))
        }

    }
}
