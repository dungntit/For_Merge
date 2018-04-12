package com.ttvnp.ttj_point_business_user_android_client.presentation.activate

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.ActivityActivateBinding
import com.ttvnp.ttj_point_business_user_android_client.presentation.complete.CompleteActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class ActivateActivity : DaggerAppCompatActivity() {
    @Inject lateinit var viewModel: ActivateViewModel

    private val binding: ActivityActivateBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_activate) as ActivityActivateBinding
    }
    private val email: String by lazy { intent.extras.get(EMAIL_INTENT_KEY) as String }
    private val password: String by lazy { intent.extras.get(PASSWORD_INTENT_KEY) as String }

    companion object {
        const val EMAIL_INTENT_KEY = "EMAIL_KEY"
        const val PASSWORD_INTENT_KEY = "PASSWORD_KEY"
        fun intent(context: Context, email: String, password: String): Intent {
            return Intent(context, ActivateActivity::class.java).apply {
                putExtra(EMAIL_INTENT_KEY, email)
                putExtra(PASSWORD_INTENT_KEY, password)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.model.email = email
        viewModel.model.password = password
        viewModel.model.activationCode = ""

        binding.viewModel = viewModel

        binding.activationButton.setOnClickListener {
            viewModel.activate {
                viewModel.login {
                    startActivity(CompleteActivity.intent(applicationContext))
                }
            }
        }

        binding.activationCodeEditText.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val inputMethodMgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodMgr.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
    }

    override fun onDestroy() {
        viewModel.disposable.clear()
        super.onDestroy()
    }
}
