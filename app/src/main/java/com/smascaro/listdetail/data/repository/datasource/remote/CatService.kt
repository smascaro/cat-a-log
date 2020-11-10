package com.smascaro.listdetail.data.repository.datasource.remote

import com.smascaro.listdetail.list.model.BreedsSearchResponseSchema
import retrofit2.http.GET
import retrofit2.http.Query

const val CAT_API_BASE_URL = "https://api.thecatapi.com/"
const val CAT_API_KEY = "741019bc-f09e-4fe0-b06f-95efaca300da"

interface CatService {
    @GET("v1/breeds")
    suspend fun getBreedsPage(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): BreedsSearchResponseSchema

    @GET("v1/breeds")
    suspend fun getBreeds(): BreedsSearchResponseSchema
}