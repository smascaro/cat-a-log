package com.smascaro.listdetail.data.repository.datasource.remote

import android.util.Log
import androidx.paging.PagingSource
import com.smascaro.listdetail.list.model.Breed
import com.smascaro.listdetail.list.model.BreedsSearchResponseSchema

class BreedsRemoteDataSource(private val catService: CatService) : PagingSource<Int, Breed>() {
    suspend fun getBreeds() = catService.getBreeds()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Breed> {
        return try {
            val page: Int = params.key ?: 0
            Log.d("Catalog", "Loading ${params.loadSize} breeds, page $page")
            val response = catService.getBreedsPage(
                limit = params.loadSize,
                page = page
            )
            val nextPage: Int? = if (response.size < params.loadSize) {
                null
            } else {
                page + 1
            }
            LoadResult.Page(
                data = response.map { it.toModel() },
                prevKey = if (page == 0) null else page - 1,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun BreedsSearchResponseSchema.Item.toModel(): Breed {
        return Breed(
            this.name,
            this.id,
            this.description,
            this.temperament,
            Breed.Lifespan.map(this.life_span),
            Breed.Weight.map(this.weight.metric),
            Breed.AffectionLevel.map(this.affection_level)
        )

    }
}