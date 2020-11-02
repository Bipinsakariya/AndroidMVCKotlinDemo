package com.example.mvcdemo_kotlin.service

class ServiceConstants {

    val BASE_URL = "https://api.stackexchange.com"

    companion object {
        const val  ItemEndPoint = "/2.2/questions?order=desc&sort=creation&site=stackoverflow"
    }

}