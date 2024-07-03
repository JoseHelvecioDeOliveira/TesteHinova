package com.example.cozinhando.list

import org.json.JSONArray
import org.json.JSONObject

class DadosIndicacao (


    var indicacao: DadosIndicacaoAssociado,
    var remetente:String ,
    var copias: ArrayList<String> ,



    ){
    fun toJsonObject(): JSONObject {
        val json = JSONObject()

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
                   */

        //salvar todas as copias
        val jsonArrayCopias: JSONArray = JSONArray()

        for(i in 0..< copias.size){
            jsonArrayCopias.put(copias[i])
        }


        json.put("Indicacao", indicacao.toJsonObject())
        json.put("Remetente", remetente)
        json.put("Copias", jsonArrayCopias)


        return json

    }
}