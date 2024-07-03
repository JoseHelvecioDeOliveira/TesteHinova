package com.example.cozinhando.api

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.cozinhando.list.ListData
import kotlin.io.encoding.ExperimentalEncodingApi


class ApiData {
    companion object {
        var listaOficinas: ArrayList<ListData> = arrayListOf()


        fun convertStringToBitmap(base64Str: String?): Bitmap? {
            val decodedString = Base64.decode(base64Str, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        }

        @OptIn(ExperimentalEncodingApi::class)
        fun downloadListaOficinas(context: Context, respCallback: (ArrayList<ListData>) -> Unit = {}) {
            val url =
                "http://app.hinovamobile.com.br/ProvaConhecimentoWebApi/Api/Oficina?codigoAssociacao=601&cpfAssociado=\"\""
            val queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                Response.Listener { response ->

                    /*
                    {
                        "ListaOficinas": [
                                {
                                    "Id": 1,
                                    "Nome": "Oficina Gecar",
                                    "Descricao": "A oficina Gecar presta serviços em Betim à mais de 30 anos, mantendo sempre a qualidade, respeito e transparência com seus clientes. Atuamos na área de funilaria e pintura, garantindo sempre sua satisfação em relação aos reparos em seu veículo, seja ele, usado ou semi novo, importado ou nacional. A garantia de nossos serviços é de 3 anos, tudo para garantirmos que nossos clientes se sintam sempre tranqüilos e satisfeitos. Serviços prestados. \\n-Funilaria\\n-Pintura\\n-Limpeza geral do veículo\\n-Serviços de mecânica\\n-Revitalização de pintura (contratação à parte)",
                                    "DescricaoCurta": "Lanternagem e Pintura",
                                    "Endereco": "Rua Ignês Maria, 326 - Betim Industrial",
                                    "Latitude": "-19.9622435",
                                    "Longitude": "-44.175102",
                                    "AvaliacaoUsuario": 0,
                                    "CodigoAssociacao": 601,
                                    "Email": "contato@hinovamobile.com.br",
                                    "Telefone1": "31-34193100",
                                    "Telefone2": null,
                                    "Ativo": true
                    */
                    val dataArrayList = ArrayList<ListData>()


                    val listaOficinasArray = response.getJSONArray("ListaOficinas")
                    for (i in 0..<listaOficinasArray.length()){
                        val itenOficina = listaOficinasArray.getJSONObject(i)
                        println(itenOficina.toString())

                        val id = itenOficina.getInt("Id")
                        val nome = itenOficina.getString("Nome")
                        val descricao = itenOficina.getString("Descricao")
                        val descricaocurta = itenOficina.getString("DescricaoCurta")
                        val endereco = itenOficina.getString("Endereco")
                        val latitude = itenOficina.getString("Latitude")
                        val longitude = itenOficina.getString("Longitude")
                        val contato = itenOficina.getString("Telefone1")
                        val avaliacaousuario = itenOficina.getString("AvaliacaoUsuario")
                        val codigoassiciacao = itenOficina.getString("CodigoAssociacao")
                        val base64logo = itenOficina.getString("Foto")
                        val email = itenOficina.getString("Email")

                        val bmpLogo = convertStringToBitmap(base64logo)


                        dataArrayList.add(ListData(nome, endereco, descricao, contato, bmpLogo, id, descricaocurta, latitude, longitude, avaliacaousuario, codigoassiciacao, email))
                    }

                    listaOficinas = dataArrayList

                    respCallback(dataArrayList)


                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                    error.printStackTrace()
                }
            )

            // Add the request to the RequestQueue.
            queue.add(jsonObjectRequest)
        }

        fun buscarOficina(id:Int): ListData?{
            return listaOficinas.find { item -> item.Id == id }
        }
    }
}