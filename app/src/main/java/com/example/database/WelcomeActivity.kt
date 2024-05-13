package com.example.database

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringExtra(SignInActivity.KEY1)
        val pass = intent.getStringExtra(SignInActivity.KEY2)

        val wlcmTxt = findViewById<TextView>(R.id.tvName)
        wlcmTxt.text = "Welcome $name"

        val passTxt = findViewById<TextView>(R.id.tvPass)
        passTxt.text = "Your Password is $pass"

    }
}