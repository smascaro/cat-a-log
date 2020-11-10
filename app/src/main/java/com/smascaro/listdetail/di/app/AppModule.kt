package com.smascaro.listdetail.di.app

import com.smascaro.listdetail.data.repository.datasource.remote.CAT_API_BASE_URL
import com.smascaro.listdetail.data.repository.datasource.remote.CAT_API_KEY
import com.smascaro.listdetail.data.repository.datasource.remote.CatService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Provides
    @OkHttpClientForCatService
    fun provideOkHttpClientForCatService(): OkHttpClient {
        val client = OkHttpClient.Builder().apply {
            addInterceptor {
                val originalRequest = it.request()
                val request = originalRequest.newBuilder().apply {
                    header("X-Api-Key", CAT_API_KEY)
                    method(originalRequest.method(), originalRequest.body())
                }.build()
                it.proceed(request)
            }
        }
        return client.build()
    }

    @Provides
    @RetrofitForCatService
    fun provideRetrofitInstance(@OkHttpClientForCatService httpClient: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder().apply {
            baseUrl(CAT_API_BASE_URL)
            client(httpClient)
            addConverterFactory(GsonConverterFactory.create())
        }
        return retrofit.build()
    }

    @Provides
    fun provideCatApiInstance(@RetrofitForCatService retrofit: Retrofit): CatService {
        return retrofit.create(CatService::class.java)
    }
}