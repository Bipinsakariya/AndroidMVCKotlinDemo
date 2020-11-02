package com.example.mvcdemo_kotlin.utils

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import com.example.mvcdemo_kotlin.R
import java.util.regex.Pattern

class Glob {

    val pref: String = "MVC_DEMO"
    val Name: String = "name"
    val Email: String = "email"
    val Password: String = "password"
    val Phono: String = "phno"
    val isLogin: String = "login"

    fun emailValidtion(email: String): Boolean {
        val EMAIL_STRING =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val p = Pattern.compile(EMAIL_STRING)
        val m = p.matcher(email)
        return m.matches()
    }

    fun errorDialg(activity: Activity, message: String){
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("ok", DialogInterface.OnClickListener { dialog, id ->
                dialog.dismiss()
            })

        val alert = builder.create()
        //Setting the title manually
        alert.setTitle(R.string.dialog_title)
        alert.show()

        //alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.WHITE)
    }

}