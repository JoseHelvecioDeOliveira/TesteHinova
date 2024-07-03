package com.example.cozinhando.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.cozinhando.MainActivity
import com.example.cozinhando.SelectionActivity
import com.example.cozinhando.databinding.ActivityLoadingSplashScreenBinding

class LoadingSplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadingSplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoadingSplashScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, SelectionActivity::class.java)
            startActivity(intent)
            finish()
        },500)
    }
}