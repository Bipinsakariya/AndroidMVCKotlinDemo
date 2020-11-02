package com.example.mvcdemo_kotlin.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mvcdemo_kotlin.R
import com.example.mvcdemo_kotlin.utils.Glob

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val sharepreferences = getSharedPreferences(Glob().pref, Context.MODE_PRIVATE)
        val islogin = sharepreferences.getBoolean(Glob().isLogin,false)

        if(islogin){
            startActivity(Intent(this,WelcomeScreen::class.java))
            finish()
        }else{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }
}