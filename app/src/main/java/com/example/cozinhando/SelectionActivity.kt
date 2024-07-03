package com.example.cozinhando

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cozinhando.databinding.ActivityDetailedBinding
import com.example.cozinhando.databinding.ActivityLoginBinding
import com.example.cozinhando.databinding.ActivityRatingBinding
import com.example.cozinhando.databinding.ActivitySelectionBinding
import com.example.cozinhando.login.LoadingSplashScreenActivity

class SelectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)


        binding.buttonListarOficinas.setOnClickListener {

            val intent = this.intent

            Toast.makeText(application, "Bem-Vindo a listagem de Oficinas", Toast.LENGTH_SHORT).show()

            val i = Intent(this, MainActivity::class.java)

            startActivity(i)

        }
        binding.buttonIrIndicacao.setOnClickListener {
            Toast.makeText(application, "Bem-Vindo a tela de indicação de amigo", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, RatingActivity::class.java))
        }
    }
}