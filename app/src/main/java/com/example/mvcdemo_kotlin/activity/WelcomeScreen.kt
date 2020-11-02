package com.example.mvcdemo_kotlin.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.mvcdemo_kotlin.R
import com.example.mvcdemo_kotlin.utils.Glob
import kotlinx.android.synthetic.main.activity_welcome_screen.*

class WelcomeScreen : AppCompatActivity(), View.OnClickListener {
    private var sharepreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)
        sharepreferences = getSharedPreferences(Glob().pref, Context.MODE_PRIVATE)
        Init()


        Handler().postDelayed({

            intent = Intent(applicationContext, ItemDetails::class.java)
            startActivity(intent)
            finish()


        }, 1000)
    }

    private fun Init() {

        txt_welcome.text = "Welcome " + sharepreferences!!.getString(Glob().Name, "")
        btn_logout.setOnClickListener(this)

    }

    override fun onClick(view: View?) {

        when (view) {
            btn_logout -> {
                sharepreferences!!.edit().putBoolean(Glob().isLogin, false).commit()
                startActivity(Intent(this, LoginActivity::class.java))
                finishAffinity()
            }
        }


    }
}