package com.example.mvcdemo_kotlin.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvcdemo_kotlin.R
import com.example.mvcdemo_kotlin.utils.Glob
import kotlinx.android.synthetic.main.activity_signup_screen.*

class SignupScreen : AppCompatActivity(), View.OnClickListener {
    private var editor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen)
        val sharepreferences = getSharedPreferences(Glob().pref, Context.MODE_PRIVATE)
        editor = sharepreferences!!.edit()
        Init()
    }

    private fun Init() {
        btn_signUp.setOnClickListener(this)
        txt_backtologin.setOnClickListener(this)

        contrycode.setCountryForNameCode("In")
        contrycode.registerCarrierNumberEditText(ed_phno)
        contrycode.isValidFullNumber
    }

    override fun onClick(view: View?) {
        when (view) {
            btn_signUp -> {
                if (isValidation()) {
                    savedata()
                }
            }
            txt_backtologin->{
                onBackPressed()
            }

        }

    }

    private fun savedata() {
        editor!!.putBoolean(Glob().isLogin,true)
        editor!!.putString(Glob().Name,ed_first_name.text.toString())
        editor!!.putString(Glob().Password,ed_password.text.toString())
        editor!!.putString(Glob().Email,ed_email.text.toString())
        editor!!.commit()

        val intent = Intent(this,WelcomeScreen::class.java)
        startActivity(intent)
        finishAffinity()

    }

    private fun isValidation(): Boolean {
        if (ed_first_name.text.isNullOrEmpty()) {
            ed_first_name.requestFocus()
            ed_first_name.setError(resources.getString(R.string.empty_name))
            return false
        } else if (ed_email.text.isNullOrEmpty()) {
            ed_email.requestFocus()
            ed_email.setError(resources.getString(R.string.empty_email))
            return false
        } else if (ed_password.text.isNullOrEmpty()) {
            ed_password.requestFocus()
            ed_password.setError(resources.getString(R.string.empty_password))
            return false
        } else if (!Glob().emailValidtion(ed_email.text.toString())) {
            ed_email.requestFocus()
            ed_email.error = resources.getString(R.string.errorValidEmail)
            return false
        } else if (ed_phno.text.isNullOrEmpty()) {
            ed_phno.requestFocus()
            ed_phno.error = resources.getString(R.string.errorPhno)
            return false
        } else if (!contrycode.isValidFullNumber) {
            ed_phno.requestFocus()
            ed_phno.error = resources.getString(R.string.errorpno)
            return false
        } else {
            return true
        }


    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}