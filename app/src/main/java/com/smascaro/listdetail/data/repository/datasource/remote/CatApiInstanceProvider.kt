package com.smascaro.listdetail.data.repository.datasource.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CatApiInstanceProvider {
    private var instance: CatService? = null
    fun getApi(): CatService {
        if (instance == null) {
            val okHttpClient = OkHttpClient.Builder().apply {
                addInterceptor {
                    val originalRequest = it.request()
                    val request = originalRequest.newBuilder().apply {
                        header("X-Api-Key", CAT_API_KEY)
                        method(originalRequest.method(), originalRequest.body())
                    }.build()
                    it.proceed(request)
                }

            }.build()
            val retrofit = Retrofit.Builder().apply {
                baseUrl(CAT_API_BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
            }.build()
            instance = retrofit.create(CatService::class.java)
        }
        return instance!!
    }
}