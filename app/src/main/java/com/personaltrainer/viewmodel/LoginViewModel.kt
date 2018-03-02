package com.personaltrainer.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.personaltrainer.Auth
import com.personaltrainer.BR
import com.personaltrainer.BindableDelegate
import com.personaltrainer.NullableBindableDelegate

/**
 * Created by Honza on 26.02.2018.
 */
class LoginViewModel : BaseObservable() {

    @get:Bindable
    var email: String by BindableDelegate("", BR.email)

    @get:Bindable
    var password: String by BindableDelegate("", BR.password)

    @get:Bindable
    var emailError: String? by NullableBindableDelegate(null, BR.emailError)

    @get:Bindable
    var passwordError: String? by NullableBindableDelegate(null, BR.passwordError)

    private fun isEmailValid(email: String) = android.util.Patterns.EMAIL_ADDRESS
            .matcher(email).matches()

    private fun isPasswordValid(password: String) = password.isNotEmpty()

    private fun validateEmail(email: String) : String? = if (isEmailValid(email)) null
        else "Email format is not valid."

    private fun validatePassword(password: String) : String? = if (isPasswordValid(password)) null
        else "Password can't be empty string."

    private fun checkCredentials(email: String, password: String) : Boolean {
        return isEmailValid(email) && isPasswordValid(password)
    }

    fun onLoginButtonPress(action: (success: Boolean) -> Unit) {
        validateEmail(email)
        validatePassword(password)
        if (checkCredentials(email, password)) {
            Auth.signInWithEmailAndPassword(email, password, {
                action(it)
            })
        }
        email = ""
        password = ""
    }
}