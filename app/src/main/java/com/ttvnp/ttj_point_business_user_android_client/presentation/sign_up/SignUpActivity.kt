package com.ttvnp.ttj_point_business_user_android_client.presentation.sign_up

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.ActivitySignUpBinding
import com.ttvnp.ttj_point_business_user_android_client.presentation.activate.ActivateActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.login.LoginActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.tabs.TabsActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager


class SignUpActivity : DaggerAppCompatActivity() {
    @Inject lateinit var viewModel: SignUpViewModel

    private val binding: ActivitySignUpBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_sign_up) as ActivitySignUpBinding
    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, SignUpActivity::class.java).apply {
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

            binding.gotoLoginButton.setOnClickListener {
                startActivity(LoginActivity.intent(applicationContext))
            }

            binding.signUpButton.setOnClickListener {
                viewModel.signUp { startActivity(ActivateActivity.intent(applicationContext, viewModel.model.email, viewModel.model.password)) }
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