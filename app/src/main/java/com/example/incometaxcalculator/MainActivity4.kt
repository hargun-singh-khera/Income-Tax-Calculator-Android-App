package com.example.incometaxcalculator

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class MainActivity4 : AppCompatActivity() {
    lateinit var userEmail: EditText
    lateinit var userPassword: EditText
    lateinit var loggingTextView: TextView
    lateinit var sharedPreferences: SharedPreferences

    val fileName="userDetails"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        this will set the toolbar title
        toolbar.setTitle("Log In")
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24)
//        this is used to add the toolbar in the Activity
        setSupportActionBar(toolbar)
//        this will enable the back button functionality

        userEmail = findViewById(R.id.userEmail)
        userPassword = findViewById(R.id.userPassword)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val loggingTextView = findViewById<TextView>(R.id.loggingTextView)

        btnLogin.setOnClickListener {
            if (userEmail.text.toString().isEmpty() || userPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Please provide your email and password to Login", Toast.LENGTH_SHORT).show()
            }
            else {
                loggingTextView.setText("Please wait...")
                progressBar.visibility= View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    loggingTextView.setText("Checking your credentials...")
                },800)
                Handler(Looper.getMainLooper()).postDelayed({
                    if (userAuthenticate()) {
                        loggingTextView.setText("Logging you in...")
                        val intent = Intent(this, MainActivity6::class.java)
                        startActivity(intent)
                    }
                    else {
                        loggingTextView.setText("Can't Login. Incorrect Login credentials!")
                    }
                    progressBar.visibility= View.INVISIBLE
                },3000)

            }
        }
    }
    fun userAuthenticate():Boolean {
        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val userEmail = userEmail.text.toString()
        val userPassword = userPassword.text.toString()
        val email = sharedPreferences.getString("Email", "").toString()
        val password = sharedPreferences.getString("Password", "").toString()
        return userEmail.equals(email) && userPassword.equals(password)
    }
}