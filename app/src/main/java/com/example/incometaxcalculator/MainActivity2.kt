package com.example.incometaxcalculator

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val btnNext = findViewById<Button>(R.id.btnNext)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        toolbar.setTitle("Billzio")
//        toolbar.setTitleTextColor(Color.WHITE)
//        toolbar.setBackgroundColor(Color.parseColor("#FF018786"))
        setSupportActionBar(toolbar)

        btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
}