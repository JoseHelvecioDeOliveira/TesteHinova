package com.example.cozinhando.api

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.cozinhando.list.DadosIndicacao
import com.example.cozinhando.list.ListData
import com.example.cozinhando.list.DadosIndicacaoAssociado
import com.example.cozinhando.list.RespostaDadosIndicacao
import org.json.JSONObject
import kotlin.io.encoding.ExperimentalEncodingApi


class ApiIndicacao {
    companion object {
        var enviaIndicacao: ArrayList<DadosIndicacaoAssociado> = arrayListOf()


        @OptIn(ExperimentalEncodingApi::class)
        fun sendEnviaIndicacao(context: Context, dadosIndicacao: DadosIndicacao, respCallback: (RespostaDadosIndicacao) -> Unit = {}) {
            val url =
                "http://app.hinovamobile.com.br/ProvaConhecimentoWebApi/Api/Indicacao"
            val queue = Volley.newRequestQueue(context)

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.POST, url, dadosIndicacao.toJsonObject(),
                Response.Listener { response ->

                    /*
                    {
                              "Indicacao":{
                                "CodigoAssociacao":"601",
                                "DataCriacao":"2016-10-30",
                                "CpfAssociado":"123123",
                                "EmailAssociado":"alksjd",
                                "NomeAssociado":"Teste",
                                "TelefoneAssociado":"asd",
                                "PlacaVeiculoAssociado":"asd",
                                "NomeAmigo":"asd",
                                "TelefoneAmigo":"asd",
                                "EmailAmigo":"asd"

                              },
                              "Remetente":"romulo.marques@hinovamobile.com.br",
                              "Copias":[]

                            }


                            {
                            "RetornoErro": {
                                "retornoErro": null
                            },
                            "Sucesso": "Indicacao enviada com sucesso!"
                        }
                    */


                    //Acessa o pai, armazena na variavel e utiliza ela para acessar os pais e ai por diante para depois criar uma variável e retornar
                    val retornoError = response.getJSONObject("RetornoErro")
                    val retornoResposta = retornoError.getString("retornoErro")
                    val sucesso = response.getString("Sucesso")

                    val resposta = RespostaDadosIndicacao(sucesso, retornoResposta)

                    respCallback(resposta)


                },
                Response.ErrorListener { error ->
                    // TODO: Handle error
                    error.printStackTrace()
                }
            )

            // Add the request to the RequestQueue.
            queue.add(jsonObjectRequest)
        }

    }
}