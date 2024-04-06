package com.example.catapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CatService {
    @GET("images/search")
    fun randomCat(): Call<List<Cat>>
}
