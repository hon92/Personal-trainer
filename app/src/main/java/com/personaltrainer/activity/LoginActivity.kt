package com.personaltrainer.activity

import android.app.ProgressDialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.personaltrainer.Auth
import com.personaltrainer.R
import com.personaltrainer.databinding.ActivityLoginBinding
import com.personaltrainer.viewmodel.LoginViewModel

/**
 * Created by Honza on 24.02.2018.
 */
class LoginActivity : AppCompatActivity() {
    private lateinit var progressDialog: ProgressDialog
    private val loginViewModel: LoginViewModel = LoginViewModel()

    val binding: ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView<ActivityLoginBinding>(this,
                R.layout.activity_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(this)
        progressDialog.isIndeterminate = true
        progressDialog.setTitle("Authenticating")
        progressDialog.setMessage("Please wait")
        progressDialog.setCancelable(false)
        progressDialog.setOnCancelListener { _ -> finish() }

        loginViewModel.email = "janhomola92@gmail.com"
        loginViewModel.password = "password"
        binding.loginViewModel = loginViewModel
        binding.onLoginButtonClickListener = View.OnClickListener {
            progressDialog.show()
            loginViewModel.onLoginButtonPress({
                progressDialog.dismiss()
                if (it) {
                    redirectToMainActivity()
                }
            })
        }
        if (Auth.isSigned()) {
            redirectToMainActivity()
        }
    }

    private fun redirectToMainActivity() {
        progressDialog.dismiss()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}