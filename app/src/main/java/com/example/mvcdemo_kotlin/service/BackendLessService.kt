package com.example.mvcdemo_kotlin.service

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface BackendLessService {

    @GET(ServiceConstants.ItemEndPoint)
    fun itemslist(): Call<JsonObject>

}