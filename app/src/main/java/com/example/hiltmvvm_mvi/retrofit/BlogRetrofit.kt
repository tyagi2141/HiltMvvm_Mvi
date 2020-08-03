package com.example.hiltmvvm_mvi.retrofit

import com.example.hiltmvvm_mvi.model.BlogNetworkEntity
import retrofit2.http.GET

interface BlogRetrofit {

    @GET("blogs")
    suspend fun get(): List<BlogNetworkEntity>
}