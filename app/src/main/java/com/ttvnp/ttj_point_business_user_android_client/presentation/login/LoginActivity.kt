package com.ttvnp.ttj_point_business_user_android_client.presentation.login

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.ActivityLoginBinding
import com.ttvnp.ttj_point_business_user_android_client.presentation.sign_up.SignUpActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.tabs.TabsActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {
    @Inject lateinit var viewModel: LoginViewModel
    private val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login) as ActivityLoginBinding
    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (viewModel.isLoggedIn()) {
            startActivity(TabsActivity.intent(applicationContext))
        } else {
            binding.viewModel = viewModel

            binding.loginButton.setOnClickListener {
                viewModel.login { startActivity(TabsActivity.intent(applicationContext)) }
            }

            binding.gotoSignUpButton.setOnClickListener {
                startActivity(SignUpActivity.intent(applicationContext))
            }

            binding.emailEditText.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    val inputMethodMgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodMgr.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                }
            }

            binding.passwordEditText.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) {
                    val inputMethodMgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodMgr.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                }
            }
        }
    }

    override fun onDestroy() {
        viewModel.disposable.clear()
        super.onDestroy()
    }
}
