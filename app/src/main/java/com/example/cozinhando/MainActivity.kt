package com.example.cozinhando

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.cozinhando.api.ApiData
import com.example.cozinhando.databinding.ActivityMainBinding
import com.example.cozinhando.list.ListAdapter
import com.example.cozinhando.list.ListData
import com.example.cozinhando.list.DadosIndicacaoAssociado

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listAdapter: ListAdapter
    private lateinit var listData: ListData
    var dataArrayList = ArrayList<ListData>()
    var indicaArrayList = ArrayList<DadosIndicacaoAssociado>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)



        Thread(Runnable {
            ApiData.downloadListaOficinas(applicationContext) { listData ->
                runOnUiThread {
                    dataArrayList = listData

                    listAdapter = ListAdapter(this@MainActivity, dataArrayList)
                    binding.listview.adapter = listAdapter
                    binding.listview.isClickable = true

                    binding.listview.onItemClickListener =
                        OnItemClickListener { adapterView, view, i, l ->
                            val intent = Intent(this@MainActivity, DetailedActivity::class.java)
                            intent.putExtra("Id", dataArrayList[i].Id)
                            startActivity(intent)
                        }

                }
            }
        }).start()


    }
}