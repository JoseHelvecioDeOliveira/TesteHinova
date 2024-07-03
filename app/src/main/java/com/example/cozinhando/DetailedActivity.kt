package com.example.cozinhando

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.cozinhando.api.ApiData
import com.example.cozinhando.databinding.ActivityDetailedBinding

class DetailedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)


        val intent = this.intent
        if (intent != null) {
//            val name = intent.getStringExtra("name")
//            val endereco = intent.getStringExtra("endereco")
//            val sobre = intent.getIntExtra("sobre", R.string.alemanhaDesc)
//            val contato = intent.getIntExtra("contato", R.string.contatoAlemanha)
//            val image = intent.getIntExtra("image", R.drawable.oficinaalemanha)

            val Id = intent.getIntExtra("Id", 0)
            val oficina = ApiData.buscarOficina(Id)


            binding.detailName.text = oficina?.name ?: ""
            binding.detailEndereco.text = oficina?.endereco ?: ""
            binding.detailDescricao.setText(oficina?.descricao ?: "")
            binding.detailContato.setText(oficina?.contato ?: "Não possui telefone")
            binding.detailLatitude.setText(oficina?.latitude ?: "")
            binding.detailLongitude.setText(oficina?.longitude ?: "")
            binding.detailBreveDescricao.setText(oficina?.descricaocurta ?: "")
            binding.detailCodigo.setText(oficina?.codigoassiciacao ?: "")
            binding.detailGmail.setText(oficina?.email ?: "")
            if (oficina != null) {
                binding.detailImage.setImageBitmap(oficina.image)
            }


        }
    }
}