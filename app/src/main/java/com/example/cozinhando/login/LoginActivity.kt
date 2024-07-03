package com.example.cozinhando.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cozinhando.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        binding.buttonLogin.setOnClickListener {

            //leitura dos valores
            val username = binding.login.text.toString().trim()
            val password = binding.senha.text.toString().trim()
            //teste das condições
            if (username.equals("user") && password.equals("pass")) {
                Toast.makeText(application, "Login válido", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, LoadingSplashScreenActivity::class.java))

            } else if (username.equals("") || password.equals("")) {
                Toast.makeText(
                    application,
                    "Preencha todos os campos para finalizar seu login",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    application,
                    "Login inválido, usuário ou senha incorreto",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(Intent(this, LoginIncorretoActivity::class.java))
            }
            //limpeza dos dois elementos
            binding.login.setText("")
            binding.senha.setText("")

        }


    }
}