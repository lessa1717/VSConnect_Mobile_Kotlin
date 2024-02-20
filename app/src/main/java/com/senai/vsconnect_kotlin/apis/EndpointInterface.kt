package com.senai.vsconnect_kotlin.apis

import com.google.gson.JsonObject
import com.senai.vsconnect_kotlin.models.Login
import com.senai.vsconnect_kotlin.models.Propaganda
import com.senai.vsconnect_kotlin.models.Servico
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.UUID

interface EndpointInterface {
    @GET("servico")
    fun listarPropagandas(): Call<List<Propaganda>>

    @POST("login")
    fun login(@Body usuario: Login): Call<JsonObject>

    @GET("servico/{idPropaganda}")
    fun buscarPropagandaPorID(@Path(value = "idPropaganda", encoded = true) idPropaganda: UUID): Call<JsonObject>

}

