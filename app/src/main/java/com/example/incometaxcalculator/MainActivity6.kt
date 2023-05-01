package com.example.incometaxcalculator

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity6 : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle("Home")
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        replaceFrameWithFragment(HomeFragment())
        bottomNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    replaceFrameWithFragment(HomeFragment())
                    toolbar.setTitle("Home")
                }
                R.id.github -> openGitHub()
                R.id.rate -> {
                    replaceFrameWithFragment(RateFragment())
                    toolbar.setTitle("Rate Us")
                }
                R.id.share-> shareApp()
                R.id.exit -> exit()
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.home -> {
                replaceFrameWithFragment(HomeFragment())
                toolbar.setTitle("Home")
            }
            R.id.github -> openGitHub()
            R.id.rate -> {
                replaceFrameWithFragment(RateFragment())
                toolbar.setTitle("Rate Us")
            }
            R.id.share-> shareApp()
            R.id.exit -> exit()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        exit()
    }

    fun replaceFrameWithFragment(frag: Fragment){
        val fragManager = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.frameLayout, frag)
        fragTransaction.commit()
    }

    fun openGitHub() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("https://github.com/hargun-singh-khera/Income-Tax-Calculator-Android-App"))
        startActivity(intent)
    }

    fun shareApp() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,"https://drive.google.com/file/d/1w2qX_xCYExfOx-o8JAylNin2RWUo5MJv/view?usp=sharing")
        startActivity(Intent.createChooser(intent, "Share Link!"))
    }

    fun exit() {
        val builder = AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle("Confirm Exit")
            .setMessage("Are you sure you want to exit?")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            })
            .setNegativeButton("Exit", DialogInterface.OnClickListener { dialog, which ->
                finishAffinity()
            })
        builder.create().show()
    }
}