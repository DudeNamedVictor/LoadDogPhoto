package com.example.loaddogphoto.data

import com.example.loaddogphoto.data.model.BreedImageResponse
import com.example.loaddogphoto.data.model.BreedsListResponse
import com.example.loaddogphoto.data.model.DogResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface NetworkApi {

    @GET("api/breeds/image/random")
    suspend fun getDogImage(): DogResponse

    @GET("api/breeds/list/all")
    suspend fun getBreedsList(): BreedsListResponse

    @GET("api/breed/{breed}/images/random")
    suspend fun getBreedImage(@Path("breed") breed: String): BreedImageResponse
}