package com.example.mvcdemo_kotlin.service


import com.example.mvcdemo_kotlin.utils.Glob
import com.example.mvcdemo_kotlin.utils.ProgressDialog

import android.content.Context
import android.util.Log

import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

public class Services {

    companion object {
        var call: retrofit2.Call<JsonObject>? = null
        private var service: Retrofit? = null
        private lateinit var okHttpClientBuilder: okhttp3.OkHttpClient.Builder
        private var mInstance: Services? = null
    }


    fun getInstance(): Services {

        okHttpClientBuilder = okhttp3.OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(Interceptor { chain ->
            val request = chain.request()
            val newRequest = request.newBuilder()
            chain.proceed(newRequest.build())
        })
        if (mInstance == null) {
            mInstance = Services()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            okHttpClientBuilder
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()

            service = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(ServiceConstants().BASE_URL)
                .client(okHttpClientBuilder.build())
                .build()
        }
        return mInstance as Services
    }

    fun calloperation(
        context: Context,
        headermap: Map<String, String>?,
        params: Map<String, String>?,
        listner: ServerOperationCompletion,
        url: String

    ) {

        if (Glob().isNetworkAvailable(context)) {
            val progressDialog = ProgressDialog(context)
            progressDialog.setCancelable(false)
            progressDialog.setCanceledOnTouchOutside(false)
             progressDialog.show()



            try {
                try {

                    val dispatcher: BackendLessService =service!!.create(BackendLessService::class.java)

                    if (url.equals(ServiceConstants.ItemEndPoint)) {
                        call = dispatcher.itemslist()
                    }

                    call!!.enqueue(object : retrofit2.Callback<JsonObject> {
                        override fun onResponse(
                            call: retrofit2.Call<JsonObject>,
                            response: Response<JsonObject>
                        ) {
                            if (response.code() == 200) {
                                listner.onResponseReceived(response.body(), url)
                                progressDialog.dismiss()

                            } else {
                                listner.onError(response.message(), url)
                                progressDialog.dismiss()
                            }
                        }

                        override fun onFailure(call: retrofit2.Call<JsonObject>, t: Throwable) {
                            Log.d("error", t.message + url)
                            listner.onError(t.message!!, url)
                            t.printStackTrace()
                            progressDialog.dismiss()


                        }

                    })
                } catch (e: Exception) {
                    progressDialog.dismiss()
                    Log.println(Log.ASSERT, "Server1 Excetion", "$e")
                }

            } catch (ioEx: IOException) {
                Log.d("ERROR", ioEx.message.toString())

            }
        } else {


            Glob().networkDoalig(context)

            listner.onError("Network Error", url)

        }

    }

    interface ServerOperationCompletion {
        fun onResponseReceived(responseData: JsonObject?, callType: String)
        fun onError(errorMessage: String, callType: String)

    }


}