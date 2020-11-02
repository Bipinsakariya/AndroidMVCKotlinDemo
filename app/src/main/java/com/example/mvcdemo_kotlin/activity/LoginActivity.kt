package com.example.mvcdemo_kotlin.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.mvcdemo_kotlin.R
import com.example.mvcdemo_kotlin.utils.Glob
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , View.OnClickListener {
    private var sharepreferences: SharedPreferences?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharepreferences = getSharedPreferences(Glob().pref, Context.MODE_PRIVATE)
        init()
    }

    private fun init() {
        btn_login.setOnClickListener(this)
        txt_signup.setOnClickListener(this)


    }

    override fun onClick(view: View?) {
        when (view) {
           btn_login -> {
               if(isValidation()){
                    if(sharepreferences!!.getString(Glob().Email,"").equals(ed_email.text.toString()) &&
                        sharepreferences!!.getString(Glob().Password,"").equals(ed_password.text.toString())){
                        sharepreferences!!.edit()!!.putBoolean(Glob().isLogin,true).apply()
                        val intent = Intent(this,WelcomeScreen::class.java)
                        startActivity(intent)
                        finishAffinity()

                    }else {
                        Glob().errorDialg(this,"Your account is not exits so please create a new account.")
                    }
               }

            }
            txt_signup->{
                startActivity(Intent(this,SignupScreen::class.java))
            }

        }

    }

    private fun isValidation(): Boolean {

        if(ed_email.text.isNullOrEmpty()){
            ed_email.requestFocus()
            ed_email.setError(resources.getString(R.string.empty_email))
            return false
        }else if(ed_password.text.isNullOrEmpty()){
            ed_password.requestFocus()
            ed_password.setError(resources.getString(R.string.empty_password))
            return false
        }else if(!Glob().emailValidtion(ed_email.text.toString())){
            ed_email.requestFocus()
            ed_email.error = resources.getString(R.string.errorValidEmail)
            return false
        }
        else{
            return true
        }

    }
}