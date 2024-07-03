package com.example.cozinhando

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cozinhando.api.ApiIndicacao.Companion.sendEnviaIndicacao
import com.example.cozinhando.databinding.ActivityRatingBinding
import com.example.cozinhando.list.DadosIndicacao
import com.example.cozinhando.list.DadosIndicacaoAssociado
import com.example.cozinhando.login.LoadingSplashScreenActivity
import com.example.cozinhando.login.LoginIncorretoActivity

class RatingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRatingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        binding.buttonEnviar.setOnClickListener {

            //leitura dos valores
            val nomeAssociado = binding.etNomeAssociado.text.toString().trim()
            val emailAssociado = binding.etEmailAssociado.text.toString().trim()
            val cpf = binding.etCpf.text.toString().trim()
            val dataCriacao = binding.etData.text.toString().trim()
            val codigo = binding.etCodigoAssociado.text.toString().trim()
            val telefone = binding.etTelefoneAssociado.text.toString().trim()
            val placa = binding.etPlacaAssociado.text.toString().trim()
            val nomeAmigo = binding.etNomeAmigo.text.toString().trim()
            val telefoneAmigo = binding.etTelefoneAmigo.text.toString().trim()
            val emailAmigo = binding.etEmailAmigo.text.toString().trim()
            val indicacao = DadosIndicacaoAssociado(
                nomeAssociado = nomeAssociado,
                emailAssociado = emailAssociado,
                cpf = cpf,
                dataCriacao = dataCriacao,
                codigo = codigo,
                telefone = telefone,
                placa = placa,
                nomeAmigo = nomeAmigo,
                telefoneAmigo = telefoneAmigo,
                emailAmigo = emailAmigo,
            )
            val dadosIndicacao = DadosIndicacao(indicacao, remetente = "", copias = ArrayList())


            //teste das condições

            Thread {
                sendEnviaIndicacao(applicationContext, dadosIndicacao) { response ->
                    runOnUiThread {
                        Toast.makeText(
                            application,
                            "Formulário Enviado",
                            Toast.LENGTH_SHORT
                        ).show()

                        onBackPressedDispatcher.onBackPressed()
                    }
                }
            }.start()



            //limpeza dos dois elementos
            binding.etNomeAssociado.setText("")
            binding.etEmailAssociado.setText("")
            binding.etCpf.setText("")
            binding.etData.setText("")
            binding.etCodigoAssociado.setText("")
            binding.etTelefoneAssociado.setText("")
            binding.etPlacaAssociado.setText("")
            binding.etNomeAmigo.setText("")
            binding.etTelefoneAmigo.setText("")
            binding.etEmailAmigo.setText("")

        }


    }
}