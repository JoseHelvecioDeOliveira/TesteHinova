package com.example.cozinhando.list

import org.json.JSONObject

class DadosIndicacaoAssociado (


    var nomeAssociado: String,
    var emailAssociado:String ,
    var cpf: String ,
    var dataCriacao:String,
    var codigo: String,
    var telefone: String ,
    var placa: String,
    var nomeAmigo:String,
    var telefoneAmigo: String,
    var emailAmigo: String,


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
        json.put("CodigoAssociacao", codigo)
        json.put("DataCriacao", dataCriacao)
        json.put("CpfAssociado", cpf)
        json.put("EmailAssociado", emailAssociado)
        json.put("NomeAssociado", nomeAssociado)
        json.put("TelefoneAssociado", telefone)
        json.put("PlacaVeiculoAssociado", placa)
        json.put("NomeAmigo", nomeAmigo)
        json.put("TelefoneAmigo", telefoneAmigo)
        json.put("EmailAmigo", emailAmigo)

        return json

    }
}