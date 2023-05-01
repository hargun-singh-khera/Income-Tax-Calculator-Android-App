package com.example.incometaxcalculator

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text
import java.util.Calendar

class MainActivity5 : AppCompatActivity() {
    lateinit var userName: EditText
    lateinit var userEmail: EditText
    lateinit var userPhoneNumber: EditText
    lateinit var userDOB: EditText
    lateinit var userPassword: EditText
    lateinit var rgrp:RadioGroup
    lateinit var loggingTextView:TextView
    lateinit var progressBar:ProgressBar

    val fileName = "userDetails"
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        this will set the toolbar title
        toolbar.setTitle("Create Account")
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24)
//        this is used to add the toolbar in the Activity
        setSupportActionBar(toolbar)


        userName = findViewById(R.id.userName)
        userEmail = findViewById(R.id.userEmail)
        userPhoneNumber = findViewById(R.id.userPhoneNumber)
        userDOB = findViewById(R.id.userDOB)
        userPassword = findViewById(R.id.userPassword)
        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE)

        progressBar = findViewById(R.id.progressBar)
        loggingTextView = findViewById(R.id.loggingTextView)
        val btnLetsGetStarted = findViewById<Button>(R.id.btnLetsGetStarted)
        rgrp = findViewById(R.id.rgrp)

        userDOB.setOnClickListener {
            showDatePicker()

        }

        btnLetsGetStarted.setOnClickListener {

            userCreationWithValidationChecks()
        }

    }

    fun checkRadioButtonSelectedOrNot():Boolean {
        val selectedId = rgrp.checkedRadioButtonId
        if (selectedId==-1) {
            Toast.makeText(this, "Please provide your gender to continue", Toast.LENGTH_SHORT).show()
            return false
        }
        else {
            val rB = findViewById<RadioButton>(selectedId)
            return true
        }
    }

    fun showDatePicker() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            userDOB.setText("$dayOfMonth/${month + 1}/$year")
        }, year, month, day)
        datePicker.show()
    }

    fun userCreationWithValidationChecks() {
        if (userName.text.toString().isEmpty() || userEmail.text.toString().isEmpty() || userPhoneNumber.text.toString().isEmpty()
            || userDOB.text.toString().isEmpty() || userPassword.text.toString().isEmpty()) {
            Toast.makeText(this, "Please provide all the required fields", Toast.LENGTH_SHORT).show()
        }
        else if(userPhoneNumber.text.toString().length != 10) {
            Toast.makeText(this, "Please enter a valid mobile number", Toast.LENGTH_SHORT).show()
        }
        else {
            if (checkRadioButtonSelectedOrNot()) {
                saveUserDetails()
                loggingTextView.setText("Creating your account...")
                progressBar.visibility= View.VISIBLE
                Handler(Looper.getMainLooper()).postDelayed({
                    loggingTextView.setText("Redirecting...")
                    progressBar.visibility=View.INVISIBLE
                    val intent = Intent(this, MainActivity4::class.java)
                    startActivity(intent)
                },2000)
            }

        }
    }

    fun saveUserDetails() {
        val userName = userName.text.toString()
        val userEmail = userEmail.text.toString()
        val userPhoneNumber = userPhoneNumber.text.toString()
        val userDOB = userDOB.text.toString()
        val userPassword = userPassword.text.toString()
        val edit = sharedPreferences.edit()

        edit.putString("Name", userName)
        edit.putString("Email", userEmail)
        edit.putString("Mobile Number", userPhoneNumber)
        edit.putString("DOB", userDOB)
        edit.putString("Password", userPassword)

        edit.apply()
    }

}