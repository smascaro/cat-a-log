package com.smascaro.listdetail.data.repository

import com.smascaro.listdetail.data.repository.datasource.remote.BreedsRemoteDataSource
import com.smascaro.listdetail.data.repository.datasource.remote.CatService
import com.smascaro.listdetail.list.model.Breed
import com.smascaro.listdetail.list.model.BreedsSearchResponseSchema
import javax.inject.Inject

class BreedsRepository @Inject constructor(catService: CatService) {
    private val remote: BreedsRemoteDataSource = BreedsRemoteDataSource(catService)
    suspend fun getBreeds() = remote.getBreeds().map { it.toModel() }

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