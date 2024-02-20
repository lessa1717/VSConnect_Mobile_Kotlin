package com.senai.vsconnect_kotlin.models

import java.util.UUID

class Propaganda(
    val id : UUID,
    val nome: String,
    val descricao: String,
    val url_img: String,
    val duracao_parceria: String,
    val horario:String
)