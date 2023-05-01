package com.example.incometaxcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//      used for delay of splash screen activity
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
            finish()
        },1000)

    }
}