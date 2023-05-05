package com.example.movies_app.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.movies_app.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Thread.sleep(3000)
        enableDarkMode()
        splashScreen.setKeepOnScreenCondition{ true }
        val intent = Intent(this, MovieActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }
}